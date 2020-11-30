package com.soldier.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.PapersGradeDao;
import com.soldier.modules.business.entity.PapersGradeEntity;
import com.soldier.modules.business.service.PapersGradeService;


@Service("papersGradeService")
public class PapersGradeServiceImpl extends ServiceImpl<PapersGradeDao, PapersGradeEntity> implements PapersGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PapersGradeEntity> page = this.page(
                new Query<PapersGradeEntity>().getPage(params),
                new QueryWrapper<PapersGradeEntity>()
        );

        return new PageUtils(page);
    }

}