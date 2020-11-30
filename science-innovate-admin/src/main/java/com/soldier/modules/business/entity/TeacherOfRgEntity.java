package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教师-of-科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Data
@TableName("tb_teacher_of_rg")
public class TeacherOfRgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long torgId;
	/**
	 * 教师id
	 */
	private Long teaId;
	/**
	 * 科研成果id
	 */
	private Long rgId;
	/**
	 * 教师类型:1-主持人 2-成员
	 */
	private Integer type;

}
