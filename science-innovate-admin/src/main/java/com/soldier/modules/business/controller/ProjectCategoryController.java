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

import com.soldier.modules.business.entity.ProjectCategoryEntity;
import com.soldier.modules.business.service.ProjectCategoryService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 项目类别
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:43
 */
@RestController
@RequestMapping("business/projectcategory")
public class ProjectCategoryController {
    @Autowired
    private ProjectCategoryService projectCategoryService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:projectcategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{value}")
    @RequiresPermissions("business:projectcategory:info")
    public R info(@PathVariable("value") Long value){
		ProjectCategoryEntity projectCategory = projectCategoryService.getById(value);

        return R.ok().put("projectCategory", projectCategory);
    }

    ///////////////////////////////////添加修改删除时都要更新浏览器缓存和redis/////////////////////////////////////////
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:projectcategory:save")
    public R save(@RequestBody ProjectCategoryEntity projectCategory){
		projectCategoryService.save(projectCategory);

        return updateRedis();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:projectcategory:update")
    public R update(@RequestBody ProjectCategoryEntity projectCategory){
		projectCategoryService.updateById(projectCategory);

        return updateRedis();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:projectcategory:delete")
    public R delete(@RequestBody Long[] values){
		projectCategoryService.removeByIds(Arrays.asList(values));

        return updateRedis();
    }

    /**
     * 更新redis
     */
    private R updateRedis() {
        // 更新redis
        List<ProjectCategoryEntity> list = projectCategoryService.list();
        redisUtils.set(RedisKeys.getBasicsDataProjectCategory(), list, RedisUtils.NOT_EXPIRE);

        // 更新浏览器缓存
        return R.ok().put("list", list);
    }
}
