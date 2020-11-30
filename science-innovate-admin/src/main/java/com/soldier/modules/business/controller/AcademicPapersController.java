package com.soldier.modules.business.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldier.modules.business.entity.AcademicPapersEntity;
import com.soldier.modules.business.service.AcademicPapersService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 学术论文
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/academicpapers")
public class AcademicPapersController {
    @Autowired
    private AcademicPapersService academicPapersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:academicpapers:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = academicPapersService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:academicpapers:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<AcademicPapersEntity> list = academicPapersService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{apId}")
    @RequiresPermissions("business:academicpapers:info")
    public R info(@PathVariable("apId") Long apId){
		AcademicPapersEntity academicPapers = academicPapersService.getById(apId);

        return R.ok().put("academicPapers", academicPapers);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{apId}")
    @RequiresPermissions("business:academicpapers:info")
    public R details(@PathVariable("apId") Long apId){
		AcademicPapersEntity academicPapers = academicPapersService.details(apId);

        return R.ok().put("academicPapers", academicPapers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:academicpapers:save")
    public R save(@RequestBody AcademicPapersEntity academicPapers){
		academicPapersService.save(academicPapers);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:academicpapers:update")
    public R update(@RequestBody AcademicPapersEntity academicPapers){
		academicPapersService.updateById(academicPapers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:academicpapers:delete")
    public R delete(@RequestBody Long[] apIds){
		academicPapersService.removeByIds(Arrays.asList(apIds));

        return R.ok();
    }

}
