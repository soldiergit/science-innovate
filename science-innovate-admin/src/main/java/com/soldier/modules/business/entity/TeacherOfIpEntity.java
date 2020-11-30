package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教师-of-大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Data
@TableName("tb_teacher_of_ip")
public class TeacherOfIpEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long toipId;
	/**
	 * 教师id
	 */
	private Long teaId;
	/**
	 * 大创项目id
	 */
	private Long ipId;
	/**
	 * 教师类型:1-第一指导老师 2-第二指导老师
	 */
	private Integer type;

}
