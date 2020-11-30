package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 教师(部门成员)与部门对应关系
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 16:28:20
 */
public interface TeacherOfDeptService extends IService<TeacherOfDeptEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询已加入部门的教师
     */
    List<TeacherInfoEntity> queryChoiceTeaList(Map<String, Object> params);

    /**
     * 查询已加入部门的教师id
     */
    List<Long> queryChoiceTeaIds(Map<String, Object> params);

    /**
     * 根据部门id删除所有关系记录
     */
    void removeByDeptId(Long deptId);
}

