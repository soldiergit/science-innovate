package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfTmEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 教师-of-教材表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeacherOfTmService extends IService<TeacherOfTmEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询负责人id
     */
    Long queryPersonId(Serializable id);

    /**
     * 查询已加入项目成员的teacherId
     */
    List<Long> queryChoiceTeaIdList(Serializable tmId);

    /**
     * 根据项目id删除所有关系记录
     */
    void removeByTaId(Long tmId);

    /**
     * 查询项目负责人信息
     */
    TeacherInfoEntity queryPersonInfo(Long tmId);

    /**
     * 查询项目成员信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long tmId);
}

