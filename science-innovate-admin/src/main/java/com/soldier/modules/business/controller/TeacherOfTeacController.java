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

import com.soldier.modules.business.entity.TeacherOfTeacEntity;
import com.soldier.modules.business.service.TeacherOfTeacService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 指导老师-of-教师竞赛表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teacherofteac")
public class TeacherOfTeacController {
    @Autowired
    private TeacherOfTeacService teacherOfTeacService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofteac:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfTeacService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{totcId}")
    @RequiresPermissions("business:teacherofteac:info")
    public R info(@PathVariable("totcId") Long totcId){
		TeacherOfTeacEntity teacherOfTeac = teacherOfTeacService.getById(totcId);

        return R.ok().put("teacherOfTeac", teacherOfTeac);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofteac:save")
    public R save(@RequestBody TeacherOfTeacEntity teacherOfTeac){
		teacherOfTeacService.save(teacherOfTeac);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofteac:update")
    public R update(@RequestBody TeacherOfTeacEntity teacherOfTeac){
		teacherOfTeacService.updateById(teacherOfTeac);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofteac:delete")
    public R delete(@RequestBody Long[] totcIds){
		teacherOfTeacService.removeByIds(Arrays.asList(totcIds));

        return R.ok();
    }

}
