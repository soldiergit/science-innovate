package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 指导老师-of-教师竞赛表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_teacher_of_teac")
public class TeacherOfTeacEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long totcId;

	/**
	 * 教师id
	 */
	private Long teaId;

	/**
	 * 教师竞赛id
	 */
	private Long tcId;

	/**
	 * 赛事id
	 */
	private Long matchId;

	/**
	 * 教师类型:1-队长 2-成员
	 */
	private Integer type;

}
