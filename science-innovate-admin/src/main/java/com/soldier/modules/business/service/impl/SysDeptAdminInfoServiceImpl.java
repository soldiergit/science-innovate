package com.soldier.modules.business.service.impl;

import com.soldier.common.validator.ValidatorUtils;
import com.soldier.common.validator.group.AddGroup;
import com.soldier.modules.sys.entity.SysUserEntity;
import com.soldier.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.SysDeptAdminInfoDao;
import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;
import com.soldier.modules.business.service.SysDeptAdminInfoService;
import org.springframework.transaction.annotation.Transactional;

import static com.soldier.common.utils.ShiroUtils.getUserId;


@Service("sysDeptAdminInfoService")
public class SysDeptAdminInfoServiceImpl extends ServiceImpl<SysDeptAdminInfoDao, SysDeptAdminInfoEntity> implements SysDeptAdminInfoService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDeptAdminInfoEntity> page = this.page(
                new Query<SysDeptAdminInfoEntity>().getPage(params),
                new QueryWrapper<SysDeptAdminInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SysDeptAdminInfoEntity getByDeptId(Long deptId) {
        // 先查询中间表信息
        SysDeptAdminInfoEntity byDeptId = baseMapper.getByDeptId(deptId);

        // 再查询用户信息
        if (byDeptId != null && byDeptId.getUserId() != null) {
            byDeptId.setSysUserEntity(sysUserService.getById(byDeptId.getUserId()));
        }
        return byDeptId;
    }

    @Override
    public SysDeptAdminInfoEntity getByUserId(Long userId) {
        return baseMapper.getByUserId(userId);
    }

    @Override
    public Long getDeptIdByUserId(Long userId) {

        QueryWrapper queryWrapper = new QueryWrapper<SysDeptAdminInfoEntity>();

        queryWrapper.select("dept_id");
        queryWrapper.eq("user_id", userId);

        SysDeptAdminInfoEntity sysDeptAdminInfoEntity = super.getOne(queryWrapper);

        return sysDeptAdminInfoEntity == null ? null : sysDeptAdminInfoEntity.getDeptId();
    }

    /**
     * 重写方法：根据id查询 因为还要获取用户信息
     */
    @Override
    @Transactional
    public SysDeptAdminInfoEntity getById(Serializable id) {
        // 先查询中间表信息
        SysDeptAdminInfoEntity sysDeptAdminInfoEntity = super.getById(id);

        // 再查询用户信息
        if (sysDeptAdminInfoEntity != null && sysDeptAdminInfoEntity.getUserId() != null) {
            sysDeptAdminInfoEntity.setSysUserEntity(sysUserService.getById(sysDeptAdminInfoEntity.getUserId()));
        }
        return sysDeptAdminInfoEntity;
    }

    /**
     * 重写方法：保存 因为还要保存用户信息
     */
    @Override
    @Transactional
    public boolean save(SysDeptAdminInfoEntity entity) {
        // 先保存用户信息
        SysUserEntity user = entity.getSysUserEntity();
        ValidatorUtils.validateEntity(user, AddGroup.class);
        sysUserService.saveUser(user);

        // 再保存中间表信息
        entity.setUserId(user.getUserId());
        return super.save(entity);
    }
}