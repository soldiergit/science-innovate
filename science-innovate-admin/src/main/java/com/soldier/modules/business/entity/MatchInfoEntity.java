package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 赛事信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_match_info")
public class MatchInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long matchId;

	/**
	 * 主办单位
	 */
	private String organizer;

	/**
	 * 承办单位
	 */
	private String contractor;

	/**
	 * 赛事名称
	 */
	private String name;

	/**
	 * 赛事等级：1-国家级 2-省级 3-校级
	 */
	private Integer grade;

	/**
	 * 赛事类型：1-学生竞赛 2-教师竞赛
	 */
	private Integer type;

	/**
	 * 附件信息
	 */
	@TableField(exist=false)
	private List<SysAttachEntity> sysAttachEntityList;

}
