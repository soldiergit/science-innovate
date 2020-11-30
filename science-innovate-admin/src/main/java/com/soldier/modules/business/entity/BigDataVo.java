package com.soldier.modules.business.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author soldier
 * @Date 20-8-27 上午9:48
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:系统首页数据屏展示
 */
@Data
public class BigDataVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总二级学院数
     */
    private Integer allCollegeSum;

    /**
     * 总教研室数
     */
    private Integer allLabSum;

    /**
     * 业绩总数
     */
    private Integer allBusinessSum;

    /**
     * 当月新增数
     */
    private Integer businessNewAddMonthSum;

    /**
     * 用户总数
     */
    private Integer allUserSum;

    /**
     * 教师总数
     */
    private Integer allTeacherSum;

    /**
     * 学术论文数
     */
    private Integer allAcademicPapersSum;

    /**
     * 学生竞赛数
     */
    private Integer allStudentCompetitionSum;

    /**
     * 教师竞赛数
     */
    private Integer allTeacherCompetitionSum;

    /**
     * 项目立项数
     */
    private Integer allProjectCreateSum;

    /**
     * 项目结题数
     */
    private Integer allProjectFinishSum;

    /**
     * 科研成果数
     */
    private Integer allResearchGainSum;

    /**
     * 大创项目数
     */
    private Integer allInnovateProjectSum;

    /**
     * 自编教材管理数
     */
    private Integer allTeachingMaterialSum;

    /**
     * 教学成果数
     */
    private Integer allTeachingAchievementsSum;

    /**
     * 教学荣誉数
     */
    private Integer allTeachingHonorSum;

    public Integer getBusinessAccumulation() {
        return allAcademicPapersSum + allStudentCompetitionSum + allTeacherCompetitionSum + allProjectCreateSum +
               allProjectFinishSum + allResearchGainSum + allInnovateProjectSum + allTeachingMaterialSum +
               allTeachingAchievementsSum + allTeachingHonorSum;
    }
}
