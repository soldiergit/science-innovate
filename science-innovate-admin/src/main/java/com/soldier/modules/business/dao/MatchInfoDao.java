package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.MatchInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 赛事信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface MatchInfoDao extends BaseMapper<MatchInfoEntity> {
	
}
