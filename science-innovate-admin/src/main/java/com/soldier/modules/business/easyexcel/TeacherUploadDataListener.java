package com.soldier.modules.business.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import com.soldier.modules.business.service.TeacherInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author soldier
 * @Date 20-7-7 上午11:23
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:教师数据导入时的监听器
 * 有个很重要的点 TeacherUploadDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 */
public class TeacherUploadDataListener extends AnalysisEventListener<TeacherInfoEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherUploadDataListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    List<TeacherInfoEntity> teacherInfoEntityList = new ArrayList<TeacherInfoEntity>();
    // 返回的错误数据
    private List<TeacherInfoEntity> errorData = new ArrayList<>();

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private TeacherInfoService teacherInfoService;
    private Long userId;
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param teacherInfoService
     */
    public TeacherUploadDataListener(TeacherInfoService teacherInfoService, Long userId) {
        this.teacherInfoService = teacherInfoService;
        this.userId = userId;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(TeacherInfoEntity data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        teacherInfoEntityList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (teacherInfoEntityList.size() >= BATCH_COUNT) {
            List<TeacherInfoEntity> error = teacherInfoService.insertBatch(teacherInfoEntityList, userId);
            // 判断错误
            if (error != null && !error.isEmpty()) {
                errorData.addAll(error);
                // 存储完成清理 list
                error.clear();
            }
            // 存储完成清理 list
            teacherInfoEntityList.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *      如果执行invoke()解析时，数据大小达到BATCH_COUNT，此时的teacherInfoEntityList大小 = 总大小 - BATCH_COUNT * n   ==>达到几次，n等于几
     *      如果执行invoke()解析时，数据大小未达到BATCH_COUNT，此时的teacherInfoEntityList大小 = 总大小
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        List<TeacherInfoEntity> error = teacherInfoService.insertBatch(teacherInfoEntityList, userId);
        // 判断错误
        if (error != null && !error.isEmpty()) {
            errorData.addAll(error);
            // 存储完成清理 list
            error.clear();
        }
        LOGGER.info("所有数据解析完成！");
    }

    public List<TeacherInfoEntity> getErrorData() {
        return errorData;
    }

    public void setErrorData(List<TeacherInfoEntity> errorData) {
        this.errorData = errorData;
    }
}