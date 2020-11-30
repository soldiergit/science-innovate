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

import com.soldier.modules.business.entity.TeacherOfTmEntity;
import com.soldier.modules.business.service.TeacherOfTmService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师-of-教材表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teacheroftm")
public class TeacherOfTmController {
    @Autowired
    private TeacherOfTmService teacherOfTmService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacheroftm:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfTmService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{totmId}")
    @RequiresPermissions("business:teacheroftm:info")
    public R info(@PathVariable("totmId") Long totmId){
		TeacherOfTmEntity teacherOfTm = teacherOfTmService.getById(totmId);

        return R.ok().put("teacherOfTm", teacherOfTm);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacheroftm:save")
    public R save(@RequestBody TeacherOfTmEntity teacherOfTm){
		teacherOfTmService.save(teacherOfTm);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacheroftm:update")
    public R update(@RequestBody TeacherOfTmEntity teacherOfTm){
		teacherOfTmService.updateById(teacherOfTm);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacheroftm:delete")
    public R delete(@RequestBody Long[] totmIds){
		teacherOfTmService.removeByIds(Arrays.asList(totmIds));

        return R.ok();
    }

}
