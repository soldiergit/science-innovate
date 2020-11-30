package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfIpEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * 教师-of-大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Mapper
public interface TeacherOfIpDao extends BaseMapper<TeacherOfIpEntity> {

    /**
     * 根据学生竞赛id查询其它指导老师id
     */
    List<Long> queryOtherTeacherIdList(Serializable ipId);

    /**
     * 查询第一指导老师信息
     */
    TeacherInfoEntity queryPersonInfo(Long id);

    /**
     * 查询其它指导老师信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long id);
}
