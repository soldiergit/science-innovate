package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教师信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface TeacherInfoDao extends BaseMapper<TeacherInfoEntity> {

    /**
     * 查询数据数量
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 查询数据
     */
    List<TeacherInfoEntity> queryPage(Map<String, Object> params);

    /**
     * 教师业绩支撑选择负责人或者成员，为全体教师
     * @return key:教师id;value:教师姓名-所属二级学院名称
     */
    List<TeacherInfoEntity> select();

    /**
     * 根据用户id查询教师id
     */
    Long queryTeaIdByUserId(Long userId);

    /**
     * 根据工号查询教师是否存在
     */
    Integer queryNumByTeaCode(String teacherCode);

    /**
     * 根据所属二级学院及teacherIds查询列表，当teacherIds为空是查询全部
     */
    List<TeacherInfoEntity> queryListByDeptAndIds(Long deptId, List<Long> teacherIds);

    /**
     * 删除教师信息，以及所有教师业务档案数据
     */
    void removeTeasAndAllBusiness(List<Long> teaIds);
}
