package com.soldier.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统附件
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_sys_attach")
public class SysAttachEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 附件id
	 */
	@TableId
	private Long attachId;

	/**
	 * 功能id：如论文附件=AP-论文id，项目立项附件=PC-项目id
	 * 文件保存路径：
	 *      ==》教师信息-TI；
	 *      ==》学生论文-AP；
	 *      ==》大创项目-IP；
	 *      ==》项目立项-PC；
	 *      ==》项目结题-PF；
	 *      ==》科研成果-RG；
	 *      ==》学生竞赛-SC；
	 *      ==》教师竞赛-TC；
	 *      ==》教材-TM；
	 *      ==》教学成果-TA；
	 *      ==》教学荣誉-TH；
	 *      ==》赛事-MATCH；
	 */
	private String functionId;

	/**
	 * 附件名称
	 */
	private String attachName;

	/**
	 * 附件路径
	 */
	private String attachPath;

	/**
	 * 0-未删除，1-已删除
	 */
	private Integer isDel;

}
