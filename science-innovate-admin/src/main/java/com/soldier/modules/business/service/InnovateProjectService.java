package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.InnovateProjectEntity;

import java.util.List;
import java.util.Map;

/**
 * 大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
public interface InnovateProjectService extends IService<InnovateProjectEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<InnovateProjectEntity> queryList(Map<String, Object> params);

    InnovateProjectEntity details(Long ipId);
}

