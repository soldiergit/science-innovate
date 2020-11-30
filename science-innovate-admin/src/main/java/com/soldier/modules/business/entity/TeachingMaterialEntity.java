package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 教材表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_teaching_material")
public class TeachingMaterialEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long materialId;

	/**
	 * 教材名称
	 */
	private String name;

	/**
	 * 出版社
	 */
	private String press;

	/**
	 * 出版时间
	 */
	private String publishTime;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 项目负责人id
	 */
	@TableField(exist=false)
	private Long personId;

	/**
	 * 成员id
	 */
	@TableField(exist=false)
	private List<Long> memberIdList;

	/**
	 * 附件信息
	 */
	@TableField(exist=false)
	private List<SysAttachEntity> sysAttachEntityList;

	/**
	 * 教师信息
	 */
	@TableField(exist=false)
	private TeacherInfoEntity personInfo;//项目负责人
	@TableField(exist=false)
	private List<TeacherInfoEntity> memberInfoList;//项目成员
	@TableField(exist=false)
	private String membersName;//项目成员姓名

	/**
	 * 教师类型:1-主编 2-成员
	 */
	@TableField(exist=false)
	private Integer type;
}
