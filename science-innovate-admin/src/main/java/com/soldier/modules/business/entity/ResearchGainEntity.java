package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Data
@TableName("tb_research_gain")
public class ResearchGainEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long rgId;

	/**
	 * 成果名称
	 */
	private String name;

	/**
	 * 参与学生(可为空)
	 */
	private String entryStu;

	/**
	 * 获得时间
	 */
	private Date awardTime;

	/**
	 * 成果类型
	 */
	private Integer gain;

	/**
	 * 成果级别 国家级等
	 */
	private Integer grade;

	/**
	 * 成果等次 一等奖等
	 */
	private Integer rank;

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
