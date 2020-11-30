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

import com.soldier.modules.business.dao.TeacherOfProcDao;
import com.soldier.modules.business.entity.TeacherOfProcEntity;
import com.soldier.modules.business.service.TeacherOfProcService;


@Service("teacherOfProcService")
public class TeacherOfProcServiceImpl extends ServiceImpl<TeacherOfProcDao, TeacherOfProcEntity> implements TeacherOfProcService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfProcEntity> page = this.page(
                new Query<TeacherOfProcEntity>().getPage(params),
                new QueryWrapper<TeacherOfProcEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long queryPersonId(Serializable id) {

        QueryWrapper<TeacherOfProcEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("pc_id", id);
        queryWrapper.eq("type", 1);

        TeacherOfProcEntity entity = super.getOne(queryWrapper);

        return entity == null ? null : entity.getTeaId();
    }

    @Override
    public List<Long> queryChoiceTeaIdList(Serializable id) {
        return baseMapper.queryChoiceTeaIdList(id);
    }

    @Override
    public void removeByPcId(Long pcId) {
        QueryWrapper<TeacherOfProcEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("pc_id", pcId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long pcId) {
        return baseMapper.queryPersonInfo(pcId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long pcId) {
        return baseMapper.queryMemberInfo(pcId);
    }

}