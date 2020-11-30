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

import com.soldier.modules.business.dao.TeacherOfTaDao;
import com.soldier.modules.business.entity.TeacherOfTaEntity;
import com.soldier.modules.business.service.TeacherOfTaService;


@Service("teacherOfTaService")
public class TeacherOfTaServiceImpl extends ServiceImpl<TeacherOfTaDao, TeacherOfTaEntity> implements TeacherOfTaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfTaEntity> page = this.page(
                new Query<TeacherOfTaEntity>().getPage(params),
                new QueryWrapper<TeacherOfTaEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long queryPersonId(Serializable id) {

        QueryWrapper<TeacherOfTaEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("ta_id", id);
        queryWrapper.eq("type", 1);

        TeacherOfTaEntity entity = super.getOne(queryWrapper);

        return entity == null ? null : entity.getTeaId();
    }

    @Override
    public List<Long> queryChoiceTeaIdList(Serializable taId) {
        return baseMapper.queryChoiceTeaIdList(taId);
    }

    @Override
    public void removeByTaId(Long taId) {
        QueryWrapper<TeacherOfTaEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("ta_id", taId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long taId) {
        return baseMapper.queryPersonInfo(taId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long taId) {
        return baseMapper.queryMemberInfo(taId);
    }

}