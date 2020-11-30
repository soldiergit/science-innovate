package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教师(部门成员)与部门对应关系
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 16:28:20
 */
@Mapper
public interface TeacherOfDeptDao extends BaseMapper<TeacherOfDeptEntity> {

    /**
     * 查询已加入部门的教师
     */
    List<TeacherInfoEntity> queryChoiceTeaList(Object deptId, Object userId);

    /**
     * 查询已加入部门的教师id
     */
    List<Long> queryChoiceTeaIds(Object deptId, Object userId);

    /**
     * 根据部门id删除所有关系记录
     */
    void removeByDeptId(Long deptId);

}
