package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeacherCompetitionEntity;

import java.util.List;
import java.util.Map;

/**
 * 教师竞赛
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeacherCompetitionService extends IService<TeacherCompetitionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TeacherCompetitionEntity> queryList(Map<String, Object> params);

    TeacherCompetitionEntity details(Long tcId);
}

