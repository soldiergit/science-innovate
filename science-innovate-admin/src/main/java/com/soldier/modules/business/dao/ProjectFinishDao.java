package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.ProjectFinishEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 项目结题表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-08-17 21:02:53
 */
@Mapper
public interface ProjectFinishDao extends BaseMapper<ProjectFinishEntity> {
    /**
     * 查询记录数
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<ProjectFinishEntity> queryPage(Map<String, Object> params);

    ProjectFinishEntity details(Long pfId);
}
