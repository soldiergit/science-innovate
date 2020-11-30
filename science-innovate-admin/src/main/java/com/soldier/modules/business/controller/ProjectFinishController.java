package com.soldier.modules.business.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldier.modules.business.entity.ProjectFinishEntity;
import com.soldier.modules.business.service.ProjectFinishService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 项目结题表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-08-17 21:02:53
 */
@RestController
@RequestMapping("business/projectfinish")
public class ProjectFinishController {
    @Autowired
    private ProjectFinishService projectFinishService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:projectfinish:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectFinishService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{pfId}")
    @RequiresPermissions("business:projectfinish:info")
    public R info(@PathVariable("pfId") Long pfId){
		ProjectFinishEntity projectFinish = projectFinishService.getById(pfId);

        return R.ok().put("projectFinish", projectFinish);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{pfId}")
    @RequiresPermissions("business:projectfinish:info")
    public R details(@PathVariable("pfId") Long pfId){
        ProjectFinishEntity projectFinish = projectFinishService.details(pfId);

        return R.ok().put("projectFinish", projectFinish);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:projectfinish:save")
    public R save(@RequestBody ProjectFinishEntity projectFinish){
		projectFinishService.save(projectFinish);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:projectfinish:update")
    public R update(@RequestBody ProjectFinishEntity projectFinish){
		projectFinishService.updateById(projectFinish);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:projectfinish:delete")
    public R delete(@RequestBody Long[] pfIds){
		projectFinishService.removeByIds(Arrays.asList(pfIds));

        return R.ok();
    }

}
