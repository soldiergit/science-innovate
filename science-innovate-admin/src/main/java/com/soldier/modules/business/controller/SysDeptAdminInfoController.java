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

import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;
import com.soldier.modules.business.service.SysDeptAdminInfoService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 系统部门管理员信息表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-06-09 14:05:40
 */
@RestController
@RequestMapping("business/sysdeptadmininfo")
public class SysDeptAdminInfoController {
    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:sysdeptadmininfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysDeptAdminInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{deptAdminId}")
    @RequiresPermissions("business:sysdeptadmininfo:info")
    public R info(@PathVariable("deptAdminId") Long deptAdminId){
		SysDeptAdminInfoEntity sysDeptAdminInfo = sysDeptAdminInfoService.getById(deptAdminId);

        return R.ok().put("sysDeptAdminInfo", sysDeptAdminInfo);
    }
    @RequestMapping("/getByDeptId/{deptId}")
    @RequiresPermissions("business:sysdeptadmininfo:info")
    public R getByDeptId(@PathVariable("deptId") Long deptId){
		SysDeptAdminInfoEntity sysDeptAdminInfo = sysDeptAdminInfoService.getByDeptId(deptId);

        return R.ok().put("sysDeptAdminInfo", sysDeptAdminInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:sysdeptadmininfo:save")
    public R save(@RequestBody SysDeptAdminInfoEntity sysDeptAdminInfo){
		sysDeptAdminInfoService.save(sysDeptAdminInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:sysdeptadmininfo:update")
    public R update(@RequestBody SysDeptAdminInfoEntity sysDeptAdminInfo){
		sysDeptAdminInfoService.updateById(sysDeptAdminInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:sysdeptadmininfo:delete")
    public R delete(@RequestBody Long[] deptAdminIds){
		sysDeptAdminInfoService.removeByIds(Arrays.asList(deptAdminIds));

        return R.ok();
    }

}
