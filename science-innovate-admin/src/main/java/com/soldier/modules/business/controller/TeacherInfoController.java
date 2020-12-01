package com.soldier.modules.business.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soldier.common.utils.*;
import com.soldier.modules.business.easyexcel.TeacherUploadDataListener;
import com.soldier.modules.business.entity.SysDeptAdminInfoEntity;
import com.soldier.modules.business.entity.SysDeptEntity;
import com.soldier.modules.business.service.SysDeptAdminInfoService;
import com.soldier.modules.business.service.SysDeptService;
import com.soldier.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.service.TeacherInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 教师信息
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/teacherinfo")
public class TeacherInfoController {
    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysDeptAdminInfoService deptAdminInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:teacherinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 教师业绩支撑选择负责人或者成员，为全体教师
     * @return key:教师id;value:教师姓名-所属二级学院名称
     */
    @GetMapping("/select")
    public R select(){
        //查询教师列表数据
        List<TeacherInfoEntity> teacherList = new ArrayList<>();
        //暂无
        TeacherInfoEntity no = new TeacherInfoEntity();
        no.setTeacherId(0L);
        no.setName("暂无");
        no.setDisabled(true);
        teacherList.add(no);
        teacherList.addAll(teacherInfoService.select());

        return R.ok().put("teacherList", teacherList);
    }

