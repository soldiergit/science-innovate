package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfRgEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 教师-of-科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
public interface TeacherOfRgService extends IService<TeacherOfRgEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询负责人id
     */
    Long queryPersonId(Serializable id);

    /**
     * 查询已加入项目成员的teacherId
     */
    List<Long> queryChoiceTeaIdList(Serializable id);

    /**
     * 根据项目id删除所有关系记录
     */
    void removeByRgId(Long rgId);

    /**
     * 查询项目负责人信息
     */
    TeacherInfoEntity queryPersonInfo(Long rgId);

    /**
     * 查询项目成员信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long rgId);
}

