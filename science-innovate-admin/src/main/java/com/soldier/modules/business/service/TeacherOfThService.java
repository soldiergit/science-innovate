package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfThEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 教师-of-教学荣誉表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeacherOfThService extends IService<TeacherOfThEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询负责人id
     */
    Long queryPersonId(Serializable id);

    /**
     * 查询已加入项目成员的teacherId
     */
    List<Long> queryChoiceTeaIdList(Serializable thId);

    /**
     * 根据项目id删除所有关系记录
     */
    void removeByTaId(Long thId);

    /**
     * 查询项目负责人信息
     */
    TeacherInfoEntity queryPersonInfo(Long thId);

    /**
     * 查询项目成员信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long thId);
}

