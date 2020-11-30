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

import com.soldier.modules.business.entity.ProjectLevelEntity;
import com.soldier.modules.business.service.ProjectLevelService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 项目级别
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
@RestController
@RequestMapping("business/projectlevel")
public class ProjectLevelController {
    @Autowired
    private ProjectLevelService projectLevelService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:projectlevel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectLevelService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{value}")
    @RequiresPermissions("business:projectlevel:info")
    public R info(@PathVariable("value") Long value){
		ProjectLevelEntity projectLevel = projectLevelService.getById(value);

        return R.ok().put("projectLevel", projectLevel);
    }

    ///////////////////////////////////添加修改删除时都要更新浏览器缓存和redis/////////////////////////////////////////
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:projectlevel:save")
    public R save(@RequestBody ProjectLevelEntity projectLevel){
		projectLevelService.save(projectLevel);

        return updateRedis();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:projectlevel:update")
    public R update(@RequestBody ProjectLevelEntity projectLevel){
		projectLevelService.updateById(projectLevel);

        return updateRedis();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:projectlevel:delete")
    public R delete(@RequestBody Long[] values){
		projectLevelService.removeByIds(Arrays.asList(values));

        return updateRedis();
    }

    /**
     * 更新redis
     */
    private R updateRedis() {
        // 更新redis
        List<ProjectLevelEntity> list = projectLevelService.list();
        redisUtils.set(RedisKeys.getBasicsDataProjectLevel(), list, RedisUtils.NOT_EXPIRE);

        // 更新浏览器缓存
        return R.ok().put("list", list);
    }
}
