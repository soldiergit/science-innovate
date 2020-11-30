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

import com.soldier.modules.business.entity.TeacherOfTaEntity;
import com.soldier.modules.business.service.TeacherOfTaService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师-of-教学成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:26
 */
@RestController
@RequestMapping("business/teacherofta")
public class TeacherOfTaController {
    @Autowired
    private TeacherOfTaService teacherOfTaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofta:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfTaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{totaId}")
    @RequiresPermissions("business:teacherofta:info")
    public R info(@PathVariable("totaId") Long totaId){
		TeacherOfTaEntity teacherOfTa = teacherOfTaService.getById(totaId);

        return R.ok().put("teacherOfTa", teacherOfTa);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofta:save")
    public R save(@RequestBody TeacherOfTaEntity teacherOfTa){
		teacherOfTaService.save(teacherOfTa);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofta:update")
    public R update(@RequestBody TeacherOfTaEntity teacherOfTa){
		teacherOfTaService.updateById(teacherOfTa);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofta:delete")
    public R delete(@RequestBody Long[] totaIds){
		teacherOfTaService.removeByIds(Arrays.asList(totaIds));

        return R.ok();
    }

}
