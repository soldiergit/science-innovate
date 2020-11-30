package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.MatchInfoEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfStucEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 指导老师-of-学生竞赛表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeacherOfStucService extends IService<TeacherOfStucEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询第一指导老师id及赛事id
     */
    TeacherOfStucEntity queryFirstTeacherInfo(Serializable scId);

    /**
     * 根据学生竞赛id查询其它指导老师id
     */
    List<Long> queryOtherTeacherIdList(Serializable scId);

    /**
     * 根据学生竞赛id删除全部中间关系
     */
    void removeByScId(Long scId);

    /**
     * 查询第一指导老师信息
     */
    TeacherInfoEntity queryPersonInfo(Long scId);

    /**
     * 查询其它指导老师信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long scId);

    /**
     * 查询赛事信息
     */
    MatchInfoEntity queryMatch(Long scId);
}