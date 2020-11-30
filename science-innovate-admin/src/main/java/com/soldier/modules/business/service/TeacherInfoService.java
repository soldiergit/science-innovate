package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeacherInfoEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 教师信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeacherInfoService extends IService<TeacherInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 教师业绩支撑选择负责人或者成员，为全体教师
     * @return key:教师id;value:教师姓名-所属二级学院名称
     */
    List<TeacherInfoEntity> select();

    /**
     * 超级管理员或学校管理员：选择教师，用于添加学院或教研室的成员和负责人
     */
    List<TeacherInfoEntity> adminSelect();

    /**
     * 根据用户id查询教师工号及姓名
     */
    TeacherInfoEntity queryTeacherCodeAndNameByUserId(String userId);

    /**
     * 根据用户id查询教师id
     */
    Long queryTeaIdByUserId(Long userId);

    /**
     * 根据所属二级学院及teacherIds查询列表，当stuIds为空是查询全部
     */
    List<TeacherInfoEntity> queryListByDeptAndIds(Long deptId, List<Long> teacherIds);

    /**
     * 批量添加教师
     * @param teacherInfoEntities   教师数据
     * @param userId                当前用户id
     */
    List<TeacherInfoEntity> insertBatch(List<TeacherInfoEntity> teacherInfoEntities, Long userId);

    /**
     * 批量删除教师
     * @param teaIds    教师id
     * @param userIds   教师对应的用户id
     */
    void deleteBatch(List<Long> teaIds, List<Long> userIds);

    TeacherInfoEntity details(Long teacherId);
}

