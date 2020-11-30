package com.soldier.modules.business.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教师信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Data
@TableName("tb_teacher_info")
public class TeacherInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ExcelIgnore
	private Long teacherId;

	/**
	 * 用户id
	 */
	@ExcelIgnore
	private Long userId;

	/**
	 * 工号
	 */
	@ExcelProperty(value = "工号")
	private String teacherCode;

	/**
	 * 姓名
	 */
	@ExcelProperty(value = "姓名")
	private String name;

	/**
	 * 性别
	 * 自定义性别转换器，因为数据直接保存男女，所以用不上
	 */
	@ExcelProperty(value = "性别")
//	@ExcelProperty(value = "性别", easyexcel = GenderConverter.class)
	private String sex;

	/**
	 * 出生日期
	 */
//	@DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
	@DateTimeFormat("yyyy-MM-dd")
	@ExcelProperty(value = "出生日期")
	private Date birthDay;

	/**
	 * 入职时间
	 */
//	@DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
	@DateTimeFormat("yyyy-MM-dd")
	@ExcelProperty(value = "入职时间")
	private Date entryTime;

	/**
	 * 个人照片
	 * 阿里云提供的String类型照片选择器
	 */
	@ExcelIgnore
//	@ExcelProperty(value = "照片", easyexcel = StringImageConverter.class)
	private String imgPath;

	/**
	 * 最高学历
	 */
	@ExcelProperty(value = "最高学历")
	private String highEdu;

	/**
	 * 第一学历
	 */
	@ExcelProperty(value = "第一学历")
	private String firstEdu;

	/**
	 * 专业技术职称
	 */
	@ExcelProperty(value = "专业技术职称")
	private String technicalPost;

	/**
	 * 行政职务
	 */
	@ExcelProperty(value = "行政职务")
	private String administerPost;

	/**
	 * 简历
	 */
	@ExcelProperty(value = "简历")
	private String resume;

	/**
	 * 主要从事工作及研究方向
	 */
	@ExcelProperty(value = "主要从事工作及研究方向")
	private String other;

	/**
	 * 创建时间
	 */
	@ExcelIgnore
	private Date createTime;

	/**
	 * 修改时间
	 */
	@ExcelIgnore
	private Date updateTime;

	/**
	 * 创建当前教师的用户id
	 */
	@ExcelIgnore
	@TableField(exist=false)
	private Long createUserId;

	/**
	 * 用于前端下拉框是否禁用
	 */
	@ExcelIgnore
	@TableField(exist=false)
	private Boolean disabled;

	public String getTeacherCodeAndName() {
		return teacherCode + "-" + name;
	}
}
