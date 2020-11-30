package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.TeacherCompetitionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教师竞赛
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface TeacherCompetitionDao extends BaseMapper<TeacherCompetitionEntity> {

    Integer queryCountPage(Map<String, Object> params);

    List<TeacherCompetitionEntity> queryPage(Map<String, Object> params);
}
