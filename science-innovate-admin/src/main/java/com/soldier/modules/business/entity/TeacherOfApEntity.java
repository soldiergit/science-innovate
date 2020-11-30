package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教师-of-论文表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_teacher_of_ap")
public class TeacherOfApEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long toapId;

	/**
	 * 教师id
	 */
	private Long teaId;

	/**
	 * 论文id
	 */
	private Long apId;

	/**
	 * 作者类型:1-主持人 2-成员
	 */
	private Integer type;

}
