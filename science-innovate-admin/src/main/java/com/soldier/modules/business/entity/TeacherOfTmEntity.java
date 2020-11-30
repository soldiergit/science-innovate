package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教师-of-教材表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_teacher_of_tm")
public class TeacherOfTmEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long totmId;

	/**
	 * 教师id
	 */
	private Long teaId;

	/**
	 * 教材id
	 */
	private Long tmId;

	/**
	 * 教师类型:1-主编 2-成员
	 */
	private Integer type;

}
