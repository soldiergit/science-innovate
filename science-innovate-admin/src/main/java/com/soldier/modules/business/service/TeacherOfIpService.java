package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.entity.TeacherOfIpEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 教师-of-大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
public interface TeacherOfIpService extends IService<TeacherOfIpEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询第一指导老师id
     */
    TeacherOfIpEntity queryFirstTeacherInfo(Serializable ipId);

    /**
     * 根据学生竞赛id查询其它指导老师id
     */
    List<Long> queryOtherTeacherIdList(Serializable ipId);

    /**
     * 根据项目id删除全部中间关系
     */
    void removeByIpId(Long ipId);

    /**
     * 查询第一指导老师信息
     */
    TeacherInfoEntity queryPersonInfo(Long ipId);

    /**
     * 查询其它指导老师信息
     */
    List<TeacherInfoEntity> queryMemberInfo(Long ipId);
}

