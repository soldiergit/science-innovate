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

import com.soldier.modules.business.entity.TeacherOfIpEntity;
import com.soldier.modules.business.service.TeacherOfIpService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师-of-大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@RestController
@RequestMapping("business/teacherofip")
public class TeacherOfIpController {
    @Autowired
    private TeacherOfIpService teacherOfIpService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofip:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfIpService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{toipId}")
    @RequiresPermissions("business:teacherofip:info")
    public R info(@PathVariable("toipId") Long toipId){
		TeacherOfIpEntity teacherOfIp = teacherOfIpService.getById(toipId);

        return R.ok().put("teacherOfIp", teacherOfIp);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofip:save")
    public R save(@RequestBody TeacherOfIpEntity teacherOfIp){
		teacherOfIpService.save(teacherOfIp);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofip:update")
    public R update(@RequestBody TeacherOfIpEntity teacherOfIp){
		teacherOfIpService.updateById(teacherOfIp);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofip:delete")
    public R delete(@RequestBody Long[] toipIds){
		teacherOfIpService.removeByIds(Arrays.asList(toipIds));

        return R.ok();
    }

}
