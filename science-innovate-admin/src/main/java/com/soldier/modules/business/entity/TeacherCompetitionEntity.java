package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 教师竞赛
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_teacher_competition")
public class TeacherCompetitionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long tcId;

	/**
	 * 竞赛名称
	 */
	private String name;

	/**
	 * 获奖级别 国家级等
	 */
	private Integer grade;

	/**
	 * 获奖等次 一等奖等
	 */
	private Integer rank;

	/**
	 * 获奖时间
	 */
	private Date prizeTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 赛事id
	 */
	@TableField(exist=false)
	private Long matchId;
	@TableField(exist=false)
	private MatchInfoEntity matchInfoEntity;

	/**
	 * 队长教师id
	 */
	@TableField(exist=false)
	private Long firstTeacherId;

	/**
	 * 其它成员老师id
	 */
	@TableField(exist=false)
	private List<Long> otherTeachersId;

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
	 * 教师类型:1-队长 2-成员
	 */
	@TableField(exist=false)
	private Integer type;
}
