package com.soldier.modules.business.service.impl;

import com.soldier.common.utils.DateUtils;
import com.soldier.common.utils.RedisKeys;
import com.soldier.common.utils.RedisUtils;
import com.soldier.modules.business.entity.BigDataVo;
import com.soldier.modules.business.service.*;
import com.soldier.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author soldier
 * @Date 20-8-27 上午9:57
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:系统首页数据屏展示
 */
@Service("bigDataService")
public class BigDataServiceImpl implements BigDataService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AcademicPapersService academicPapersService;
    @Autowired
    private ResearchGainService patentAchievementsService;
    @Autowired
    private ProjectCreateService projectCreateService;
    @Autowired
    private ProjectFinishService projectFinishService;
    @Autowired
    private InnovateProjectService softwareCopyrightService;
    @Autowired
    private StudentCompetitionService studentCompetitionService;
    @Autowired
    private TeacherCompetitionService teacherCompetitionService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private TeachingAchievementsService teachingAchievementsService;
    @Autowired
    private TeachingHonorService teachingHonorService;
    @Autowired
    private TeachingMaterialService teachingMaterialService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public BigDataVo allSum() {

        BigDataVo bigDataVo = new BigDataVo();
        int index = 0;
        // 总二级学院数
        Integer allCollegeSum = redisUtils.get(RedisKeys.getBigDataAllCollegeSum(), Integer.class);
        if (allCollegeSum == null) {
            index++;
            allCollegeSum = sysDeptService.queryCount(0);
            redisUtils.set(RedisKeys.getBigDataAllCollegeSum(), allCollegeSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllCollegeSum(allCollegeSum);

        // 总教研室数
        Integer allLabSum = redisUtils.get(RedisKeys.getBigDataAllLabSum(), Integer.class);
        if (allLabSum == null) {
            index++;
            allLabSum = sysDeptService.queryCount(1);
            redisUtils.set(RedisKeys.getBigDataAllLabSum(), allLabSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllLabSum(allLabSum);

        // 用户总数
        Integer allUserSum = redisUtils.get(RedisKeys.getBigDataAllUserSum(), Integer.class);
        if (allUserSum == null) {
            index++;
            allUserSum = sysUserService.count();
            redisUtils.set(RedisKeys.getBigDataAllUserSum(), allUserSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllUserSum(allUserSum);

        // 教师总数
        Integer allTeacherSum = redisUtils.get(RedisKeys.getBigDataAllTeacherSum(), Integer.class);
        if (allTeacherSum == null) {
            index++;
            allTeacherSum = teacherInfoService.count();
            redisUtils.set(RedisKeys.getBigDataAllTeacherSum(), allTeacherSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllTeacherSum(allTeacherSum);

        // 学术论文数
        Integer allAcademicPapersSum = redisUtils.get(RedisKeys.getBigDataAllAcademicPapersSum(), Integer.class);
        if (allAcademicPapersSum == null) {
            index++;
            allAcademicPapersSum = academicPapersService.count();
            redisUtils.set(RedisKeys.getBigDataAllAcademicPapersSum(), allAcademicPapersSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllAcademicPapersSum(allAcademicPapersSum);

        // 学生竞赛数
        Integer allStudentCompetitionSum = redisUtils.get(RedisKeys.getBigDataAllStudentCompetitionSum(), Integer.class);
        if (allStudentCompetitionSum == null) {
            index++;
            allStudentCompetitionSum = studentCompetitionService.count();
            redisUtils.set(RedisKeys.getBigDataAllStudentCompetitionSum(), allStudentCompetitionSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllStudentCompetitionSum(allStudentCompetitionSum);

        // 教师竞赛数
        Integer allTeacherCompetitionSum = redisUtils.get(RedisKeys.getBigDataAllTeacherCompetitionSum(), Integer.class);
        if (allTeacherCompetitionSum == null) {
            index++;
            allTeacherCompetitionSum = teacherCompetitionService.count();
            redisUtils.set(RedisKeys.getBigDataAllTeacherCompetitionSum(), allTeacherCompetitionSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllTeacherCompetitionSum(allTeacherCompetitionSum);

        // 项目立项数
        Integer allProjectCreateSum = redisUtils.get(RedisKeys.getBigDataAllProjectCreateSum(), Integer.class);
        if (allProjectCreateSum == null) {
            index++;
            allProjectCreateSum = projectCreateService.count();
            redisUtils.set(RedisKeys.getBigDataAllProjectCreateSum(), allProjectCreateSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllProjectCreateSum(allProjectCreateSum);

        // 项目结题数
        Integer allProjectFinishSum = redisUtils.get(RedisKeys.getBigDataAllProjectFinishSum(), Integer.class);
        if (allProjectFinishSum == null) {
            index++;
            allProjectFinishSum = projectFinishService.count();
            redisUtils.set(RedisKeys.getBigDataAllProjectFinishSum(), allProjectFinishSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllProjectFinishSum(allProjectFinishSum);

        // 科研成果数
        Integer allResearchGainSum = redisUtils.get(RedisKeys.getBigDataAllInnovateProjectSum(), Integer.class);
        if (allResearchGainSum == null) {
            index++;
            allResearchGainSum = patentAchievementsService.count();
            redisUtils.set(RedisKeys.getBigDataAllInnovateProjectSum(), allResearchGainSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllResearchGainSum(allResearchGainSum);

        // 大创项目数
        Integer allInnovateProjectSum = redisUtils.get(RedisKeys.getBigDataAllResearchGainSum(), Integer.class);
        if (allInnovateProjectSum == null) {
            index++;
            allInnovateProjectSum = softwareCopyrightService.count();
            redisUtils.set(RedisKeys.getBigDataAllResearchGainSum(), allInnovateProjectSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllInnovateProjectSum(allInnovateProjectSum);

        // 自编教材管理数
        Integer allTeachingMaterialSum = redisUtils.get(RedisKeys.getBigDataAllTeachingMaterialSum(), Integer.class);
        if (allTeachingMaterialSum == null) {
            index++;
            allTeachingMaterialSum = teachingMaterialService.count();
            redisUtils.set(RedisKeys.getBigDataAllTeachingMaterialSum(), allTeachingMaterialSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllTeachingMaterialSum(allTeachingMaterialSum);

        // 教学成果数
        Integer allTeachingAchievementsSum = redisUtils.get(RedisKeys.getBigDataAllTeachingAchievementsSum(), Integer.class);
        if (allTeachingAchievementsSum == null) {
            index++;
            allTeachingAchievementsSum = teachingAchievementsService.count();
            redisUtils.set(RedisKeys.getBigDataAllTeachingAchievementsSum(), allTeachingAchievementsSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllTeachingAchievementsSum(allTeachingAchievementsSum);

        // 教学荣誉数
        Integer allTeachingHonorSum = redisUtils.get(RedisKeys.getBigDataAllTeachingHonorSum(), Integer.class);
        if (allTeachingHonorSum == null) {
            index++;
            allTeachingHonorSum = teachingHonorService.count();
            redisUtils.set(RedisKeys.getBigDataAllTeachingHonorSum(), allTeachingHonorSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllTeachingHonorSum(allTeachingHonorSum);

        // 业绩总数
        Integer allBusinessSum = redisUtils.get(RedisKeys.getBigDataAllBusinessSum(), Integer.class);
        if (allBusinessSum == null) {
            allBusinessSum = bigDataVo.getBusinessAccumulation();
            redisUtils.set(RedisKeys.getBigDataAllBusinessSum(), allBusinessSum, RedisUtils.NOT_EXPIRE);
        }
        bigDataVo.setAllBusinessSum(allBusinessSum);

        // 当月新增数，如2020-09
        String now = DateUtils.format(new Date(), "yyyy-MM");
        Integer businessNewAddMonthSum = redisUtils.get(RedisKeys.getBigDataBusinessNewAddMonthSum(now), Integer.class);
        bigDataVo.setBusinessNewAddMonthSum(businessNewAddMonthSum == null ? 0 : businessNewAddMonthSum);

        logger.info("系统首页数据屏展示共查询mysql数据库{}次", index);
        return bigDataVo;
    }
}
