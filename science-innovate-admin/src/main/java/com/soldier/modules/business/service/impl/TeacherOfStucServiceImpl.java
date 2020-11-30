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

import com.soldier.modules.business.dao.TeacherOfStucDao;
import com.soldier.modules.business.entity.TeacherOfStucEntity;
import com.soldier.modules.business.service.TeacherOfStucService;


@Service("teacherOfStucService")
public class TeacherOfStucServiceImpl extends ServiceImpl<TeacherOfStucDao, TeacherOfStucEntity> implements TeacherOfStucService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfStucEntity> page = this.page(
                new Query<TeacherOfStucEntity>().getPage(params),
                new QueryWrapper<TeacherOfStucEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public TeacherOfStucEntity queryFirstTeacherInfo(Serializable scId) {
        QueryWrapper queryWrapper = new QueryWrapper<TeacherOfStucEntity>();

        queryWrapper.eq("sc_id", scId);
        queryWrapper.eq("type", 1);

        queryWrapper.select("tea_id", "match_id");

        return getOne(queryWrapper);
    }

    @Override
    public List<Long> queryOtherTeacherIdList(Serializable scId) {
        return baseMapper.queryOtherTeacherIdList(scId);
    }

    @Override
    public void removeByScId(Long scId) {
        QueryWrapper queryWrapper = new QueryWrapper<TeacherOfStucEntity>();

        queryWrapper.eq("sc_id", scId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long scId) {
        return baseMapper.queryPersonInfo(scId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long scId) {
        return baseMapper.queryMemberInfo(scId);
    }

    @Override
    public MatchInfoEntity queryMatch(Long scId) {
        return baseMapper.queryMatch(scId);
    }

}