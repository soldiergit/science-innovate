package com.soldier.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.ProjectLevelDao;
import com.soldier.modules.business.entity.ProjectLevelEntity;
import com.soldier.modules.business.service.ProjectLevelService;


@Service("projectLevelService")
public class ProjectLevelServiceImpl extends ServiceImpl<ProjectLevelDao, ProjectLevelEntity> implements ProjectLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProjectLevelEntity> page = this.page(
                new Query<ProjectLevelEntity>().getPage(params),
                new QueryWrapper<ProjectLevelEntity>()
        );

        return new PageUtils(page);
    }

}