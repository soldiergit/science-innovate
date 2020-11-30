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

import com.soldier.modules.business.entity.TeacherOfStucEntity;
import com.soldier.modules.business.service.TeacherOfStucService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 指导老师-of-学生竞赛表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teacherofstuc")
public class TeacherOfStucController {
    @Autowired
    private TeacherOfStucService teacherOfStucService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofstuc:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfStucService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{toscId}")
    @RequiresPermissions("business:teacherofstuc:info")
    public R info(@PathVariable("toscId") Long toscId){
        TeacherOfStucEntity teacherOfStuc = teacherOfStucService.getById(toscId);

        return R.ok().put("teacherOfStuc", teacherOfStuc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofstuc:save")
    public R save(@RequestBody TeacherOfStucEntity teacherOfStuc){
        teacherOfStucService.save(teacherOfStuc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofstuc:update")
    public R update(@RequestBody TeacherOfStucEntity teacherOfStuc){
        teacherOfStucService.updateById(teacherOfStuc);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofstuc:delete")
    public R delete(@RequestBody Long[] toscIds){
        teacherOfStucService.removeByIds(Arrays.asList(toscIds));

        return R.ok();
    }

}
