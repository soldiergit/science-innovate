package com.soldier.modules.business.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author soldier
 * @Date 20-9-15 下午4:23
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:基础数据vo对象
 */
@Data
public class BasicsDataVo implements Serializable {

    /**
     * 获奖级别集合
     */
    private List<CompetitionGradeEntity> competitionGradeEntities;

    /**
     * 获奖等次集合
     */
    private List<CompetitionRankEntity> competitionRankEntities;

    /**
     * 成果类型
     */
    private List<GainTypeEntity> gainTypeEntities;

    /**
     * 论文等级集合
     */
    private List<PapersGradeEntity> papersGradeEntities;

    /**
     * 项目类别集合
     */
    private List<ProjectCategoryEntity> projectCategoryEntities;

    /**
     * 项目级别集合
     */
    private List<ProjectLevelEntity> projectLevelEntities;

}
