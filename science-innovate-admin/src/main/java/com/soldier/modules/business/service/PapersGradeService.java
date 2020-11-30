package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.PapersGradeEntity;

import java.util.Map;

/**
 * 论文等级
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
public interface PapersGradeService extends IService<PapersGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

