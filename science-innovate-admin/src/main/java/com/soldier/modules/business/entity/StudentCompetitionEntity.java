package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 学生竞赛
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_student_competition")
public class StudentCompetitionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long scId;

	/**
	 * 作品名称
	 */
	private String worksName;

	/**
	 * 参赛学生
	 */
	private String entryStu;

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
	 * 第一指导老师id
	 */
	@TableField(exist=false)
	private Long firstTeacherId;

	/**
	 * 其它老师id
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
	private TeacherInfoEntity personInfo;//第一指导老师
	@TableField(exist=false)
	private List<TeacherInfoEntity> memberInfoList;//其它指导老师
	@TableField(exist=false)
	private String membersName;//其它指导老师姓名

	/**
	 * 教师类型:1-第一指导老师 2-其它指导老师
	 */
	@TableField(exist=false)
	private Integer type;
}
