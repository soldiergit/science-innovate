package com.soldier.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.CompetitionGradeDao;
import com.soldier.modules.business.entity.CompetitionGradeEntity;
import com.soldier.modules.business.service.CompetitionGradeService;


@Service("competitionGradeService")
public class CompetitionGradeServiceImpl extends ServiceImpl<CompetitionGradeDao, CompetitionGradeEntity> implements CompetitionGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CompetitionGradeEntity> page = this.page(
                new Query<CompetitionGradeEntity>().getPage(params),
                new QueryWrapper<CompetitionGradeEntity>()
        );

        return new PageUtils(page);
    }

}