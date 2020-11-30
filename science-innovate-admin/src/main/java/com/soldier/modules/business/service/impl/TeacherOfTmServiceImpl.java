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

import com.soldier.modules.business.dao.TeacherOfTmDao;
import com.soldier.modules.business.entity.TeacherOfTmEntity;
import com.soldier.modules.business.service.TeacherOfTmService;


@Service("teacherOfTmService")
public class TeacherOfTmServiceImpl extends ServiceImpl<TeacherOfTmDao, TeacherOfTmEntity> implements TeacherOfTmService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfTmEntity> page = this.page(
                new Query<TeacherOfTmEntity>().getPage(params),
                new QueryWrapper<TeacherOfTmEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long queryPersonId(Serializable id) {

        QueryWrapper<TeacherOfTmEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("tm_id", id);
        queryWrapper.eq("type", 1);

        TeacherOfTmEntity entity = super.getOne(queryWrapper);

        return entity == null ? null : entity.getTeaId();
    }

    @Override
    public List<Long> queryChoiceTeaIdList(Serializable tmId) {
        return baseMapper.queryChoiceTeaIdList(tmId);
    }

    @Override
    public void removeByTaId(Long tmId) {
        QueryWrapper<TeacherOfTmEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("tm_id", tmId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long tmId) {
        return baseMapper.queryPersonInfo(tmId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long tmId) {
        return baseMapper.queryMemberInfo(tmId);
    }

}