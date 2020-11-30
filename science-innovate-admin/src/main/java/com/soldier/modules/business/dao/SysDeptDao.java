package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.SysDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统部门表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    /**
     * 根据用户id查询教师所属二级学院
     */
    SysDeptEntity queryCollegeByUserId(Long userId);

    /**
     * 根据用户id查询教师所属二级学院id
     */
    Long queryCollegeIdByUserId(Long userId);

    /**
     * 查询子部门数
     */
    Integer queryNumByParent(Long parentId);
}
