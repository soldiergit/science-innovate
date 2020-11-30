package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.MatchInfoEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfTeacEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 指导老师-of-教师竞赛表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeacherOfTeacService extends IService<TeacherOfTeacEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询队长教师id及赛事id
     */
    TeacherOfTeacEntity queryFirstTeacherInfo(Serializable id);

    /**
     * 根据教师竞赛id查询成员教师id
     */
    List<Long> queryOtherTeacherIdList(Serializable id);

    /**
     * 根据教师竞赛id删除全部中间关系
     */
    void removeByTcId(Long tcId);

    /**
     * 查询项目负责人信息
     */
    TeacherInfoEntity queryPersonInfo(Long tcId);

    /**
     * 查询项目成员信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long tcId);

    /**
     * 查询赛事信息
     */
    MatchInfoEntity queryMatch(Long tcId);
}

