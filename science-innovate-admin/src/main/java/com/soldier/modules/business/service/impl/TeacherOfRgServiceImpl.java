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

import com.soldier.modules.business.dao.TeacherOfRgDao;
import com.soldier.modules.business.entity.TeacherOfRgEntity;
import com.soldier.modules.business.service.TeacherOfRgService;

@Service("teacherOfRgService")
public class TeacherOfRgServiceImpl extends ServiceImpl<TeacherOfRgDao, TeacherOfRgEntity> implements TeacherOfRgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfRgEntity> page = this.page(
                new Query<TeacherOfRgEntity>().getPage(params),
                new QueryWrapper<TeacherOfRgEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long queryPersonId(Serializable id) {

        QueryWrapper<TeacherOfRgEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("rg_id", id);
        queryWrapper.eq("type", 1);

        TeacherOfRgEntity entity = super.getOne(queryWrapper);

        return entity == null ? null : entity.getTeaId();
    }

    @Override
    public List<Long> queryChoiceTeaIdList(Serializable id) {
        return baseMapper.queryChoiceTeaIdList(id);
    }

    @Override
    public void removeByRgId(Long rgId) {
        QueryWrapper<TeacherOfRgEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("rg_id", rgId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long rgId) {
        return baseMapper.queryPersonInfo(rgId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long rgId) {
        return baseMapper.queryMemberInfo(rgId);
    }

}