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

import com.soldier.modules.business.entity.ResearchGainEntity;
import com.soldier.modules.business.service.ResearchGainService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 科研成果表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-09-16 20:16:52
 */
@RestController
@RequestMapping("business/researchgain")
public class ResearchGainController {
    @Autowired
    private ResearchGainService researchGainService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:researchgain:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = researchGainService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 个人业绩列表
     */
    @RequestMapping("/myList")
    @RequiresPermissions("business:researchgain:list")
    public R myList(@RequestParam Map<String, Object> params){
        List<ResearchGainEntity> list = researchGainService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{rgId}")
    @RequiresPermissions("business:researchgain:info")
    public R info(@PathVariable("rgId") Long rgId){
		ResearchGainEntity researchGain = researchGainService.getById(rgId);

        return R.ok().put("researchGain", researchGain);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{tcId}")
    @RequiresPermissions("business:researchgain:info")
    public R details(@PathVariable("tcId") Long tcId){
        ResearchGainEntity researchGain = researchGainService.details(tcId);

        return R.ok().put("researchGain", researchGain);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:researchgain:save")
    public R save(@RequestBody ResearchGainEntity researchGain){
		researchGainService.save(researchGain);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:researchgain:update")
    public R update(@RequestBody ResearchGainEntity researchGain){
		researchGainService.updateById(researchGain);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:researchgain:delete")
    public R delete(@RequestBody Long[] rgIds){
		researchGainService.removeByIds(Arrays.asList(rgIds));

        return R.ok();
    }

}
