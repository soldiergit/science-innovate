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

import com.soldier.modules.business.entity.TeacherOfThEntity;
import com.soldier.modules.business.service.TeacherOfThService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师-of-教学荣誉表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teacherofth")
public class TeacherOfThController {
    @Autowired
    private TeacherOfThService teacherOfThService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofth:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfThService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{totaId}")
    @RequiresPermissions("business:teacherofth:info")
    public R info(@PathVariable("totaId") Long totaId){
		TeacherOfThEntity teacherOfTh = teacherOfThService.getById(totaId);

        return R.ok().put("teacherOfTh", teacherOfTh);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofth:save")
    public R save(@RequestBody TeacherOfThEntity teacherOfTh){
		teacherOfThService.save(teacherOfTh);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofth:update")
    public R update(@RequestBody TeacherOfThEntity teacherOfTh){
		teacherOfThService.updateById(teacherOfTh);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofth:delete")
    public R delete(@RequestBody Long[] totaIds){
		teacherOfThService.removeByIds(Arrays.asList(totaIds));

        return R.ok();
    }

}
