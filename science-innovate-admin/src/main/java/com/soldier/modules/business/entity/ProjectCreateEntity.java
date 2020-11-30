package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 教师项目立项
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_project_create")
public class ProjectCreateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long pcId;

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 项目编号
	 */
	private String number;

	/**
	 * 项目类型:1-科研类 2-教改类
	 */
	private Integer type;

	/**
	 * 项目类别：横向等
	 */
	private Integer category;

	/**
	 * 项目级别：一般项目等
	 */
	private Integer level;

	/**
	 * 经费/万元
	 */
	private Float money;

	/**
	 * 立项时间
	 */
	private Date createTime;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 创建时间
	 */
	private Date creationTime;

	/**
	 * 结题状态
	 */
	private Integer isFinish;

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
	 * 教师类型:1-主持人 2-成员
	 */
	@TableField(exist=false)
	private Integer teacherType;
}
