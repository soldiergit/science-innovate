package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.ResearchGainEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@Mapper
public interface ResearchGainDao extends BaseMapper<ResearchGainEntity> {

    /**
     * 查询记录数
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<ResearchGainEntity> queryPage(Map<String, Object> params);
}
