package com.soldier.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.soldier.modules.business.entity.TeacherInfoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldier.modules.business.entity.TeacherOfApEntity;
import com.soldier.modules.business.service.TeacherOfApService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师-of-论文表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teacherofap")
public class TeacherOfApController {
    @Autowired
    private TeacherOfApService teacherOfApService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofap:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfApService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{toapId}")
    @RequiresPermissions("business:teacherofap:info")
    public R info(@PathVariable("toapId") Long toapId){
		TeacherOfApEntity teacherOfAp = teacherOfApService.getById(toapId);

        return R.ok().put("teacherOfAp", teacherOfAp);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofap:save")
    public R save(@RequestBody TeacherOfApEntity teacherOfAp){
		teacherOfApService.save(teacherOfAp);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofap:update")
    public R update(@RequestBody TeacherOfApEntity teacherOfAp){
		teacherOfApService.updateById(teacherOfAp);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofap:delete")
    public R delete(@RequestBody Long[] toapIds){
		teacherOfApService.removeByIds(Arrays.asList(toapIds));

        return R.ok();
    }

}
