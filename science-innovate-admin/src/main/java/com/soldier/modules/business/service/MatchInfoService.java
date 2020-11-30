package com.soldier.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldier.common.utils.PageUtils;
import com.soldier.modules.business.entity.MatchInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 赛事信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
public interface MatchInfoService extends IService<MatchInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询全部赛事
     *  赛事类型：1-学生竞赛 2-教师竞赛
     */
    List<MatchInfoEntity> select(Integer type);
}

