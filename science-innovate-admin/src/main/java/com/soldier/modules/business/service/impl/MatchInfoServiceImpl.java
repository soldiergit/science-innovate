package com.soldier.modules.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.soldier.modules.business.entity.SysAttachEntity;
import com.soldier.modules.business.service.SysAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.MatchInfoDao;
import com.soldier.modules.business.entity.MatchInfoEntity;
import com.soldier.modules.business.service.MatchInfoService;
import org.springframework.transaction.annotation.Transactional;

@Service("matchInfoService")
public class MatchInfoServiceImpl extends ServiceImpl<MatchInfoDao, MatchInfoEntity> implements MatchInfoService {

    @Autowired
    private SysAttachService sysAttachService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper queryWrapper = new QueryWrapper<MatchInfoEntity>();

        // 赛事等级
        Object grade = params.get("grade");
        // 赛事类型
        Object type = params.get("type");
        if (ObjectUtils.isNotEmpty(grade)) queryWrapper.eq("grade", grade);
        if (ObjectUtils.isNotEmpty(type)) queryWrapper.eq("type", grade);


        IPage<MatchInfoEntity> page = this.page(
                new Query<MatchInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<MatchInfoEntity> select(Integer type) {

        QueryWrapper queryWrapper = new QueryWrapper<MatchInfoEntity>();

        // 设置查询字段
        queryWrapper.select("match_id", "name");
        queryWrapper.eq("type", type);

        return super.list(queryWrapper);
    }

    /**
     * 重写保存方法，因为还要保存赛事附件
     */
    @Override
    @Transactional
    public boolean save(MatchInfoEntity entity) {

        // 第一步：保存项目信息
        super.save(entity);

        // 第二步：保存附件信息
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId("MATCH-" + entity.getMatchId());
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        return false;
    }

    /**
     * 重写查询方法，因为还要查询赛事附件
     */
    @Override
    @Transactional
    public MatchInfoEntity getById(Serializable id) {

        // 第一步：获取基本数据
        MatchInfoEntity matchInfoEntity = super.getById(id);

        // 第二步：查询项目所有附件
        matchInfoEntity.setSysAttachEntityList(sysAttachService.queryByFunctionId("MATCH-"+id));

        return matchInfoEntity;
    }

    /**
     * 重写更新方法，因为还要更新赛事附件
     */
    @Override
    @Transactional
    public boolean updateById(MatchInfoEntity entity) {

        // 第一步：更新项目信息
        super.updateById(entity);

        // 第二步：删除项目相关附件信息
        String functionId = "MATCH-" + entity.getMatchId();
        sysAttachService.removeByFunctionId(functionId, false);

        // 第三步：新增附件
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId(functionId);
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        return true;
    }

    /**
     * 重写更新方法，因为还要删除项目附件
     */
    @Override
    @Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {

        // 删除附件
        for (Serializable id : idList) {
            sysAttachService.removeByFunctionId("MATCH-"+id, true);
        }

        // 删除项目信息
        return super.removeByIds(idList);
    }
}