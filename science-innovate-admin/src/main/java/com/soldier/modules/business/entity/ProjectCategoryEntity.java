package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 项目类别
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:43
 */
@Data
@TableName("tb_project_category")
public class ProjectCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long value;
	/**
	 * 项目类别
	 */
	private String label;

}
