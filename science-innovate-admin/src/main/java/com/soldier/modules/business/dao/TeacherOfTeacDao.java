package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.MatchInfoEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfTeacEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * 指导老师-of-教师竞赛表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface TeacherOfTeacDao extends BaseMapper<TeacherOfTeacEntity> {

    /**
     * 根据教师竞赛id查询成员教师id
     */
    List<Long> queryOtherTeacherIdList(Serializable tcId);

    /**
     * 查询项目负责人信息
     */
    TeacherInfoEntity queryPersonInfo(Long id);

    /**
     * 查询项目成员信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long id);

    /**
     * 查询赛事信息
     */
    MatchInfoEntity queryMatch(Long tcId);
}
