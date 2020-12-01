package com.soldier.modules.business.service.impl;

import com.soldier.common.utils.OSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.SysAttachDao;
import com.soldier.modules.business.entity.SysAttachEntity;
import com.soldier.modules.business.service.SysAttachService;
import org.springframework.transaction.annotation.Transactional;


@Service("sysAttachService")
public class SysAttachServiceImpl extends ServiceImpl<SysAttachDao, SysAttachEntity> implements SysAttachService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysAttachEntity> page = this.page(
                new Query<SysAttachEntity>().getPage(params),
                new QueryWrapper<SysAttachEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {

        // 第一步：先查询出要删除的附件信息
        QueryWrapper<SysAttachEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("attach_id", idList);
        List<SysAttachEntity> sysAttachEntities = super.list(queryWrapper);

        // 第二步：删除文件
        deleteFolderFiles(sysAttachEntities);

        // 第三步：删除数据库记录
        return super.removeByIds(idList);
    }

    @Override
    public List<SysAttachEntity> queryByFunctionId(String functionId) {
        return baseMapper.queryByFunctionId(functionId);
    }

    @Override
    @Transactional
    public void removeByFunctionId(String functionId, boolean removeSystemFile) {

        QueryWrapper<SysAttachEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("function_id", functionId);

        if (removeSystemFile) {
            // 查询出要删除的附件信息，并删除文件
            List<SysAttachEntity> sysAttachEntities = super.list(queryWrapper);
            deleteFolderFiles(sysAttachEntities);
        }

        // 删除数据库记录
        super.remove(queryWrapper);
    }

    /**
     * 删除文件夹中的文件
     * @param sysAttachEntities
     */
    private void deleteFolderFiles(List<SysAttachEntity> sysAttachEntities) {
        File file = null;
        String path = null;
        for (SysAttachEntity sysAttachEntity: sysAttachEntities) {
            path = sysAttachEntity.getAttachPath();
            OSSUtils.deleteFile(path);
            logger.info("删除了文件，地址为：{}", path);
        }
    }
}