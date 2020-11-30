package com.soldier.modules.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.soldier.common.utils.*;
import com.soldier.modules.business.entity.*;
import com.soldier.modules.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.soldier.modules.business.dao.InnovateProjectDao;
import com.soldier.modules.business.entity.InnovateProjectEntity;
import org.springframework.transaction.annotation.Transactional;

@Service("innovateProjectService")
public class InnovateProjectServiceImpl extends ServiceImpl<InnovateProjectDao, InnovateProjectEntity> implements InnovateProjectService {

    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;
    @Autowired
    private TeacherOfIpService teacherOfIpService;
    @Autowired
    private SysAttachService sysAttachService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        /*
         * 如果是教师，则可以根据userId查询到教师id，从而添加约束
         * 如果是二级学院管理员，则查询不到教师id，添加部门约束
         * 如果是系统管理员不添加约束
         */
        Object userId = params.get("userId");
        //系统管理员，拥有最高权限
        if (ObjectUtils.isNotEmpty(userId)) {
            if(Integer.parseInt(userId.toString()) == Constant.SUPER_ADMIN) {
//            return getAllMenuList(null);
            } else {
                Long teaId = teacherInfoService.queryTeaIdByUserId(Long.parseLong(userId.toString()));
                // 如果是教师
                if (teaId!=null)params.put("teaId", teaId);
                else {
                    // 二级学院管理员
                    Long erAdmin = sysDeptAdminInfoService.getDeptIdByUserId(Long.parseLong(userId.toString()));
                    if (erAdmin != null) params.put("deptId", erAdmin);
                }
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
        List<InnovateProjectEntity> tmp = baseMapper.queryPage(params);

        // 获取指导老师信息
        return new PageUtils(getTeacherData(tmp), totalPage, limit, page);
    }

    @Override
    public List<InnovateProjectEntity> queryList(Map<String, Object> params) {

        // 查询数据
        List<InnovateProjectEntity> tmp = baseMapper.queryPage(params);

        // 获取指导老师信息
        return getTeacherData(tmp);
    }

    @Override
    @Transactional
    public InnovateProjectEntity details(Long scId) {

        // 第一步：获取基本数据
        InnovateProjectEntity entity = super.getById(scId);

        // 第二步：查询项目所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("IP-"+scId));

        // 第三步：查询第一指导老师信息
        entity.setPersonInfo(teacherOfIpService.queryPersonInfo(scId));

        // 第四步：查询其它指导老师信息
        entity.setMemberInfoList(teacherOfIpService.queryMemberInfo(scId));

        return entity;
    }

    /**
     * 重写保存方法，因为还要保存《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public boolean save(InnovateProjectEntity entity) {

        // 第一步：保存项目信息
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        super.save(entity);

        // 第二步：保存中间关系
        teacherOfIpService.saveBatch(addRelation(entity));

        // 第三步：保存附件信息
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId("IP-" + entity.getIpId());
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        // 第四步：redis的count数加1
        redisUtils.incr(RedisKeys.getBigDataAllInnovateProjectSum());
        redisUtils.incr(RedisKeys.getBigDataAllBusinessSum());
        redisUtils.incr(RedisKeys.getBigDataBusinessNewAddMonthSum(DateUtils.format(new Date(), "yyyy-MM")));

        return true;
    }

    /**
     * 重写查询方法，因为还要查询出《项目附件》
     */
    @Override
    @Transactional
    public InnovateProjectEntity getById(Serializable id) {

        // 第一步：获取基本数据
        InnovateProjectEntity entity = super.getById(id);

        // 第二步：查询指导老师id
        TeacherOfIpEntity first = teacherOfIpService.queryFirstTeacherInfo(id);
        entity.setFirstTeacherId(first.getTeaId());
        entity.setOtherTeachersId(teacherOfIpService.queryOtherTeacherIdList(id));

        // 第三步：查询项目所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("IP-"+id));

        return entity;
    }

    /**
     * 重写更新方法，因为还要更新《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public boolean updateById(InnovateProjectEntity entity) {

        // 第一步：更新项目信息
        entity.setUpdateTime(new Date());
        super.updateById(entity);

        // 第二步：删除教师-of-学生竞赛关系
        teacherOfIpService.removeByIpId(entity.getIpId());

        // 第三步：保存教师-of-学生竞赛关系
        teacherOfIpService.saveBatch(addRelation(entity));

        // 第四步：删除项目相关附件信息
        String functionId = "IP-" + entity.getIpId();
        sysAttachService.removeByFunctionId(functionId, false);

        // 第三步：新增附件
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId(functionId);
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        return true;
    }

    /**
     * 重写更新方法，因为还要删除《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {

        // 删除附件及中间表
        for (Serializable id : idList) {
            teacherOfIpService.removeByIpId(Long.valueOf(id+""));
            sysAttachService.removeByFunctionId("IP-"+id, true);
        }

        // 删除项目信息
        return super.removeByIds(idList);
    }

    /**
     * 添加中间关系
     */
    private List<TeacherOfIpEntity> addRelation(InnovateProjectEntity entity) {
        List<TeacherOfIpEntity> teacherOfIpEntities = new ArrayList<>();
        Long scId = entity.getIpId();

        // 保存第一指导老师信息
        Long firstTeacherId = entity.getFirstTeacherId();
        TeacherOfIpEntity first = new TeacherOfIpEntity();
        first.setIpId(scId);
        first.setTeaId(firstTeacherId);
        first.setType(1);
        teacherOfIpEntities.add(first);

        // 保存其它知道老师信息
        TeacherOfIpEntity other;
        for (Long memberId : entity.getOtherTeachersId()) {
            if (memberId != 0L && !memberId.equals(firstTeacherId)) {
                other = new TeacherOfIpEntity();
                other.setIpId(scId);
                other.setTeaId(memberId);
                other.setType(2);
                teacherOfIpEntities.add(other);
            }
        }
        return teacherOfIpEntities;
    }

    /**
     * 获取各个指导老师信息
     */
    private List<InnovateProjectEntity> getTeacherData (List<InnovateProjectEntity> tmp) {
        // 查询各个指导老师信息
        Long ipId;
        List<TeacherInfoEntity> memberTmp = null;
        for (int i = 0; i < tmp.size(); i++) {
            ipId = tmp.get(i).getIpId();
            // 查询第一指导老师信息
            tmp.get(i).setPersonInfo(teacherOfIpService.queryPersonInfo(ipId));
            // 查询其它指导老师信息
            memberTmp = teacherOfIpService.queryMemberInfo(ipId);
            if (memberTmp == null || memberTmp.size() <= 0) tmp.get(i).setMembersName("暂无");
            else tmp.get(i).setMembersName(memberTmp.stream().map(TeacherInfoEntity::getName).collect(Collectors.joining(",")));
        }
        return tmp;
    }
}