package com.soldier.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.ProjectCategoryDao;
import com.soldier.modules.business.entity.ProjectCategoryEntity;
import com.soldier.modules.business.service.ProjectCategoryService;


@Service("projectCategoryService")
public class ProjectCategoryServiceImpl extends ServiceImpl<ProjectCategoryDao, ProjectCategoryEntity> implements ProjectCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProjectCategoryEntity> page = this.page(
                new Query<ProjectCategoryEntity>().getPage(params),
                new QueryWrapper<ProjectCategoryEntity>()
        );

        return new PageUtils(page);
    }

}