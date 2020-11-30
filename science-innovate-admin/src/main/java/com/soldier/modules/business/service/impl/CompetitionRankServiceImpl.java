package com.soldier.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.CompetitionRankDao;
import com.soldier.modules.business.entity.CompetitionRankEntity;
import com.soldier.modules.business.service.CompetitionRankService;


@Service("competitionRankService")
public class CompetitionRankServiceImpl extends ServiceImpl<CompetitionRankDao, CompetitionRankEntity> implements CompetitionRankService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CompetitionRankEntity> page = this.page(
                new Query<CompetitionRankEntity>().getPage(params),
                new QueryWrapper<CompetitionRankEntity>()
        );

        return new PageUtils(page);
    }

}