package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.CompetitionGradeEntity;

import java.util.Map;

/**
 * 竞赛获奖级别
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
public interface CompetitionGradeService extends IService<CompetitionGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

