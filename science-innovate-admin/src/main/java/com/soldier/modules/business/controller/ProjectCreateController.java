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

import com.soldier.modules.business.entity.ProjectCreateEntity;
import com.soldier.modules.business.service.ProjectCreateService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师项目立项
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/projectcreate")
public class ProjectCreateController {
    @Autowired
    private ProjectCreateService projectCreateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:projectcreate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectCreateService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:projectcreate:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<ProjectCreateEntity> list = projectCreateService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 教师以负责人或成员的身份查询未结题项目
     */
    @RequestMapping("/myproject/{userId}")
    @RequiresPermissions("business:projectcreate:list")
    public R myproject(@PathVariable("userId") Long userId){
        List<ProjectCreateEntity> projectCreateEntities = projectCreateService.queryMyProject(userId);

        if(projectCreateEntities == null || projectCreateEntities.size() == 0){
            return R.error("尊敬的教师，您好！当前无可结题项目，即将返回列表页面...");
        }

        return R.ok().put("projectCreateEntities", projectCreateEntities);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{pcId}")
    @RequiresPermissions("business:projectcreate:info")
    public R info(@PathVariable("pcId") Long pcId){
		ProjectCreateEntity projectCreate = projectCreateService.getById(pcId);

        return R.ok().put("projectCreate", projectCreate);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{pcId}")
    @RequiresPermissions("business:projectcreate:info")
    public R details(@PathVariable("pcId") Long pcId){
        ProjectCreateEntity projectCreate = projectCreateService.details(pcId);

        return R.ok().put("projectCreate", projectCreate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:projectcreate:save")
    public R save(@RequestBody ProjectCreateEntity projectCreate){
		projectCreateService.save(projectCreate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:projectcreate:update")
    public R update(@RequestBody ProjectCreateEntity projectCreate){
		projectCreateService.updateById(projectCreate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:projectcreate:delete")
    public R delete(@RequestBody Long[] pcIds){
		projectCreateService.removeByIds(Arrays.asList(pcIds));

        return R.ok();
    }

}