    /**
     * 超级管理员或学校管理员：选择教师，用于添加学院或教研室的成员和负责人
     */
    @GetMapping("/adminSelect")
    @RequiresPermissions("business:teacherinfo:select")
    public R adminSelect(){
        //查询教师列表数据
        List<TeacherInfoEntity> teacherList = new ArrayList<>();
        //暂无
        TeacherInfoEntity no = new TeacherInfoEntity();
        no.setTeacherId(0L);
        no.setName("暂无");
        teacherList.add(no);
        teacherList.addAll(teacherInfoService.adminSelect());

        return R.ok().put("teacherList", teacherList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{teacherId}")
    @RequiresPermissions("business:teacherinfo:info")
    public R info(@PathVariable("teacherId") Long teacherId){
		TeacherInfoEntity teacherInfo = teacherInfoService.getById(teacherId);

        return R.ok().put("teacherInfo", teacherInfo);
    }

    /**
     * 教师查看个人信息
     */
    @RequestMapping("/myInfo/{userId}")
    public R myInfo(@PathVariable("userId") Long userId){
        Long teacherId = teacherInfoService.queryTeaIdByUserId(userId);
        Long notData = 0L;
        if (teacherId == null || notData.equals(teacherId)) {
            return R.error("尊敬的用户：您好！无法查询到您的教师信息，请您重新登录或联系二级学院管理员！");
        }
        TeacherInfoEntity teacherInfo = teacherInfoService.getById(teacherId);

        return R.ok().put("teacherInfo", teacherInfo);
    }

    /**
     * 详情
     */
    @RequestMapping("/details/{teacherId}")
    @RequiresPermissions("business:teacherinfo:info")
    public R details(@PathVariable("teacherId") Long teacherId){
        TeacherInfoEntity teacherInfo = teacherInfoService.details(teacherId);

        return R.ok().put("teacherInfo", teacherInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:teacherinfo:save")
    public R save(@RequestBody TeacherInfoEntity teacherInfo){
        boolean save = teacherInfoService.save(teacherInfo);

        if (save) {
            return R.ok();
        } else {
            return R.error("工号为： " + teacherInfo.getTeacherCode() + " 的教师已存在，请重新添加！");
        }
    }

    /**
     * 修改：updateById 更新字段为null 不更新
     */
    @RequestMapping("/update")
//    @RequiresPermissions("business:teacherinfo:update")
    public R update(@RequestBody TeacherInfoEntity teacherInfo){
		teacherInfoService.updateById(teacherInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:teacherinfo:delete")
//    public R delete(@RequestBody Long[] teacherIds){
    public R delete(@RequestBody String str){
//		teacherInfoService.removeByIds(Arrays.asList(teacherIds));

        JSONObject jsonObject = JSON.parseObject(str);
        JSONArray teaIdJson = (JSONArray) jsonObject.get("ids");
        JSONArray userIdJson = (JSONArray) jsonObject.get("userIds");
        List<Long> teaIds = JSONArray.parseArray(teaIdJson.toJSONString(), Long.class);
        List<Long> userIds = JSONArray.parseArray(userIdJson.toJSONString(), Long.class);
        teacherInfoService.deleteBatch(teaIds, userIds);
        return R.ok();
    }

    /**
     * 导出模板
     */
    @RequestMapping("/downloadExcelTemplate")
    public void downloadExcelTemplate(HttpServletResponse response) {
        List<TeacherInfoEntity> teacherInfoEntities = new ArrayList<>();
        TeacherInfoEntity tmp = new TeacherInfoEntity();
        tmp.setTeacherCode("11111");
        tmp.setName("马云");
        tmp.setSex("男");
        tmp.setBirthDay(new Date());
        tmp.setEntryTime(new Date());
        tmp.setHighEdu("博士");
        tmp.setFirstEdu("本科");
        tmp.setTechnicalPost("飞机研发");
        tmp.setAdministerPost("教授");
        tmp.setResume("爱开坦克");
        tmp.setOther("从事飞机研发");
        teacherInfoEntities.add(tmp);

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        /*
         * 方法1：web导出
         */
        try {
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            response.setHeader("Content-disposition", "attachment;filename=教师信息导入模板.xlsx");
            EasyExcel.write(response.getOutputStream(), TeacherInfoEntity.class).sheet("部门教师").doWrite(teacherInfoEntities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入教师信息
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public R importTeacherInfo(@RequestParam(value = "file") MultipartFile file,
                               @RequestParam(value = "userId") Long userId) {

        if (Objects.isNull(file) || file.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }

        // 错误数据
        List<TeacherInfoEntity> errorData = new ArrayList<>();
        try {
            // 读取excel数据
            TeacherUploadDataListener teacherUploadDataListener = new TeacherUploadDataListener(teacherInfoService, userId);
            EasyExcel.read(file.getInputStream(), TeacherInfoEntity.class, teacherUploadDataListener).sheet().doRead();
            errorData = teacherUploadDataListener.getErrorData();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("解析失败，请刷新后重试!请上传正确的模板!");
        }

        if (errorData.size() > 0) {
            return R.error(444, "以下工号的教师已存在，请核对！其它工号教师已成功保存，无需再次上传！").put("errorData", errorData);
        }
        return R.ok();
    }

    /**
     * 导出教师信息
     * @param teacherIds    指定学生id，为空时导出所有
     * @param response      response对象
     * @return  学校根据二级学院的不同写到不同的sheet中，学院管理员只能导出学院教师数据
     */
    @PostMapping("/export")
    public void export(@RequestBody Long[] teacherIds, HttpServletResponse response) {

        // 获取学生集合
        List<TeacherInfoEntity> teacherList = new ArrayList<>();

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 获取当前用户信息
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        String adminName = userEntity.getUsername();
        // 用于方法2
        ExcelWriter excelWriter = null;
        try {
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("教师信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            // 如果是学校管理员或者我自己(拥有所有权限)
            if ("soldier".equals(adminName) || "wzxyGLY".equals(adminName)) {

                /*
                 * 方法2：如果写到不同的sheet 同一个对象
                 */
                // 指定实体，即为excel表添加了样式，及照片、日期、性别等的格式化
                excelWriter = EasyExcel.write(response.getOutputStream(), TeacherInfoEntity.class).build();

                // 查询所有二级学院
                List<SysDeptEntity> erList = sysDeptService.queryCollegeList();
                String sheetName = "";
                SysDeptEntity deptEntity;
                for (int i = 0; i < erList.size(); i++) {
                    deptEntity = erList.get(i);
                    sheetName = deptEntity.getName();
                    // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName).build();
                    // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                    // 根据部门id及选择的教师id查询数据
                    teacherList = teacherInfoService.queryListByDeptAndIds(deptEntity.getDeptId(), Arrays.asList(teacherIds));
                    excelWriter.write(teacherList, writeSheet);
                }
            } else {
                // 查询指定学院
                SysDeptAdminInfoEntity byUserId = deptAdminInfoService.getByUserId(userEntity.getUserId());
                teacherList = teacherInfoService.queryListByDeptAndIds(byUserId.getDeptId(), Arrays.asList(teacherIds));

                /*
                 * 方法1：web导出
                 */
                try {
                    // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
                    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
                    EasyExcel.write(response.getOutputStream(), TeacherInfoEntity.class).sheet("部门教师").doWrite(teacherList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 返回头像
     * @param imgPath    头像地址
     * @throws Exception
     */
    @RequestMapping(value = "/portrait", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestParam("imgPath") String imgPath) throws Exception {

        byte[] imageContent = OSSUtils.fileToByte(OSSUtils.downloadFileFromOSS(imgPath));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

}
