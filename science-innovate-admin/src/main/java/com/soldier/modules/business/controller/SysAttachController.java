package com.soldier.modules.business.controller;

import java.io.*;
import java.util.*;

import com.soldier.common.utils.OSSUtils;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.service.TeacherInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.soldier.modules.business.entity.SysAttachEntity;
import com.soldier.modules.business.service.SysAttachService;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统附件
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@RestController
@RequestMapping("business/sysattach")
public class SysAttachController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysAttachService sysAttachService;
    @Autowired
    private TeacherInfoService teacherInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:sysattach:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysAttachService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attachId}")
    @RequiresPermissions("business:sysattach:info")
    public R info(@PathVariable("attachId") Long attachId){
		SysAttachEntity sysAttach = sysAttachService.getById(attachId);

        return R.ok().put("sysAttach", sysAttach);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:sysattach:save")
    public R save(@RequestBody SysAttachEntity sysAttach){
		sysAttachService.save(sysAttach);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:sysattach:update")
    public R update(@RequestBody SysAttachEntity sysAttach){
		sysAttachService.updateById(sysAttach);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:sysattach:delete")
    public R delete(@RequestBody Long[] attachIds){
		sysAttachService.removeByIds(Arrays.asList(attachIds));

        return R.ok();
    }

    /**
     * 文件上传
     * @param files     文件集合
     * @param request   request对象
     * @return          返回一个sysAttachEntityList对象到前端，到时候与项目信息一起提交到后台再保存
     */
    @PostMapping(value = "/upload")
    public Object uploadFile(@RequestParam("file") List<MultipartFile> files, final HttpServletRequest request) {
        String userId = request.getParameter("userId");
        TeacherInfoEntity teacherInfo = teacherInfoService.queryTeacherCodeAndNameByUserId(userId);
        if (teacherInfo == null || teacherInfo.getTeacherCode() == null || teacherInfo.getName() == null) {
            return R.error("尊敬的用户：您好！无法查询到您的教师信息，请您重新登录或联系二级学院管理员！");
        }

        // fileType = /AP/  /TI/    ...
        String fileType = request.getParameter("fileType");

        /*
         * 文件保存路径：
         *  教师工号+
         *      ==》教师信息-TI；
         *      ==》学生论文-AP；
         *      ==》大创项目-IP；
         *      ==》项目立项-PC；
         *      ==》项目结题-PF；
         *      ==》科研成果-RG；
         *      ==》学生竞赛-SC；
         *      ==》教师竞赛-TC；
         *      ==》教材-TM；
         *      ==》教学成果-TA；
         *      ==》教学荣誉-TH；
         *      ==》赛事-MATCH；
         * 例子：
         *  17041-莫智懿/AP/教师logo.jpg
         */
        // 17041-莫智懿/AP/
        String UPLOAD_FILES_PATH = teacherInfo.getTeacherCodeAndName() + fileType;
        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }

        SysAttachEntity sysAttachEntity = null;
        for(MultipartFile file : files){
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件名前缀
            String filePrefix = fileName.substring(0, fileName.lastIndexOf("."));
            // 获取文件的后缀=".jpg"
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            // 上传文件 返回真实路径
            String fileAbsolutePath = OSSUtils.upload2OSS(file, filePrefix, fileSuffix, UPLOAD_FILES_PATH);
            sysAttachEntity = new SysAttachEntity();
            sysAttachEntity.setAttachPath(fileAbsolutePath);
            sysAttachEntity.setAttachName(fileName);
            sysAttachEntity.setIsDel(0);
        }

        return R.ok("文件上传成功").put("sysAttachEntity", sysAttachEntity);
    }

    /**
     * 教师上传个人照片
     * @return  返回照片路径
     */
    @PostMapping(value = "/uploadPortrait")
    public R importStuImage(@RequestParam(value = "file") MultipartFile file, final HttpServletRequest request) {

        if (Objects.isNull(file) || file.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }

        // 获取文件后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));

        String fileName = request.getParameter("teacherCode");
        String teacherCode = fileName + "-";
        String teacherName = request.getParameter("name") + "/portrait/";
        // 设置新的照片文件名称为教师工号
        String pathname = teacherCode + teacherName + fileName + suffix;
        //获取上传图片的名字
        File dest = new File(pathname);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.ok("照片上传成功")
                .put("savePath", pathname);
    }

    /**
     * 删除文件夹中的文件
     */
    @PostMapping("/deleteFile")
    public R deleteFolderFiles(@RequestParam("filePath") String filePath){
        boolean b = OSSUtils.deleteFile(filePath);
        if (b)logger.info("删除了文件，地址为：{}", filePath);

        return R.ok();
    }

    /**
     * 文件下载
     */
    @PostMapping(value = "/download")
    public void downloadFile(final HttpServletResponse response, @RequestParam("filePath") String filePath) {
        logger.info("从OSS下载文件，地址为：{}", filePath);
        OSSUtils.downloadFileFromOSS(response, filePath);
    }
}
