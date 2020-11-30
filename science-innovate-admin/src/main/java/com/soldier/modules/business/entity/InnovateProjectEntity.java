package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Data
@TableName("tb_innovate_project")
public class InnovateProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long ipId;

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 项目组成员
	 */
	private String entryStu;

	/**
	 * 经费/万元
	 */
	private Float money;

	/**
	 * 立项时间
	 */
	private Date awardTime;

	/**
	 * 立项级别 国家级等
	 */
	private Integer grade;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

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
