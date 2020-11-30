package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.ProjectCreateEntity;

import java.util.List;
import java.util.Map;

/**
 * 教师项目立项
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface ProjectCreateService extends IService<ProjectCreateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProjectCreateEntity> queryList(Map<String, Object> params);

    /**
     * 教师以负责人或成员的身份查询未结题项目
     */
    List<ProjectCreateEntity> queryMyProject(Long userId);

    /**
     * 改变立项项目状态：0-未结题   1-已结题
     */
    void apply(Long pcId, int type);

    ProjectCreateEntity details(Long pcId);
}

