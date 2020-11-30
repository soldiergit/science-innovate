package com.soldier.modules.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.soldier.common.utils.*;
import com.soldier.modules.business.entity.SysAttachEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfThEntity;
import com.soldier.modules.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.soldier.modules.business.dao.TeachingHonorDao;
import com.soldier.modules.business.entity.TeachingHonorEntity;
import org.springframework.transaction.annotation.Transactional;


@Service("teachingHonorService")
public class TeachingHonorServiceImpl extends ServiceImpl<TeachingHonorDao, TeachingHonorEntity> implements TeachingHonorService {

    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;
    @Autowired
    private TeacherOfThService teacherOfThService;
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
        List<TeachingHonorEntity> tmp = baseMapper.queryPage(params);

        return new PageUtils(getTeacherData(tmp), totalPage, limit, page);
    }

    @Override
    public List<TeachingHonorEntity> queryList(Map<String, Object> params) {

        // 查询数据
        List<TeachingHonorEntity> tmp = baseMapper.queryPage(params);

        // 获取各个主持人成员信息
        return getTeacherData(tmp);
    }

    @Override
    @Transactional
    public TeachingHonorEntity details(Long thId) {

        // 第一步：获取基本数据
        TeachingHonorEntity entity = super.getById(thId);

        // 第二步：查询项目所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("TH-"+thId));

        // 第三步：查询项目负责人信息
        entity.setPersonInfo(teacherOfThService.queryPersonInfo(thId));

        // 第四步：查询项目成员信息
        entity.setMemberInfoList(teacherOfThService.queryMemberInfo(thId));

        return entity;
    }

    /**
     * 重写保存方法，因为还要保存《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public boolean save(TeachingHonorEntity entity) {

        // 第一步：保存项目信息
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        super.save(entity);

        // 第二步：保存中间关系
        Long thId = entity.getThId();
        teacherOfThService.saveBatch(addRelation(entity, thId));

        // 保存附件信息
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId("TH-" + thId);
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        // 第四步：redis的count数加1
        redisUtils.incr(RedisKeys.getBigDataAllTeachingHonorSum());
        redisUtils.incr(RedisKeys.getBigDataAllBusinessSum());
        redisUtils.incr(RedisKeys.getBigDataBusinessNewAddMonthSum(DateUtils.format(new Date(), "yyyy-MM")));

        return true;
    }

    /**
     * 重写查询方法，因为还要查询出《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public TeachingHonorEntity getById(Serializable id) {

        // 第一步：获取基本数据
        TeachingHonorEntity entity = super.getById(id);

        // 第二步：查询已加入项目成员的teacherId
        entity.setPersonId(teacherOfThService.queryPersonId(id));
        entity.setMemberIdList(teacherOfThService.queryChoiceTeaIdList(id));

        // 第三步：查询项目所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("TH-"+id));

        return entity;
    }

    /**
     * 重写更新方法，因为还要更新《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public boolean updateById(TeachingHonorEntity entity) {

        // 第一步：更新项目信息
        entity.setUpdateTime(new Date());
        super.updateById(entity);

        // 第二步：删除中间关系
        Long thId = entity.getThId();
        teacherOfThService.removeByTaId(thId);

        // 第三步：保存中间关系
        teacherOfThService.saveBatch(addRelation(entity, thId));

        // 第四步：删除项目相关附件信息
        String functionId = "TH-" + thId;
        sysAttachService.removeByFunctionId(functionId, false);

        // 第五步：新增附件
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
            teacherOfThService.removeByTaId(Long.valueOf(id+""));
            sysAttachService.removeByFunctionId("TH-"+id, true);
        }

        // 删除项目信息
        return super.removeByIds(idList);
    }

    /**
     * 添加中间关系
     */
    private List<TeacherOfThEntity> addRelation(TeachingHonorEntity entity, Long thId) {
        List<TeacherOfThEntity> teacherOfTaEntities = new ArrayList<>();

        // 保存队长信息
        TeacherOfThEntity person = new TeacherOfThEntity();
        person.setThId(thId);
        Long personId = entity.getPersonId();
        person.setTeaId(personId);
        person.setType(1);
        teacherOfTaEntities.add(person);

        // 保存成员信息
        TeacherOfThEntity member;
        for (Long memberId : entity.getMemberIdList()) {
            if (memberId != 0L && !memberId.equals(personId)) {
                member = new TeacherOfThEntity();
                member.setThId(thId);
                member.setTeaId(memberId);
                member.setType(2);
                teacherOfTaEntities.add(member);
            }
        }
        return teacherOfTaEntities;
    }

    /**
     * 获取各个主持人成员信息
     */
    private List<TeachingHonorEntity> getTeacherData (List<TeachingHonorEntity> tmp) {
        // 查询各个主持人成员信息
        Long thId;
        List<TeacherInfoEntity> memberTmp = null;
        for (int i = 0; i < tmp.size(); i++) {
            thId = tmp.get(i).getThId();
            // 查询主持人信息
            tmp.get(i).setPersonInfo(teacherOfThService.queryPersonInfo(thId));
            // 查询成员信息
            memberTmp = teacherOfThService.queryMemberInfo(thId);
            if (memberTmp == null || memberTmp.size() <= 0) tmp.get(i).setMembersName("暂无");
            else tmp.get(i).setMembersName(memberTmp.stream().map(TeacherInfoEntity::getName).collect(Collectors.joining(",")));
        }
        return tmp;
    }
}