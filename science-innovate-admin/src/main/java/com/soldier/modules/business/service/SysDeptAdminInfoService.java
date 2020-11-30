package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;

import java.util.Map;

/**
 * 系统部门管理员信息表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-06-09 14:05:40
 */
public interface SysDeptAdminInfoService extends IService<SysDeptAdminInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 根据部门id查询
    SysDeptAdminInfoEntity getByDeptId(Long deptId);

    // 根据用户id查询
    SysDeptAdminInfoEntity getByUserId(Long userId);

    // 根据用户id查询部门id
    Long getDeptIdByUserId(Long userId);
}

