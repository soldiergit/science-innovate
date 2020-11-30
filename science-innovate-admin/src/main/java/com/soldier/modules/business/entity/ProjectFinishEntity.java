package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 项目结题表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-08-17 21:02:53
 */
@Data
@TableName("tb_project_finish")
public class ProjectFinishEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long pfId;

	/**
	 * 立项项目id
	 */
	private Long pcId;
	@TableField(exist=false)
	private ProjectCreateEntity projectCreateEntity;

	/**
	 * 结题教师id
	 */
	private Long teaId;
	@TableField(exist=false)
	private TeacherInfoEntity teacherInfoEntity;

	/**
	 * 结题时间
	 */
	private Date finishTime;

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
	 * 项目结题人用户id
	 */
	@TableField(exist=false)
	private Long userId;

	/**
	 * 附件信息
	 */
	@TableField(exist=false)
	private List<SysAttachEntity> sysAttachEntityList;

}
