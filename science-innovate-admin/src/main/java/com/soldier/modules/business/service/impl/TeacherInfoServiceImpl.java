package com.soldier.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.soldier.common.utils.*;
import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;
import com.soldier.modules.business.entity.TeacherOfDeptEntity;
import com.soldier.modules.business.service.*;
import com.soldier.modules.sys.entity.SysUserEntity;
import com.soldier.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.soldier.modules.business.dao.TeacherInfoDao;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import org.springframework.transaction.annotation.Transactional;


@Service("teacherInfoService")
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoDao, TeacherInfoEntity> implements TeacherInfoService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TeacherOfDeptService teacherOfDeptService;
    @Autowired
    private RedisUtils redisUtils;

    private List<Long> roleIdList = new ArrayList<Long>(){{{add(3L);}}};

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        // 如果二级学院管理员，添加约束
        Object userId = params.get("userId");
        // 系统管理员，拥有最高权限
        if (ObjectUtils.isNotEmpty(userId)) {
            if(Integer.parseInt(userId.toString()) == Constant.SUPER_ADMIN) {
//            return getAllMenuList(null);
            } else {
                // 二级学院管理员
                Long erAdmin = sysDeptAdminInfoService.getDeptIdByUserId(Long.parseLong(userId.toString()));
                if (erAdmin != null) params.put("deptId", erAdmin);
            }
        }

        //页索引
        Integer page = Integer.parseInt(params.get("page").toString());
        //每页数目
        Integer limit = Integer.parseInt(params.get("limit").toString());

        // 获取记录数
        Integer totalPage = baseMapper.queryCountPage(params);
        if (totalPage <= 0) {
            return new PageUtils(null, totalPage, limit, page);
        }
        //startIndex
        Integer startIndex = 0 + limit * (page - 1);

        params.put("startIndex", startIndex);
        params.put("pageSize", limit);

        // 查询数据
        List<TeacherInfoEntity> teacherInfoEntities = baseMapper.queryPage(params);

        return new PageUtils(teacherInfoEntities, totalPage, limit, page);
    }

    @Override
    public List<TeacherInfoEntity> select() {

        // 先查询缓存中是否有数据
        long start = System.currentTimeMillis();
        List<TeacherInfoEntity> list = redisUtils.getList(RedisKeys.getAllTeacherSelect(), TeacherInfoEntity.class);

        if (list != null && list.size() != 0) {
            logger.info("TeacherInfoService-allTeacherSelect：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
            // 如果有数据 直接返回
            return list;
        } else {
            // 如果没有 则先保存到redis再返回
            List<TeacherInfoEntity> teacherInfoEntities = baseMapper.select();

            // 不设置过期时长 永久有效
            redisUtils.set(RedisKeys.getAllTeacherSelect(), teacherInfoEntities, RedisUtils.NOT_EXPIRE);
            logger.info("TeacherInfoService-allTeacherSelect：查询了redis缓存，没查到数据，消耗时间：{}", System.currentTimeMillis() - start);
            return teacherInfoEntities;
        }
    }

    /**
     * 2020-08-14利用redis优化==>超级管理员或学校管理员：选择教师，用于添加学院或教研室的成员和负责人
     */
    @Override
    public List<TeacherInfoEntity> adminSelect() {

        // 先查询缓存中是否有数据
        long start = System.currentTimeMillis();
        List<TeacherInfoEntity> list = redisUtils.getList(RedisKeys.getTeacherInfoAdminSelect(), TeacherInfoEntity.class);

        if (list != null && list.size() != 0) {
            logger.info("TeacherInfoService-adminSelect：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
            // 如果有数据 直接返回
            return list;
        } else {
            // 如果没有 则先保存到redis再返回
            QueryWrapper queryWrapper = new QueryWrapper<TeacherInfoEntity>();
            queryWrapper.select("teacher_id", "name");
            List<TeacherInfoEntity> teacherInfoEntities = super.list(queryWrapper);

            // 不设置过期时长 永久有效
            redisUtils.set(RedisKeys.getTeacherInfoAdminSelect(), teacherInfoEntities, RedisUtils.NOT_EXPIRE);
            logger.info("TeacherInfoService-adminSelect：查询了redis缓存，没查到数据，消耗时间：{}", System.currentTimeMillis() - start);
            return teacherInfoEntities;
        }
    }

    @Override
    public TeacherInfoEntity queryTeacherCodeAndNameByUserId(String userId) {

        QueryWrapper queryWrapper = new QueryWrapper<TeacherInfoEntity>();

        queryWrapper.select("teacher_code", "name");
        queryWrapper.eq("user_id", userId);

        return super.getOne(queryWrapper);
    }

    @Override
    public Long queryTeaIdByUserId(Long userId) {

        return baseMapper.queryTeaIdByUserId(userId);
    }

    /**
     * 根据所属二级学院及teacherIds查询列表，当teacherIds为空是查询全部
     */
    @Override
    public List<TeacherInfoEntity> queryListByDeptAndIds(Long deptId, List<Long> teacherIds) {
        return baseMapper.queryListByDeptAndIds(deptId, teacherIds);
    }

    /**
     * 批量导入教师信息
     * @param teacherInfoEntities   教师数据
     * @param userId                当前用户id
     * @return  工号已存在的错误数据
     */
    @Override
    @Transactional
    public List<TeacherInfoEntity> insertBatch(List<TeacherInfoEntity> teacherInfoEntities, Long userId) {

        // 首先：获取当前用户（二级学院管理员）所属部门
        SysDeptAdminInfoEntity byUserId = sysDeptAdminInfoService.getByUserId(userId);
        Long deptId = byUserId.getDeptId();

        // 第二步：创建错误数据集合
        List<TeacherInfoEntity> errorData = new ArrayList<>();

        // 第三步：遍历教师信息集合
        Date nowDate = new Date();
        SysUserEntity tempUser = null;
        for (TeacherInfoEntity teacherInfo: teacherInfoEntities) {
            // 查询该工号教师是否存在
            String teacherCode = teacherInfo.getTeacherCode();
            Integer number = this.baseMapper.queryNumByTeaCode(teacherCode);
            if (number != null) {
                errorData.add(teacherInfo);
                // 如果存在，结束本次业务操作
                continue;
            }

            System.out.println(teacherInfo.toString());
            // 第四步：如果不存在，根据工号创建用户
            tempUser = this.createNewUser(null, userId);
            tempUser.setUsername(teacherCode);
            sysUserService.saveUser(tempUser);

            // 第五步：保存教师信息
            teacherInfo.setUserId(tempUser.getUserId());
            teacherInfo.setCreateTime(nowDate);
            teacherInfo.setUpdateTime(nowDate);
            super.save(teacherInfo);

            // 第六步：保存教师 of 部门信息
            TeacherOfDeptEntity teacherOfDeptEntity = new TeacherOfDeptEntity();
            teacherOfDeptEntity.setTeaId(teacherInfo.getTeacherId());
            teacherOfDeptEntity.setDeptId(deptId);
            teacherOfDeptService.save(teacherOfDeptEntity);

            // 第七步：讲用户id设为空
            tempUser.setUserId(null);

            // 第八步：redis的count数加1
            redisUtils.incr(RedisKeys.getBigDataAllTeacherSum());
            redisUtils.incr(RedisKeys.getBigDataAllUserSum());
        }

        // 删除redis
        removeRedisData();

        return errorData;
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> teaIds, List<Long> userIds) {

        // 第一步：删除教师信息，以及所有教师业务档案数据
        baseMapper.removeTeasAndAllBusiness(teaIds);

        // 第二步：删除对应用户，以及用户角色数据
        sysUserService.removeUserDataByIds(userIds);

        // 删除redis
        removeRedisData();
    }

    @Override
    @Transactional
    public TeacherInfoEntity details(Long teacherId) {
        return null;
    }

    /**
     * 重写保存方法：因为还要保存中间表信息--用户表、教师部门表
     * @param entity    教师信息
     * @return
     */
    @Override
    @Transactional
    public boolean save(TeacherInfoEntity entity) {

        // 首先：获取当前用户（二级学院管理员）所属部门
        Long createUserId = entity.getCreateUserId();
        SysDeptAdminInfoEntity byUserId = sysDeptAdminInfoService.getByUserId(createUserId);
        Long deptId = byUserId.getDeptId();

        // 第二步：查询该工号教师是否存在
        String teacherCode = entity.getTeacherCode();
        Integer number = this.baseMapper.queryNumByTeaCode(teacherCode);
        if (number != null) {
            // 返回错误
            return false;
        }

        // 第三步：如果不存在，根据工号创建用户
        SysUserEntity sysUserEntity = this.createNewUser(entity.getTeacherCode(), createUserId);
        sysUserService.saveUser(sysUserEntity);

        // 第四步：保存教师信息
        Date nowDate = new Date();
        entity.setUserId(sysUserEntity.getUserId());
        entity.setCreateTime(nowDate);
        entity.setUpdateTime(nowDate);
        super.save(entity);

        // 第五步：保存教师 of 部门信息
        TeacherOfDeptEntity teacherOfDeptEntity = new TeacherOfDeptEntity();
        teacherOfDeptEntity.setTeaId(entity.getTeacherId());
        teacherOfDeptEntity.setDeptId(deptId);
        teacherOfDeptService.save(teacherOfDeptEntity);

        // 第六步：redis的count数加1
        redisUtils.incr(RedisKeys.getBigDataAllTeacherSum());
        redisUtils.incr(RedisKeys.getBigDataAllUserSum());

        // 删除redis
        removeRedisData();

        return true;
    }

    /**
     * 添加、修改、删除时都要删除reids中的数据
     */
    @Override
    public boolean updateById(TeacherInfoEntity entity) {
        // 数据库操作
        entity.setUpdateTime(new Date());
        super.updateById(entity);
        // 删除redis
        removeRedisData();
        return true;
    }

    /**
     * 创建一个新用户
     */
    private SysUserEntity createNewUser(String teacherCode, Long createUserId) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername(teacherCode);
        sysUserEntity.setPassword("123456");
        sysUserEntity.setEmail("123456@qq.com");
        sysUserEntity.setMobile("18888888888");
        sysUserEntity.setStatus(1);
        sysUserEntity.setRoleIdList(this.roleIdList);
        sysUserEntity.setCreateUserId(createUserId);

        return sysUserEntity;
    }

    /**
     * 删除redis中关于教师全部的数据:science_innovate_teacher_开头的
     */
    private void removeRedisData() {
        redisUtils.deleteBatch(RedisKeys.getTeacherPublicKey());
        logger.info("删除redis中关于教师全部的数据");
    }

}