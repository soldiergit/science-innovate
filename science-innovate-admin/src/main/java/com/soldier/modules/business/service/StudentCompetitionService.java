package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.StudentCompetitionEntity;

import java.util.List;
import java.util.Map;

/**
 * 学生竞赛
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface StudentCompetitionService extends IService<StudentCompetitionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<StudentCompetitionEntity> queryList(Map<String, Object> params);

    StudentCompetitionEntity details(Long scId);
}

