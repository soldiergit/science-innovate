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

import com.soldier.modules.business.dao.TeacherOfApDao;
import com.soldier.modules.business.entity.TeacherOfApEntity;
import com.soldier.modules.business.service.TeacherOfApService;


@Service("teacherOfApService")
public class TeacherOfApServiceImpl extends ServiceImpl<TeacherOfApDao, TeacherOfApEntity> implements TeacherOfApService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfApEntity> page = this.page(
                new Query<TeacherOfApEntity>().getPage(params),
                new QueryWrapper<TeacherOfApEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long queryPersonId(Serializable id) {

        QueryWrapper<TeacherOfApEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("ap_id", id);
        queryWrapper.eq("type", 1);

        TeacherOfApEntity entity = super.getOne(queryWrapper);

        return entity == null ? null : entity.getTeaId();
    }

    @Override
    public List<Long> queryChoiceTeaIdList(Serializable apId) {
        return baseMapper.queryChoiceTeaIdList(apId);
    }

    @Override
    public void removeByApId(Long apId) {

        QueryWrapper<TeacherOfApEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("ap_id", apId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long id) {
        return baseMapper.queryPersonInfo(id);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long id) {
        return baseMapper.queryMemberInfo(id);
    }

}