package com.soldier.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark sunlightcs@gmail.com
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "science_innovate_sys_config:" + key;
    }

    /**
     * 菜单相关
     */
    // 菜单相关的公共key, *号 必须要加，否则无法模糊查询
    public static String getSysMenuPublicKey() {
        return "science_innovate_sys_menu_*";
    }
    // 所有菜单列表
    public static String getSysMenuAllList() {
        return "science_innovate_sys_menu_all_list";
    }
    // 不包含按钮的菜单列表
    public static String getSysMenuNotButtonList() {
        return "science_innovate_sys_menu_not_button_list";
    }
    // 用户菜单列表
    public static String getSysMenuUserMenuList(Object userId) {
        return "science_innovate_sys_menu_user_menu_list_userId:"+userId;
    }
    // 用户的所有菜单ID
    public static String getSysMenuUserMenuIdList(Object userId) {
        return "science_innovate_sys_menu_user_menu_id_list_userId:"+userId;
    }

    /**
     * 部门相关
     */
    // 部门相关的公共key, *号 必须要加，否则无法模糊查询
    public static String getSysDeptPublicKey() {
        return "science_innovate_sys_dept_*";
    }
    // 所有菜单列表
    public static String getSysDeptUserTreeList(Object userId) {
        return "science_innovate_sys_dept_user_tree_list_userId:"+userId;
    }
    // 所有二级学院（一级部门）
    public static String getSysDeptAllCollegeList() {
        return "science_innovate_sys_dept_all_college_list";
    }

    /**
     * 教师相关
     */
    // 教师相关的公共key, *号 必须要加，否则无法模糊查询
    public static String getTeacherPublicKey() {
        return "science_innovate_teacher_*";
    }
    public static String getAllTeacherSelect() {
        return "science_innovate_all_teacher_select";
    }
    public static String getTeacherInfoAdminSelect() {
        return "science_innovate_teacher_info_admin_select";
    }
    public static String getTeacherInfoChoiceList(Object deptId, Object userId) {
        if (userId == null || userId =="") userId = -666666;
        return "science_innovate_teacher_info_list_deptId:"+deptId+"_userId:"+userId;
    }
    public static String getTeacherIdChoiceList(Object deptId, Object userId) {
        if (userId == null || userId =="") userId = -666666;
        return "science_innovate_teacher_ids_deptId:"+deptId+"_userId:"+userId;
    }

    /**
     * 数据屏展示
     */
    // 总二级学院数
    public static String getBigDataAllCollegeSum() {return "science_innovate_big_data_all_college_sum";}
    // 总教研室数
    public static String getBigDataAllLabSum() {return "science_innovate_big_data_all_lab_sum";}
    // 业绩总数
    public static String getBigDataAllBusinessSum() {return "science_innovate_big_data_all_business_sum";}
    // 当月新增数，如2020-09
    public static String getBigDataBusinessNewAddMonthSum(String years) {return "science_innovate_big_data_business_new_add_month("+years+")_sum";}
    // 用户总数
    public static String getBigDataAllUserSum() {return "science_innovate_big_data_all_user_sum";}
    // 教师总数
    public static String getBigDataAllTeacherSum() {return "science_innovate_big_data_all_teacher_sum";}
    // 学术论文数
    public static String getBigDataAllAcademicPapersSum() {return "science_innovate_big_data_all_academic_papers_sum";}
    // 学生竞赛数
    public static String getBigDataAllStudentCompetitionSum() {return "science_innovate_big_data_all_student_competition_sum";}
    // 教师竞赛数
    public static String getBigDataAllTeacherCompetitionSum() {return "science_innovate_big_data_all_teacher_competition_sum";}
    // 项目立项数
    public static String getBigDataAllProjectCreateSum() {return "science_innovate_big_data_all_project_create_sum";}
    // 项目结题数
    public static String getBigDataAllProjectFinishSum() {return "science_innovate_big_data_all_project_finish_sum";}
    // 科研成果数
    public static String getBigDataAllResearchGainSum() {return "science_innovate_big_data_all_research_gain_sum";}
    // 大创项目数
    public static String getBigDataAllInnovateProjectSum() {return "science_innovate_big_data_all_innovate_project_sum";}
    // 自编教材管理数
    public static String getBigDataAllTeachingMaterialSum() {return "science_innovate_big_data_all_teaching_material_sum";}
    // 教学成果数
    public static String getBigDataAllTeachingAchievementsSum() {return "science_innovate_big_data_all_teaching_achievements_sum";}
    // 教学荣誉数
    public static String getBigDataAllTeachingHonorSum() {return "science_innovate_big_data_all_teaching_honor_sum";}

    /**
     * 基础数据
     */
    // 获奖级别集合
    public static String getBasicsDataCompetitionGrade() {return "science_innovate_basics_data_competition_grade";}
    // 获奖等次集合
    public static String getBasicsDataCompetitionRank() {return "science_innovate_basics_data_competition_rank";}
    // 成果类型
    public static String getBasicsDataGainType() {return "science_innovate_basics_data_gain_type";}
    // 论文等级集合
    public static String getBasicsDataPapersGrade() {return "science_innovate_basics_data_papers_grade";}
    // 项目类别集合
    public static String getBasicsDataProjectCategory() {return "science_innovate_basics_data_project_category";}
    // 项目级别集合
    public static String getBasicsDataProjectLevel() {return "science_innovate_basics_data_Data_project_level";}

    /**
     * 用户相关
     */
    public static String getUserToken() {
        return "science_innovate_user_token";
    }
}
