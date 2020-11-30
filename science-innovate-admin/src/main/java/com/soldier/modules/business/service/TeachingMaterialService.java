package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeachingMaterialEntity;

import java.util.List;
import java.util.Map;

/**
 * 教材表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeachingMaterialService extends IService<TeachingMaterialEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TeachingMaterialEntity> queryList(Map<String, Object> params);

    TeachingMaterialEntity details(Long materialId);
}

