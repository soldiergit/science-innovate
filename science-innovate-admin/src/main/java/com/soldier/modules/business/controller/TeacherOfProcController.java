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

import com.soldier.modules.business.entity.TeacherOfProcEntity;
import com.soldier.modules.business.service.TeacherOfProcService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师-of-项目立项表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teacherofproc")
public class TeacherOfProcController {
    @Autowired
    private TeacherOfProcService teacherOfProcService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofproc:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfProcService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{topcId}")
    @RequiresPermissions("business:teacherofproc:info")
    public R info(@PathVariable("topcId") Long topcId){
		TeacherOfProcEntity teacherOfProc = teacherOfProcService.getById(topcId);

        return R.ok().put("teacherOfProc", teacherOfProc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofproc:save")
    public R save(@RequestBody TeacherOfProcEntity teacherOfProc){
		teacherOfProcService.save(teacherOfProc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofproc:update")
    public R update(@RequestBody TeacherOfProcEntity teacherOfProc){
		teacherOfProcService.updateById(teacherOfProc);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofproc:delete")
    public R delete(@RequestBody Long[] topcIds){
		teacherOfProcService.removeByIds(Arrays.asList(topcIds));

        return R.ok();
    }

}
