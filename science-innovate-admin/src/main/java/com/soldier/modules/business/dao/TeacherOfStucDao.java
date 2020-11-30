package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.MatchInfoEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfStucEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * 指导老师-of-学生竞赛表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface TeacherOfStucDao extends BaseMapper<TeacherOfStucEntity> {

    /**
     * 根据学生竞赛id查询其它指导老师id
     */
    List<Long> queryOtherTeacherIdList(Serializable scId);

    /**
     * 查询第一指导老师信息
     */
    TeacherInfoEntity queryPersonInfo(Long id);

    /**
     * 查询其它指导老师信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long id);

    /**
     * 查询赛事信息
     */
    MatchInfoEntity queryMatch(Long scId);
}