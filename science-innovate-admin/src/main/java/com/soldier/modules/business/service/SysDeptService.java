package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统部门表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询部门树
     */
    List<SysDeptEntity> treelist(Object userId);

    /**
     * 查询所有二级学院（一级部门）
     */
    List<SysDeptEntity> queryCollegeList();

    /**
     * 根据用户id查询教师所属二级学院
     */
    SysDeptEntity queryCollegeByUserId(Long userId);

    /**
     * 根据用户id查询教师所属二级学院id
     */
    Long queryCollegeIdByUserId(Long userId);

    /**
     * 查询子部门数
     */
    Integer queryNumByParent(Long parentId);

    /**
     * 查询部门数量
     * @param type  部门类型
     */
    Integer queryCount(Integer type);
}

