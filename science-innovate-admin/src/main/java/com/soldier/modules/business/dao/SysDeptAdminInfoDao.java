package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统部门管理员信息表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-06-09 14:05:40
 */
@Mapper
public interface SysDeptAdminInfoDao extends BaseMapper<SysDeptAdminInfoEntity> {

    // 根据部门id查询
    SysDeptAdminInfoEntity getByDeptId(Long deptId);

    // 根据用户id查询
    SysDeptAdminInfoEntity getByUserId(Long deptId);
}
