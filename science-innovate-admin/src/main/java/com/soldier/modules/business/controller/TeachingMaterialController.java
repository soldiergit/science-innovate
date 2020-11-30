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

import com.soldier.modules.business.entity.TeachingMaterialEntity;
import com.soldier.modules.business.service.TeachingMaterialService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教材表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teachingmaterial")
public class TeachingMaterialController {
    @Autowired
    private TeachingMaterialService teachingMaterialService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teachingmaterial:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teachingMaterialService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:teachingmaterial:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<TeachingMaterialEntity> list = teachingMaterialService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{materialId}")
    @RequiresPermissions("business:teachingmaterial:info")
    public R info(@PathVariable("materialId") Long materialId){
		TeachingMaterialEntity teachingMaterial = teachingMaterialService.getById(materialId);

        return R.ok().put("teachingMaterial", teachingMaterial);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{materialId}")
    @RequiresPermissions("business:teachingmaterial:info")
    public R details(@PathVariable("materialId") Long materialId){
        TeachingMaterialEntity teachingMaterial = teachingMaterialService.details(materialId);

        return R.ok().put("teachingMaterial", teachingMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teachingmaterial:save")
    public R save(@RequestBody TeachingMaterialEntity teachingMaterial){
		teachingMaterialService.save(teachingMaterial);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teachingmaterial:update")
    public R update(@RequestBody TeachingMaterialEntity teachingMaterial){
		teachingMaterialService.updateById(teachingMaterial);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teachingmaterial:delete")
    public R delete(@RequestBody Long[] materialIds){
		teachingMaterialService.removeByIds(Arrays.asList(materialIds));

        return R.ok();
    }

}
