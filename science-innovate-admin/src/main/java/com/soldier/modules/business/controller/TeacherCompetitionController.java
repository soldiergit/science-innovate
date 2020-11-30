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

import com.soldier.modules.business.entity.TeacherCompetitionEntity;
import com.soldier.modules.business.service.TeacherCompetitionService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师竞赛
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teachercompetition")
public class TeacherCompetitionController {
    @Autowired
    private TeacherCompetitionService teacherCompetitionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teachercompetition:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherCompetitionService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:teachercompetition:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<TeacherCompetitionEntity> list = teacherCompetitionService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{tcId}")
    @RequiresPermissions("business:teachercompetition:info")
    public R info(@PathVariable("tcId") Long tcId){
		TeacherCompetitionEntity teacherCompetition = teacherCompetitionService.getById(tcId);

        return R.ok().put("teacherCompetition", teacherCompetition);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{tcId}")
    @RequiresPermissions("business:teachercompetition:info")
    public R details(@PathVariable("tcId") Long tcId){
        TeacherCompetitionEntity teacherCompetition = teacherCompetitionService.details(tcId);

        return R.ok().put("teacherCompetition", teacherCompetition);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teachercompetition:save")
    public R save(@RequestBody TeacherCompetitionEntity teacherCompetition){
		teacherCompetitionService.save(teacherCompetition);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teachercompetition:update")
    public R update(@RequestBody TeacherCompetitionEntity teacherCompetition){
		teacherCompetitionService.updateById(teacherCompetition);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teachercompetition:delete")
    public R delete(@RequestBody Long[] tcIds){
		teacherCompetitionService.removeByIds(Arrays.asList(tcIds));

        return R.ok();
    }

}
