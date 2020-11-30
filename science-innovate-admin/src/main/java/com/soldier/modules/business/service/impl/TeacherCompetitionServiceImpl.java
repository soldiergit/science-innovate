package com.soldier.modules.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.soldier.common.utils.*;
import com.soldier.modules.business.entity.SysAttachEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfTeacEntity;
import com.soldier.modules.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.soldier.modules.business.dao.TeacherCompetitionDao;
import com.soldier.modules.business.entity.TeacherCompetitionEntity;
import org.springframework.transaction.annotation.Transactional;

@Service("teacherCompetitionService")
public class TeacherCompetitionServiceImpl extends ServiceImpl<TeacherCompetitionDao, TeacherCompetitionEntity> implements TeacherCompetitionService {

    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;
    @Autowired
    private TeacherOfTeacService teacherOfTeacService;
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
        List<TeacherCompetitionEntity> tmp = baseMapper.queryPage(params);

        // 查询各个主持人成员信息和赛事
        return new PageUtils(getTeacherAndMatchData(tmp), totalPage, limit, page);
    }

    @Override
    public List<TeacherCompetitionEntity> queryList(Map<String, Object> params) {
        
        // 查询数据
        List<TeacherCompetitionEntity> tmp = baseMapper.queryPage(params);

        // 查询各个主持人成员信息和赛事
        return getTeacherAndMatchData(tmp);
    }

    @Override
    @Transactional
    public TeacherCompetitionEntity details(Long tcId) {

        // 第一步：获取基本数据
        TeacherCompetitionEntity entity = super.getById(tcId);

        // 第二步：查询项目所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("TC-"+tcId));

        // 第三步：查询项目负责人信息
        entity.setPersonInfo(teacherOfTeacService.queryPersonInfo(tcId));

        // 第四步：查询项目成员信息
        entity.setMemberInfoList(teacherOfTeacService.queryMemberInfo(tcId));

        // 第五步：查询赛事信息
        entity.setMatchInfoEntity(teacherOfTeacService.queryMatch(tcId));

        return entity;
    }

    /**
     * 重写保存方法，因为还要保存《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public boolean save(TeacherCompetitionEntity entity) {

        // 第一步：保存项目信息
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        super.save(entity);

        // 第二步：保存中间关系
        teacherOfTeacService.saveBatch(addRelation(entity));

        // 第三步：保存附件信息
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId("TC-" + entity.getTcId());
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        // 第四步：redis的count数加1
        redisUtils.incr(RedisKeys.getBigDataAllTeacherCompetitionSum());
        redisUtils.incr(RedisKeys.getBigDataAllBusinessSum());
        redisUtils.incr(RedisKeys.getBigDataBusinessNewAddMonthSum(DateUtils.format(new Date(), "yyyy-MM")));

        return true;
    }

    /**
     * 重写查询方法，因为还要查询出《项目附件》
     */
    @Override
    @Transactional
    public TeacherCompetitionEntity getById(Serializable id) {

        // 第一步：获取基本数据
        TeacherCompetitionEntity entity = super.getById(id);

        // 第二步：查询队长教师id
        TeacherOfTeacEntity first = teacherOfTeacService.queryFirstTeacherInfo(id);
        entity.setFirstTeacherId(first.getTeaId());
        entity.setMatchId(first.getMatchId());
        entity.setOtherTeachersId(teacherOfTeacService.queryOtherTeacherIdList(id));

        // 第三步：查询项目所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("TC-"+id));

        return entity;
    }

    /**
     * 重写更新方法，因为还要更新《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public boolean updateById(TeacherCompetitionEntity entity) {

        // 第一步：更新项目信息
        entity.setUpdateTime(new Date());
        super.updateById(entity);

        // 第二步：删除教师-of-教师竞赛关系
        teacherOfTeacService.removeByTcId(entity.getTcId());

        // 第三步：保存中间关系
        teacherOfTeacService.saveBatch(addRelation(entity));

        // 第四步：删除项目相关附件信息
        String functionId = "TC-" + entity.getTcId();
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
            teacherOfTeacService.removeByTcId(Long.valueOf(id+""));
            sysAttachService.removeByFunctionId("TC-"+id, true);
        }

        // 删除项目信息
        return super.removeByIds(idList);
    }

    /**
     * 添加中间关系
     */
    private List<TeacherOfTeacEntity> addRelation(TeacherCompetitionEntity entity) {
        List<TeacherOfTeacEntity> teacherOfTeacEntities = new ArrayList<>();
        Long tcId = entity.getTcId();
        Long matchId = entity.getMatchId();

        // 保存队长信息
        Long firstTeacherId = entity.getFirstTeacherId();
        TeacherOfTeacEntity first = new TeacherOfTeacEntity();
        first.setTcId(tcId);
        first.setMatchId(matchId);
        first.setTeaId(firstTeacherId);
        first.setType(1);
        teacherOfTeacEntities.add(first);

        // 保存成员信息
        TeacherOfTeacEntity other;
        for (Long memberId : entity.getOtherTeachersId()) {
            if (memberId != 0L && !memberId.equals(firstTeacherId)) {
                other = new TeacherOfTeacEntity();
                other.setTcId(tcId);
                other.setMatchId(matchId);
                other.setTeaId(memberId);
                other.setType(2);
                teacherOfTeacEntities.add(other);
            }
        }
        return teacherOfTeacEntities;
    }

    /**
     * 获取各个主持人成员和赛事信息
     */
    private List<TeacherCompetitionEntity> getTeacherAndMatchData (List<TeacherCompetitionEntity> tmp) {
        // 查询各个主持人成员信息和赛事
        Long tcId;
        List<TeacherInfoEntity> memberTmp = null;
        for (int i = 0; i < tmp.size(); i++) {
            tcId = tmp.get(i).getTcId();
            // 查询赛事信息
            tmp.get(i).setMatchInfoEntity(teacherOfTeacService.queryMatch(tcId));
            // 查询主持人信息
            tmp.get(i).setPersonInfo(teacherOfTeacService.queryPersonInfo(tcId));
            // 查询成员信息
            memberTmp = teacherOfTeacService.queryMemberInfo(tcId);
            if (memberTmp == null || memberTmp.size() <= 0) tmp.get(i).setMembersName("暂无");
            else tmp.get(i).setMembersName(memberTmp.stream().map(TeacherInfoEntity::getName).collect(Collectors.joining(",")));
        }
        return tmp;
    }
}