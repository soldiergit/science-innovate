package com.soldier.modules.business.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.soldier.modules.business.entity.SysDeptEntity;
import com.soldier.modules.business.service.SysDeptService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 系统部门表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/sysdept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:sysdept:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysDeptService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有部门树列表
     */
    @GetMapping("/treelist")
    @RequiresPermissions("business:sysdept:list")
    public List<SysDeptEntity> treelist(@RequestParam Map<String, Object> params){
        List<SysDeptEntity> deptList = sysDeptService.treelist(params.get("userId"));

        return deptList;
    }

    /**
     * 查询系统全部二级学院：选择菜单(添加、修改学校部门)
     */
    @GetMapping("/collegeSelect")
    @RequiresPermissions("business:sysdept:select")
    public R collegeSelect(){
        //查询列表数据
        List<SysDeptEntity> deptList = sysDeptService.queryCollegeList();

        //添加顶级部门
        SysDeptEntity root = new SysDeptEntity();
        root.setDeptId(0L);
        root.setName("一级部门");
        root.setParentId(-1L);
        root.setOpen(true);
        root.setDisabled(true);
        deptList.add(root);

        return R.ok().put("deptList", deptList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("business:sysdept:info")
    public R info(@PathVariable("deptId") Long deptId){
		SysDeptEntity sysDept = sysDeptService.getById(deptId);

        return R.ok().put("dept", sysDept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:sysdept:save")
    public R save(@RequestBody SysDeptEntity sysDept){
		sysDeptService.save(sysDept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:sysdept:update")
    public R update(@RequestBody SysDeptEntity sysDept){
		sysDeptService.updateById(sysDept);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{deptId}")
    @RequiresPermissions("business:sysdept:delete")
    public R delete(@PathVariable Long deptId){

        // 判断是否有子菜单或按钮
        Integer number = sysDeptService.queryNumByParent(deptId);
        if(number != null){
            return R.error("请先删除子部门");
        }

		sysDeptService.removeById(deptId);

        return R.ok();
    }

}
