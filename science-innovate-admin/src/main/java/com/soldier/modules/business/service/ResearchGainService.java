package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.ResearchGainEntity;

import java.util.List;
import java.util.Map;

/**
 * 科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
public interface ResearchGainService extends IService<ResearchGainEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ResearchGainEntity> queryList(Map<String, Object> params);

    ResearchGainEntity details(Long rgId);
}

