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

import com.soldier.modules.business.entity.CompetitionGradeEntity;
import com.soldier.modules.business.service.CompetitionGradeService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 竞赛获奖级别
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
@RestController
@RequestMapping("business/competitiongrade")
public class CompetitionGradeController {
    @Autowired
    private CompetitionGradeService competitionGradeService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:competitiongrade:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = competitionGradeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{value}")
    @RequiresPermissions("business:competitiongrade:info")
    public R info(@PathVariable("value") Long value){
		CompetitionGradeEntity competitionGrade = competitionGradeService.getById(value);

        return R.ok().put("competitionGrade", competitionGrade);
    }

    ///////////////////////////////////添加修改删除时都要更新浏览器缓存和redis/////////////////////////////////////////
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:competitiongrade:save")
    public R save(@RequestBody CompetitionGradeEntity competitionGrade){
		competitionGradeService.save(competitionGrade);

		return updateRedis();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:competitiongrade:update")
    public R update(@RequestBody CompetitionGradeEntity competitionGrade){
		competitionGradeService.updateById(competitionGrade);

        return updateRedis();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:competitiongrade:delete")
    public R delete(@RequestBody Long[] values){
		competitionGradeService.removeByIds(Arrays.asList(values));

        return updateRedis();
    }

    /**
     * 更新redis
     */
    private R updateRedis() {
        // 更新redis
        List<CompetitionGradeEntity> list = competitionGradeService.list();
        redisUtils.set(RedisKeys.getBasicsDataCompetitionGrade(), list, RedisUtils.NOT_EXPIRE);

        // 更新浏览器缓存
        return R.ok().put("list", list);
    }
}
