package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.soldier.modules.sys.entity.SysUserEntity;
import lombok.Data;

/**
 * 系统部门管理员信息表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-06-09 14:05:40
 */
@Data
@TableName("sys_dept_admin_info")
public class SysDeptAdminInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long deptAdminId;

	/**
	 * 管理部门ID
	 */
	private Long deptId;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 部门信息
	 * 注解加载bean属性上，表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个，不会报错
	 */
	@TableField(exist = false)
	private SysDeptEntity sysDeptEntity;

	/**
	 * 用户信息
	 * 注解加载bean属性上，表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个，不会报错
	 */
	@TableField(exist = false)
	private SysUserEntity sysUserEntity;

}
