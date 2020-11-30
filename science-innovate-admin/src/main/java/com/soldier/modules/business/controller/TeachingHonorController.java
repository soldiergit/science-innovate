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

import com.soldier.modules.business.entity.TeachingHonorEntity;
import com.soldier.modules.business.service.TeachingHonorService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教学荣誉表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teachinghonor")
public class TeachingHonorController {
    @Autowired
    private TeachingHonorService teachingHonorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teachinghonor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teachingHonorService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:academicpapers:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<TeachingHonorEntity> list = teachingHonorService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{thId}")
    @RequiresPermissions("business:teachinghonor:info")
    public R info(@PathVariable("thId") Long thId){
		TeachingHonorEntity teachingHonor = teachingHonorService.getById(thId);

        return R.ok().put("teachingHonor", teachingHonor);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{thId}")
    @RequiresPermissions("business:teachinghonor:info")
    public R details(@PathVariable("thId") Long thId){
        TeachingHonorEntity teachingHonor = teachingHonorService.details(thId);

        return R.ok().put("teachingHonor", teachingHonor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teachinghonor:save")
    public R save(@RequestBody TeachingHonorEntity teachingHonor){
		teachingHonorService.save(teachingHonor);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teachinghonor:update")
    public R update(@RequestBody TeachingHonorEntity teachingHonor){
		teachingHonorService.updateById(teachingHonor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teachinghonor:delete")
    public R delete(@RequestBody Long[] thIds){
		teachingHonorService.removeByIds(Arrays.asList(thIds));

        return R.ok();
    }

}
