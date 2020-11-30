package com.soldier.modules.business.service.impl;

import com.soldier.common.utils.RedisKeys;
import com.soldier.common.utils.RedisUtils;
import com.soldier.modules.business.entity.*;
import com.soldier.modules.business.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author soldier
 * @Date 20-9-15 下午4:30
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:基础数据
 */
@Service("basicsDataService")
public class BasicsDataServiceImpl implements BasicsDataService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CompetitionGradeService competitionGradeService;
    @Autowired
    private CompetitionRankService competitionRankService;
    @Autowired
    private GainTypeService gainTypeService;
    @Autowired
    private PapersGradeService papersGradeService;
    @Autowired
    private ProjectCategoryService projectCategoryService;
    @Autowired
    private ProjectLevelService projectLevelService;

    @Override
    public BasicsDataVo datas() {

        BasicsDataVo basicsDataVo = new BasicsDataVo();
        int index = 0;

        // 获奖级别集合
        List<CompetitionGradeEntity> competitionGradeEntities = redisUtils.getList(RedisKeys.getBasicsDataCompetitionGrade(), CompetitionGradeEntity.class);
        if (competitionGradeEntities == null || competitionGradeEntities.size() == 0) {
            index++;
            competitionGradeEntities = competitionGradeService.list();
            redisUtils.set(RedisKeys.getBasicsDataCompetitionGrade(), competitionGradeEntities, RedisUtils.NOT_EXPIRE);
        }
        basicsDataVo.setCompetitionGradeEntities(competitionGradeEntities);

        // 获奖等次集合
        List<CompetitionRankEntity> competitionRankEntities = redisUtils.getList(RedisKeys.getBasicsDataCompetitionRank(), CompetitionRankEntity.class);
        if (competitionRankEntities == null || competitionRankEntities.size() == 0) {
            index++;
            competitionRankEntities = competitionRankService.list();
            redisUtils.set(RedisKeys.getBasicsDataCompetitionRank(), competitionRankEntities, RedisUtils.NOT_EXPIRE);
        }
        basicsDataVo.setCompetitionRankEntities(competitionRankEntities);

        // 成果类型
        List<GainTypeEntity> gainTypeEntities = redisUtils.getList(RedisKeys.getBasicsDataGainType(), GainTypeEntity.class);
        if (gainTypeEntities == null || gainTypeEntities.size() == 0) {
            index++;
            gainTypeEntities = gainTypeService.list();
            redisUtils.set(RedisKeys.getBasicsDataGainType(), gainTypeEntities, RedisUtils.NOT_EXPIRE);
        }
        basicsDataVo.setGainTypeEntities(gainTypeEntities);

        // 论文等级集合
        List<PapersGradeEntity> papersGradeEntities = redisUtils.getList(RedisKeys.getBasicsDataPapersGrade(), PapersGradeEntity.class);
        if (papersGradeEntities == null || papersGradeEntities.size() == 0) {
            index++;
            papersGradeEntities = papersGradeService.list();
            redisUtils.set(RedisKeys.getBasicsDataPapersGrade(), papersGradeEntities, RedisUtils.NOT_EXPIRE);
        }
        basicsDataVo.setPapersGradeEntities(papersGradeEntities);

        // 项目类别集合
        List<ProjectCategoryEntity> projectCategoryEntities = redisUtils.getList(RedisKeys.getBasicsDataProjectCategory(), ProjectCategoryEntity.class);
        if (projectCategoryEntities == null || projectCategoryEntities.size() == 0) {
            index++;
            projectCategoryEntities = projectCategoryService.list();
            redisUtils.set(RedisKeys.getBasicsDataProjectCategory(), projectCategoryEntities, RedisUtils.NOT_EXPIRE);
        }
        basicsDataVo.setProjectCategoryEntities(projectCategoryEntities);

        // 项目级别集合
        List<ProjectLevelEntity> projectLevelEntities = redisUtils.getList(RedisKeys.getBasicsDataProjectLevel(), ProjectLevelEntity.class);
        if (projectLevelEntities == null || projectLevelEntities.size() == 0) {
            index++;
            projectLevelEntities = projectLevelService.list();
            redisUtils.set(RedisKeys.getBasicsDataProjectLevel(), projectLevelEntities, RedisUtils.NOT_EXPIRE);
        }
        basicsDataVo.setProjectLevelEntities(projectLevelEntities);

        logger.info("后台基础数据共查询mysql数据库{}次", index);
        return basicsDataVo;
    }
}
