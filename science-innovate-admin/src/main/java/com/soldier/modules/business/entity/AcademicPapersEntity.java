package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 学术论文
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_academic_papers")
public class AcademicPapersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long apId;

	/**
	 * 论文名称
	 */
	private String name;

	/**
	 * 发布期刊名称
	 */
	private String periodicalName;

	/**
	 * 归属项目名称及编号
	 */
	private String projectName;

	/**
	 * 作者类型:1-第一作者或通讯作者 2-独立作者 3-其他
	 */
	private Integer authorType;

	/**
	 * 论文类型:1-教改类 2-科研类
	 */
	private Integer paperType;

	/**
	 * 论文等级:国家级等
	 */
	private Long grade;

	/**
	 * 发表时间
	 */
	private Date publishTime;

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
	 * 教师类型:1-主持人 2-成员
	 */
	@TableField(exist=false)
	private Integer type;
}
