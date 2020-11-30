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

import com.soldier.modules.business.entity.GainTypeEntity;
import com.soldier.modules.business.service.GainTypeService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 成果类型
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-15 17:32:44
 */
@RestController
@RequestMapping("business/gaintype")
public class GainTypeController {
    @Autowired
    private GainTypeService gainTypeService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:gaintype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gainTypeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{value}")
    @RequiresPermissions("business:gaintype:info")
    public R info(@PathVariable("value") Long value){
		GainTypeEntity gainType = gainTypeService.getById(value);

        return R.ok().put("gainType", gainType);
    }

    ///////////////////////////////////添加修改删除时都要更新浏览器缓存和redis/////////////////////////////////////////
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:gaintype:save")
    public R save(@RequestBody GainTypeEntity gainType){
		gainTypeService.save(gainType);

        return updateRedis();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:gaintype:update")
    public R update(@RequestBody GainTypeEntity gainType){
		gainTypeService.updateById(gainType);

        return updateRedis();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:gaintype:delete")
    public R delete(@RequestBody Long[] values){
		gainTypeService.removeByIds(Arrays.asList(values));

        return updateRedis();
    }

    /**
     * 更新redis
     */
    private R updateRedis() {
        // 更新redis
        List<GainTypeEntity> list = gainTypeService.list();
        redisUtils.set(RedisKeys.getBasicsDataGainType(), list, RedisUtils.NOT_EXPIRE);

        // 更新浏览器缓存
        return R.ok().put("list", list);
    }
}
