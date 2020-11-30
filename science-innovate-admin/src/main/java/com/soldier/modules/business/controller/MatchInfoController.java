package com.soldier.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.soldier.modules.business.service.TeacherInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.soldier.modules.business.entity.MatchInfoEntity;
import com.soldier.modules.business.service.MatchInfoService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 赛事信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/matchinfo")
public class MatchInfoController {
    @Autowired
    private MatchInfoService matchInfoService;
    @Autowired
    private TeacherInfoService teacherInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:matchinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = matchInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 选择赛事
     *  userId：指导老师默认选择自己
     *  matchType：赛事类型：1-学生竞赛 2-教师竞赛
     */
    @GetMapping("/select")
    @RequiresPermissions("business:matchinfo:select")
    public R select(@RequestParam Map<String, Object> params){
        //查询赛事列表数据
        List<MatchInfoEntity> matchList = matchInfoService.select(Integer.parseInt(params.get("matchType").toString()));

        // 查询教师Id
        Long teacherId = teacherInfoService.queryTeaIdByUserId(Long.parseLong(params.get("userId").toString()));

        return R.ok().put("matchList", matchList).put("teacherId", teacherId);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{matchId}")
    @RequiresPermissions("business:matchinfo:info")
    public R info(@PathVariable("matchId") Long matchId){
		MatchInfoEntity matchInfo = matchInfoService.getById(matchId);

        return R.ok().put("matchInfo", matchInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:matchinfo:save")
    public R save(@RequestBody MatchInfoEntity matchInfo){
		matchInfoService.save(matchInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:matchinfo:update")
    public R update(@RequestBody MatchInfoEntity matchInfo){
		matchInfoService.updateById(matchInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:matchinfo:delete")
    public R delete(@RequestBody Long[] matchIds){
		matchInfoService.removeByIds(Arrays.asList(matchIds));

        return R.ok();
    }

}
