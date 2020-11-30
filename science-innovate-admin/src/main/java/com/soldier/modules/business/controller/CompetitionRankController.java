package com.soldier.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.soldier.common.utils.RedisKeys;
import com.soldier.common.utils.RedisUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldier.modules.business.entity.CompetitionRankEntity;
import com.soldier.modules.business.service.CompetitionRankService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 竞赛获奖等次
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
@RestController
@RequestMapping("business/competitionrank")
public class CompetitionRankController {
    @Autowired
    private CompetitionRankService competitionRankService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:competitionrank:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = competitionRankService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{value}")
    @RequiresPermissions("business:competitionrank:info")
    public R info(@PathVariable("value") Long value){
		CompetitionRankEntity competitionRank = competitionRankService.getById(value);

        return R.ok().put("competitionRank", competitionRank);
    }

    ///////////////////////////////////添加修改删除时都要更新浏览器缓存和redis/////////////////////////////////////////
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:competitionrank:save")
    public R save(@RequestBody CompetitionRankEntity competitionRank){
		competitionRankService.save(competitionRank);

        return updateRedis();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:competitionrank:update")
    public R update(@RequestBody CompetitionRankEntity competitionRank){
		competitionRankService.updateById(competitionRank);

        return updateRedis();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:competitionrank:delete")
    public R delete(@RequestBody Long[] values){
		competitionRankService.removeByIds(Arrays.asList(values));

        return updateRedis();
    }

    /**
     * 更新redis
     */
    private R updateRedis() {
        // 更新redis
        List<CompetitionRankEntity> list = competitionRankService.list();
        redisUtils.set(RedisKeys.getBasicsDataCompetitionRank(), list, RedisUtils.NOT_EXPIRE);

        // 更新浏览器缓存
        return R.ok().put("list", list);
    }
}
