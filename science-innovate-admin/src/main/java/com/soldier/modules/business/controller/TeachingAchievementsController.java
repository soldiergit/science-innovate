package com.soldier.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldier.modules.business.entity.TeachingAchievementsEntity;
import com.soldier.modules.business.service.TeachingAchievementsService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教学成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teachingachievements")
public class TeachingAchievementsController {
    @Autowired
    private TeachingAchievementsService teachingAchievementsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teachingachievements:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teachingAchievementsService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:teachingachievements:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<TeachingAchievementsEntity> list = teachingAchievementsService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{taId}")
    @RequiresPermissions("business:teachingachievements:info")
    public R info(@PathVariable("taId") Long taId){
		TeachingAchievementsEntity teachingAchievements = teachingAchievementsService.getById(taId);

        return R.ok().put("teachingAchievements", teachingAchievements);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{taId}")
    @RequiresPermissions("business:teachingachievements:info")
    public R details(@PathVariable("taId") Long taId){
        TeachingAchievementsEntity teachingAchievements = teachingAchievementsService.details(taId);

        return R.ok().put("teachingAchievements", teachingAchievements);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teachingachievements:save")
    public R save(@RequestBody TeachingAchievementsEntity teachingAchievements){
		teachingAchievementsService.save(teachingAchievements);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teachingachievements:update")
    public R update(@RequestBody TeachingAchievementsEntity teachingAchievements){
		teachingAchievementsService.updateById(teachingAchievements);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teachingachievements:delete")
    public R delete(@RequestBody Long[] taIds){
		teachingAchievementsService.removeByIds(Arrays.asList(taIds));

        return R.ok();
    }

}
