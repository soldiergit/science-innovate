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

import com.soldier.modules.business.entity.StudentCompetitionEntity;
import com.soldier.modules.business.service.StudentCompetitionService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 学生竞赛
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/studentcompetition")
public class StudentCompetitionController {
    @Autowired
    private StudentCompetitionService studentCompetitionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:studentcompetition:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = studentCompetitionService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:studentcompetition:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<StudentCompetitionEntity> list = studentCompetitionService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{scId}")
    @RequiresPermissions("business:studentcompetition:info")
    public R info(@PathVariable("scId") Long scId){
		StudentCompetitionEntity studentCompetition = studentCompetitionService.getById(scId);

        return R.ok().put("studentCompetition", studentCompetition);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{scId}")
    @RequiresPermissions("business:studentcompetition:info")
    public R details(@PathVariable("scId") Long scId){
        StudentCompetitionEntity studentCompetition = studentCompetitionService.details(scId);

        return R.ok().put("studentCompetition", studentCompetition);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:studentcompetition:save")
    public R save(@RequestBody StudentCompetitionEntity studentCompetition){
		studentCompetitionService.save(studentCompetition);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:studentcompetition:update")
    public R update(@RequestBody StudentCompetitionEntity studentCompetition){
		studentCompetitionService.updateById(studentCompetition);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:studentcompetition:delete")
    public R delete(@RequestBody Long[] scIds){
		studentCompetitionService.removeByIds(Arrays.asList(scIds));

        return R.ok();
    }

}
