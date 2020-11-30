package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教师(部门成员)与部门对应关系
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 16:28:20
 */
@Data
@TableName("tb_teacher_of_dept")
public class TeacherOfDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;

	/**
	 * 教师ID
	 */
	private Long teaId;

	/**
	 * 部门ID
	 */
	private Long deptId;

}
