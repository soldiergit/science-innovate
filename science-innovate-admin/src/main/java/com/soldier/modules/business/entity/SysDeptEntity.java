package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 系统部门表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long deptId;

	/**
	 * 父部门ID，一级部门(学校)为0
	 */
	private Long parentId;

	/**
	 * 上级部门名称
	 */
	@TableField(exist=false)
	private String parentName;

	/**
	 * 部门负责人ID，为教师ID
	 */
	private Long personId;

	/**
	 * 部门负责人姓名
	 */
	@TableField(exist=false)
	private String personName;

	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 部门电话
	 */
	private String phone;

	/**
	 * 类型     0：二级学院   1：教研室/实验室
	 */
	private Integer type;

	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean open;

	/**
	 * 用于前端下拉框是否禁用
	 */
	@TableField(exist=false)
	private Boolean disabled;

	/**
	 * 成员id
	 */
	@TableField(exist=false)
	private List<Long> memberIdList;

}
