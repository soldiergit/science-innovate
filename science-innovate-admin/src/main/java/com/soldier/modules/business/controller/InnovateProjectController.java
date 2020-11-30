package com.soldier.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.soldier.modules.business.entity.StudentCompetitionEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soldier.modules.business.entity.InnovateProjectEntity;
import com.soldier.modules.business.service.InnovateProjectService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 大创项目表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@RestController
@RequestMapping("business/innovateproject")
public class InnovateProjectController {
    @Autowired
    private InnovateProjectService innovateProjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:innovateproject:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateProjectService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:innovateproject:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<InnovateProjectEntity> list = innovateProjectService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{ipId}")
    @RequiresPermissions("business:innovateproject:info")
    public R info(@PathVariable("ipId") Long ipId){
		InnovateProjectEntity innovateProject = innovateProjectService.getById(ipId);

        return R.ok().put("innovateProject", innovateProject);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{scId}")
    @RequiresPermissions("business:innovateproject:info")
    public R details(@PathVariable("scId") Long scId){
        InnovateProjectEntity innovateProject = innovateProjectService.details(scId);

        return R.ok().put("innovateProject", innovateProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:innovateproject:save")
    public R save(@RequestBody InnovateProjectEntity innovateProject){
		innovateProjectService.save(innovateProject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:innovateproject:update")
    public R update(@RequestBody InnovateProjectEntity innovateProject){
		innovateProjectService.updateById(innovateProject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:innovateproject:delete")
    public R delete(@RequestBody Long[] ipIds){
		innovateProjectService.removeByIds(Arrays.asList(ipIds));

        return R.ok();
    }

}
