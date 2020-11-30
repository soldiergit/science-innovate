package com.soldier.modules.business.service.impl;

import com.soldier.modules.business.entity.TeacherInfoEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.TeacherOfThDao;
import com.soldier.modules.business.entity.TeacherOfThEntity;
import com.soldier.modules.business.service.TeacherOfThService;


@Service("teacherOfThService")
public class TeacherOfThServiceImpl extends ServiceImpl<TeacherOfThDao, TeacherOfThEntity> implements TeacherOfThService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfThEntity> page = this.page(
                new Query<TeacherOfThEntity>().getPage(params),
                new QueryWrapper<TeacherOfThEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long queryPersonId(Serializable id) {

        QueryWrapper<TeacherOfThEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("th_id", id);
        queryWrapper.eq("type", 1);

        TeacherOfThEntity entity = super.getOne(queryWrapper);

        return entity == null ? null : entity.getTeaId();
    }

    @Override
    public List<Long> queryChoiceTeaIdList(Serializable thId) {
        return baseMapper.queryChoiceTeaIdList(thId);
    }

    @Override
    public void removeByTaId(Long thId) {
        QueryWrapper<TeacherOfThEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("th_id", thId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long thId) {
        return baseMapper.queryPersonInfo(thId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long thId) {
        return baseMapper.queryMemberInfo(thId);
    }

}