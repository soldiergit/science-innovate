package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.InnovateProjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Mapper
public interface InnovateProjectDao extends BaseMapper<InnovateProjectEntity> {

    /**
     * 查询记录数
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<InnovateProjectEntity> queryPage(Map<String, Object> params);
}
