package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.TeachingMaterialEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教材表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface TeachingMaterialDao extends BaseMapper<TeachingMaterialEntity> {

    /**
     * 查询记录数
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<TeachingMaterialEntity> queryPage(Map<String, Object> params);
	
}
