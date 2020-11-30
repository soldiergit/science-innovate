package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.ProjectCreateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教师项目立项
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface ProjectCreateDao extends BaseMapper<ProjectCreateEntity> {

    /**
     * 查询记录数
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<ProjectCreateEntity> queryPage(Map<String, Object> params);

    /**
     * 教师以负责人或成员的身份查询未结题项目
     */
    List<ProjectCreateEntity> queryMyProject(Long userId);
}
