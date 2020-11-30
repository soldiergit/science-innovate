package com.soldier.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.GainTypeDao;
import com.soldier.modules.business.entity.GainTypeEntity;
import com.soldier.modules.business.service.GainTypeService;


@Service("gainTypeService")
public class GainTypeServiceImpl extends ServiceImpl<GainTypeDao, GainTypeEntity> implements GainTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GainTypeEntity> page = this.page(
                new Query<GainTypeEntity>().getPage(params),
                new QueryWrapper<GainTypeEntity>()
        );

        return new PageUtils(page);
    }

}