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

import com.soldier.modules.business.dao.TeacherOfIpDao;
import com.soldier.modules.business.entity.TeacherOfIpEntity;
import com.soldier.modules.business.service.TeacherOfIpService;

@Service("teacherOfIpService")
public class TeacherOfIpServiceImpl extends ServiceImpl<TeacherOfIpDao, TeacherOfIpEntity> implements TeacherOfIpService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfIpEntity> page = this.page(
                new Query<TeacherOfIpEntity>().getPage(params),
                new QueryWrapper<TeacherOfIpEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public TeacherOfIpEntity queryFirstTeacherInfo(Serializable ipId) {
        QueryWrapper queryWrapper = new QueryWrapper<TeacherOfIpEntity>();

        queryWrapper.eq("ip_id", ipId);
        queryWrapper.eq("type", 1);

        queryWrapper.select("tea_id");

        return getOne(queryWrapper);
    }

    @Override
    public List<Long> queryOtherTeacherIdList(Serializable ipId) {
        return null;
    }

    @Override
    public void removeByIpId(Long ipId) {
        QueryWrapper queryWrapper = new QueryWrapper<TeacherOfIpEntity>();

        queryWrapper.eq("ip_id", ipId);

        super.remove(queryWrapper);
    }

    @Override
    public TeacherInfoEntity queryPersonInfo(Long ipId) {
        return baseMapper.queryPersonInfo(ipId);
    }

    @Override
    public List<TeacherInfoEntity> queryMemberInfo(Long ipId) {
        return baseMapper.queryMemberInfo(ipId);
    }
}