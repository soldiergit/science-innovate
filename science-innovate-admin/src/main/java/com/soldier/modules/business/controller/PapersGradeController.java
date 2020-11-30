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

import com.soldier.modules.business.entity.PapersGradeEntity;
import com.soldier.modules.business.service.PapersGradeService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 论文等级
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
@RestController
@RequestMapping("business/papersgrade")
public class PapersGradeController {
    @Autowired
    private PapersGradeService papersGradeService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:papersgrade:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = papersGradeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{value}")
    @RequiresPermissions("business:papersgrade:info")
    public R info(@PathVariable("value") Long value){
		PapersGradeEntity papersGrade = papersGradeService.getById(value);

        return R.ok().put("papersGrade", papersGrade);
    }

    ///////////////////////////////////添加修改删除时都要更新浏览器缓存和redis/////////////////////////////////////////
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:papersgrade:save")
    public R save(@RequestBody PapersGradeEntity papersGrade){
		papersGradeService.save(papersGrade);

        return updateRedis();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:papersgrade:update")
    public R update(@RequestBody PapersGradeEntity papersGrade){
		papersGradeService.updateById(papersGrade);

        return updateRedis();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:papersgrade:delete")
    public R delete(@RequestBody Long[] values){
		papersGradeService.removeByIds(Arrays.asList(values));

        return updateRedis();
    }

    /**
     * 更新redis
     */
    private R updateRedis() {
        // 更新redis
        List<PapersGradeEntity> list = papersGradeService.list();
        redisUtils.set(RedisKeys.getBasicsDataPapersGrade(), list, RedisUtils.NOT_EXPIRE);

        // 更新浏览器缓存
        return R.ok().put("list", list);
    }
}
