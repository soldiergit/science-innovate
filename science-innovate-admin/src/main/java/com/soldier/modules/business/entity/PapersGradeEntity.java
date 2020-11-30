package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 论文等级
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
@Data
@TableName("tb_papers_grade")
public class PapersGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long value;
	/**
	 * 论文等级
	 */
	private String label;

}
