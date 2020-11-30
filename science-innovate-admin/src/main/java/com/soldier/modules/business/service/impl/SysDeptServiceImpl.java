package com.soldier.modules.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.soldier.common.utils.*;
import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfDeptEntity;
import com.soldier.modules.business.service.SysDeptAdminInfoService;
import com.soldier.modules.business.service.TeacherInfoService;
import com.soldier.modules.business.service.TeacherOfDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.soldier.modules.business.dao.SysDeptDao;
import com.soldier.modules.business.entity.SysDeptEntity;
import com.soldier.modules.business.service.SysDeptService;
import org.springframework.transaction.annotation.Transactional;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private TeacherOfDeptService teacherOfDeptService;
    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDeptEntity> page = this.page(
                new Query<SysDeptEntity>().getPage(params),
                new QueryWrapper<SysDeptEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 2020-08-14利用redis优化：所有菜单列表
     */
    @Override
    public List<SysDeptEntity> treelist(Object userId) {

        Long thisUserId = null;
        if (ObjectUtils.isNotEmpty(userId)) {
            thisUserId = Long.parseLong(userId.toString());
        }

        // 先查询缓存中是否有数据
        long start = System.currentTimeMillis();
        List<SysDeptEntity> list = redisUtils.getList(RedisKeys.getSysDeptUserTreeList(thisUserId), SysDeptEntity.class);

        if (list != null && list.size() != 0) {
            logger.info("SysDept-treelist：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
            // 如果有数据 直接返回
            return list;
        } else {
            // 如果没有 则先保存到redis再返回
            List<SysDeptEntity> deptList = new ArrayList<>();
            //系统管理员，拥有最高权限
            if (thisUserId == null || thisUserId == Constant.SUPER_ADMIN) {
                deptList = super.list();
            } else {
                QueryWrapper queryWrapper = new QueryWrapper<SysDeptEntity>();
                //  查询二级学院管理员所管理的二级学院id
                SysDeptAdminInfoEntity deptAdmin = sysDeptAdminInfoService.getByUserId(thisUserId);
                queryWrapper.eq("parent_id", deptAdmin.getDeptId());
                deptList = super.list(queryWrapper);
            }

            // 查询父部门名称及部门负责人姓名
            for(SysDeptEntity sysMenuEntity : deptList){
                SysDeptEntity parentMenuEntity = super.getById(sysMenuEntity.getParentId());
                TeacherInfoEntity personInfo = teacherInfoService.getById(sysMenuEntity.getPersonId());
                if(parentMenuEntity != null){
                    sysMenuEntity.setParentName(parentMenuEntity.getName());
                }
                if(personInfo != null){
                    sysMenuEntity.setPersonName(personInfo.getName());
                }
            }

            // 不设置过期时长 永久有效
            redisUtils.set(RedisKeys.getSysDeptUserTreeList(thisUserId == null ? Constant.SUPER_ADMIN : thisUserId), deptList, RedisUtils.NOT_EXPIRE);
            logger.info("SysDept-treelist：查询了redis缓存，没查到数据，消耗时间：{}", System.currentTimeMillis() - start);
            return deptList;
        }
    }

    /**
     * 2020-08-14利用redis优化：查询所有二级学院（一级部门）
     */
    @Override
    public List<SysDeptEntity> queryCollegeList() {

        // 先查询缓存中是否有数据
        long start = System.currentTimeMillis();
        List<SysDeptEntity> list = redisUtils.getList(RedisKeys.getSysDeptAllCollegeList(), SysDeptEntity.class);

        if (list != null && list.size() != 0) {
            logger.info("SysDept-queryCollegeList：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
            // 如果有数据 直接返回
            return list;
        } else {
            // 如果没有 则先保存到redis再返回
            QueryWrapper queryWrapper = new QueryWrapper<SysDeptEntity>();
            queryWrapper.eq("type", 0);
            List<SysDeptEntity> collegeList = super.list(queryWrapper);

            // 不设置过期时长 永久有效
            redisUtils.set(RedisKeys.getSysDeptAllCollegeList(), collegeList, RedisUtils.NOT_EXPIRE);
            logger.info("SysDept-queryCollegeList：查询了redis缓存，没查到数据，不是系统管理员#########消耗时间：{}", System.currentTimeMillis() - start);
            return collegeList;
        }
    }

    @Override
    public SysDeptEntity queryCollegeByUserId(Long userId) {
        return baseMapper.queryCollegeByUserId(userId);
    }

    @Override
    public Long queryCollegeIdByUserId(Long userId) {
        return baseMapper.queryCollegeIdByUserId(userId);
    }

    @Override
    public Integer queryNumByParent(Long parentId) {
        return baseMapper.queryNumByParent(parentId);
    }

    @Override
    public Integer queryCount(Integer type) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);

        return super.count(queryWrapper);
    }

    /**
     * 重写保存方法，因为还要保存《教师(部门成员)与部门对应关系》
     *      而且还要删除redis中的数据
     */
    @Override
    @Transactional
    public boolean save(SysDeptEntity entity) {

        // 保存部门信息
        super.save(entity);

        // 保存关系信息
        teacherOfDeptService.saveBatch(addRelation(entity.getDeptId(), entity.getPersonId(), entity.getMemberIdList()));

        // 删除redis
        removeRedisData();

        // 第四步：redis的count数加1
        if (Objects.equals(entity.getType(), 0)) {
            redisUtils.incr(RedisKeys.getBigDataAllCollegeSum());
        } else {
            redisUtils.incr(RedisKeys.getBigDataAllLabSum());
        }

        return true;
    }

    public static void main(String[] args) {
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        sysDeptEntity.setType(0);
        if (Objects.equals(sysDeptEntity.getType(), 0)) {
            System.out.println("二级学院");
        } else {
            System.out.println("教研室");
        }
    }

    /**
     * 重写更新方法，因为还要更新《教师(部门成员)与部门对应关系》
     *          而且还要删除redis中的数据
     */
    @Override
    public boolean updateById(SysDeptEntity entity) {

        // 更新部门信息
        super.updateById(entity);

        // 删除中间关系
        teacherOfDeptService.removeByDeptId(entity.getDeptId());

        // 保存关系信息
        teacherOfDeptService.saveBatch(addRelation(entity.getDeptId(), entity.getPersonId(), entity.getMemberIdList()));

        // 删除redis
        removeRedisData();

        return true;
    }

    /**
     * 重写删除方法
     *      而且还要删除redis中的数据
     */
    @Override
    public boolean removeById(Serializable id) {

        // 删除部门
        super.removeById(id);

        // 删除redis
        removeRedisData();

        return true;
    }

    /**
     * 添加中间关系
     * @param deptId        部门id
     * @param personId      部门负责人id
     * @param memberIdList  成员id
     * @return
     */
    private List<TeacherOfDeptEntity> addRelation(Long deptId, Long personId, List<Long> memberIdList) {
        List<TeacherOfDeptEntity> data = new ArrayList<>();
        TeacherOfDeptEntity tmp = null;
//        boolean memberHavePerson = false;
        for (Long memberId : memberIdList) {
            tmp = new TeacherOfDeptEntity();
            tmp.setDeptId(deptId);
            tmp.setTeaId(memberId);
            data.add(tmp);
//            if (memberId.longValue() == personId.longValue()) memberHavePerson = true;
        }

        // TODO 部门负责人算不算部门成员？？？待确认
//        if (!memberHavePerson) {
//            tmp = new TeacherOfDeptEntity();
//            tmp.setDeptId(deptId);
//            tmp.setTeaId(personId);
//            data.add(tmp);
//        }
        return data;
    }

    /**
     * 删除redis中关于部门全部的数据:science_innovate_sys_dept_开头的
     */
    private void removeRedisData() {
        redisUtils.deleteBatch(RedisKeys.getSysDeptPublicKey());
        logger.info("删除redis中关于部门全部的数据");
    }
}