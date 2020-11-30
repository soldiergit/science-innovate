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

import com.soldier.modules.business.entity.TeacherOfRgEntity;
import com.soldier.modules.business.service.TeacherOfRgService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师-of-科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@RestController
@RequestMapping("business/teacherofrg")
public class TeacherOfRgController {
    @Autowired
    private TeacherOfRgService teacherOfRgService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofrg:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfRgService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{torgId}")
    @RequiresPermissions("business:teacherofrg:info")
    public R info(@PathVariable("torgId") Long torgId){
		TeacherOfRgEntity teacherOfRg = teacherOfRgService.getById(torgId);

        return R.ok().put("teacherOfRg", teacherOfRg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofrg:save")
    public R save(@RequestBody TeacherOfRgEntity teacherOfRg){
		teacherOfRgService.save(teacherOfRg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofrg:update")
    public R update(@RequestBody TeacherOfRgEntity teacherOfRg){
		teacherOfRgService.updateById(teacherOfRg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofrg:delete")
    public R delete(@RequestBody Long[] torgIds){
		teacherOfRgService.removeByIds(Arrays.asList(torgIds));

        return R.ok();
    }

}
