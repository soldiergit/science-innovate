package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.AcademicPapersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 学术论文
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface AcademicPapersDao extends BaseMapper<AcademicPapersEntity> {

    /**
     * 查询记录数
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<AcademicPapersEntity> queryPage(Map<String, Object> params);
	
}
