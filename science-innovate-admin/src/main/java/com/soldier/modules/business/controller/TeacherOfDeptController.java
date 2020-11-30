package com.soldier.modules.business.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;
import com.soldier.modules.business.entity.SysDeptEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.service.SysDeptAdminInfoService;
import com.soldier.modules.business.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.soldier.modules.business.entity.TeacherOfDeptEntity;
import com.soldier.modules.business.service.TeacherOfDeptService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;

/**
 * 教师(部门成员)与部门对应关系
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 16:28:20
 */
@RestController
@RequestMapping("business/teacherofdept")
public class TeacherOfDeptController {

    @Autowired
    private TeacherOfDeptService teacherOfDeptService;

    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherofdept:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherOfDeptService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询已加入部门的教师id
     */
    @RequestMapping("/choiceteaids")
    @RequiresPermissions("business:teacherofdept:list")
    public R choiceTea(@RequestParam Map<String, Object> params){
        List<Long> list = teacherOfDeptService.queryChoiceTeaIds(params);
        return R.ok().put("data", list);
    }

    /**
     * 二级学院管理员：查询该二级学院下的所有教师
     */
    @RequestMapping("/mydepttealist")
    @RequiresPermissions("business:teacherofdept:list")
    public R myDeptTeas(@RequestParam Map<String, Object> params){

        // 查询该用户（二级学院管理员）所管理的部门
        Object userId = params.get("userId");
        SysDeptAdminInfoEntity byUserId = null;
        if (userId!=null) {
            byUserId = sysDeptAdminInfoService.getByUserId(Long.valueOf(String.valueOf(userId)));
        }
        if (byUserId == null) {
            return R.error("二级学院管理员，查询不到你管理的部门，请联系系统管理员！");
        }

        // 查询该二级学院管理员管理的部门信息
        Long deptId = byUserId.getDeptId();
        byUserId.setSysDeptEntity(sysDeptService.getById(deptId));
        params.put("deptId", deptId);

        //暂无
        List<TeacherInfoEntity> teacherList = new ArrayList<>();
        teacherList.add(this.nowNo(false));
        teacherList.addAll(teacherOfDeptService.queryChoiceTeaList(params));
        return R.ok().put("teacherList", teacherList).put("parentDeptInfo", byUserId.getSysDeptEntity());
    }

    /**已摒弃
     * 教师：查询自己所在学院的所有教师，即自己的同事，且不包含本人，
     */
    @RequestMapping("/mycolleague")
    public R myColleague(@RequestParam Map<String, Object> params){

        // 查询教师所属学院
        Object userId = params.get("userId");
        Long thisUserCollegeId = null;
        if (userId!=null) {
            thisUserCollegeId = sysDeptService.queryCollegeIdByUserId(Long.valueOf(String.valueOf(userId)));
        }
        if (thisUserCollegeId == null) {
            return R.error("尊敬的用户：您好！无法查询到您所在的二级学院信息，请联系二级学院管理员！");
        }

        // 查询该学院下的所有教师，且不包含本人
        params.put("deptId", thisUserCollegeId);

        // 是否为【暂无】添加disabled = true
        Object haveDisabled = params.get("addDisabled");
        boolean addDisabled = false;
        if (haveDisabled != null && haveDisabled != "") addDisabled = true;

        //暂无
        List<TeacherInfoEntity> teacherList = new ArrayList<>();
        teacherList.add(this.nowNo(addDisabled));
        teacherList.addAll(teacherOfDeptService.queryChoiceTeaList(params));
        return R.ok().put("teacherList", teacherList);
    }

    private TeacherInfoEntity nowNo(boolean addDisabled) {
        TeacherInfoEntity no = new TeacherInfoEntity();
        no.setTeacherId(0L);
        no.setName("暂无");
        if (addDisabled) no.setDisabled(true);
        return no;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("business:teacherofdept:info")
    public R info(@PathVariable("id") Long id){
		TeacherOfDeptEntity teacherOfDept = teacherOfDeptService.getById(id);

        return R.ok().put("teacherOfDept", teacherOfDept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherofdept:save")
    public R save(@RequestBody TeacherOfDeptEntity teacherOfDept){
		teacherOfDeptService.save(teacherOfDept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:teacherofdept:update")
    public R update(@RequestBody TeacherOfDeptEntity teacherOfDept){
		teacherOfDeptService.updateById(teacherOfDept);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherofdept:delete")
    public R delete(@RequestBody Long[] ids){
		teacherOfDeptService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
