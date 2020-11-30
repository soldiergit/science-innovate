package com.soldier.modules.business.service.impl;

import com.soldier.modules.business.entity.MatchInfoEntity;
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

import com.soldier.modules.business.dao.TeacherOfTeacDao;
import com.soldier.modules.business.entity.TeacherOfTeacEntity;
import com.soldier.modules.business.service.TeacherOfTeacService;


@Service("teacherOfTeacService")
public class TeacherOfTeacServiceImpl extends ServiceImpl<TeacherOfTeacDao, TeacherOfTeacEntity> implements TeacherOfTeacService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfTeacEntity> page = this.page(
                new Query<TeacherOfTeacEntity>().getPage(params),
                new QueryWrapper<TeacherOfTeacEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public TeacherOfTeacEntity queryFirstTeacherInfo(Serializable tcId) {
        QueryWrapper queryWrapper = new QueryWrapper<TeacherOfTeacEntity>();

        queryWrapper.eq("tc_id", tcId);
        queryWrapper.eq("type", 1);

        queryWrapper.select("tea_id", "match_id");

        return getOne(queryWrapper);
    }

    @Override
    public List<Long> queryOtherTeacherIdList(Serializable tcId) {
        return baseMapper.queryOtherTeacherIdList(tcId);
    }

    @Override
    public void removeByTcId(Long tcId) {
        QueryWrapper queryWrapper = new QueryWrapper<TeacherOfTeacEntity>();

        queryWrapper.eq("tc_id", tcId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long tcId) {
        return baseMapper.queryPersonInfo(tcId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long tcId) {
        return baseMapper.queryMemberInfo(tcId);
    }

    @Override
    public MatchInfoEntity queryMatch(Long tcId) {
        return baseMapper.queryMatch(tcId);
    }

}