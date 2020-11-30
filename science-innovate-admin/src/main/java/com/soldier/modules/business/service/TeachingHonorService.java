package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.TeachingHonorEntity;

import java.util.List;
import java.util.Map;

/**
 * 教学荣誉表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface TeachingHonorService extends IService<TeachingHonorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TeachingHonorEntity> queryList(Map<String, Object> params);

    TeachingHonorEntity details(Long thId);
}

