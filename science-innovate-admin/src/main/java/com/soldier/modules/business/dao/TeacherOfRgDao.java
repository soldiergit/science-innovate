package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfRgEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * 教师-of-科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Mapper
public interface TeacherOfRgDao extends BaseMapper<TeacherOfRgEntity> {

    /**
     * 查询已加入项目成员的teacherId
     */
    List<Long> queryChoiceTeaIdList(Serializable rgId);

    /**
     * 查询项目负责人信息
     */
    TeacherInfoEntity queryPersonInfo(Long id);

    /**
     * 查询项目成员信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long id);
}
