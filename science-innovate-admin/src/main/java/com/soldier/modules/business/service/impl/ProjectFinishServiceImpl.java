package com.soldier.modules.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.soldier.common.utils.*;
import com.soldier.modules.business.entity.SysAttachEntity;
import com.soldier.modules.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.soldier.modules.business.dao.ProjectFinishDao;
import com.soldier.modules.business.entity.ProjectFinishEntity;
import org.springframework.transaction.annotation.Transactional;

@Service("projectFinishService")
public class ProjectFinishServiceImpl extends ServiceImpl<ProjectFinishDao, ProjectFinishEntity> implements ProjectFinishService {

    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private SysDeptAdminInfoService sysDeptAdminInfoService;
    @Autowired
    private ProjectCreateService projectCreateService;
    @Autowired
    private SysAttachService sysAttachService;
    @Autowired
    private TeacherOfProcService teacherOfProcService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        /*
         * 如果是教师，则可以根据userId查询到教师id，从而添加约束
         * 如果是二级学院管理员，则查询不到教师id，添加部门约束
         * 如果是系统管理员不添加约束
         */
        Object userId = params.get("userId");
        //系统管理员，拥有最高权限
        if (ObjectUtils.isNotEmpty(userId)) {
            if(Integer.parseInt(userId.toString()) == Constant.SUPER_ADMIN) {
//            return getAllMenuList(null);
            } else {
                Long teaId = teacherInfoService.queryTeaIdByUserId(Long.parseLong(userId.toString()));
                // 如果是教师
                if (teaId!=null)params.put("teaId", teaId);
                else {
                    // 二级学院管理员
                    Long erAdmin = sysDeptAdminInfoService.getDeptIdByUserId(Long.parseLong(userId.toString()));
                    if (erAdmin != null) params.put("deptId", erAdmin);
                }
            }
        }

        //页索引
        Integer page = Integer.parseInt(params.get("page").toString());
        //每页数目
        Integer limit = Integer.parseInt(params.get("limit").toString());

        // 获取记录数
        Integer totalPage = baseMapper.queryCountPage(params);
        if (totalPage <= 0) {
            return new PageUtils(null, totalPage, limit, page);
        }
        //startIndex
        Integer startIndex = 0 + limit * (page - 1);

        params.put("startIndex", startIndex);
        params.put("pageSize", limit);

        // 查询数据
        List<ProjectFinishEntity> tmp = baseMapper.queryPage(params);

        return new PageUtils(tmp, totalPage, limit, page);
    }

    @Override
    @Transactional
    public ProjectFinishEntity details(Long pfId) {

        // 第一步：获取基本数据
        ProjectFinishEntity entity = baseMapper.details(pfId);

        // 第二步：查询项目结题所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("PF-"+pfId));

        // 第三步：完善项目立项信息
        Long pcId = entity.getPcId();
        entity.getProjectCreateEntity().setSysAttachEntityList(sysAttachService.queryByFunctionId("PC-"+pcId));
        entity.getProjectCreateEntity().setPersonInfo(teacherOfProcService.queryPersonInfo(pcId));
        entity.getProjectCreateEntity().setMemberInfoList(teacherOfProcService.queryMemberInfo(pcId));

        return entity;
    }

    /**
     * 重写保存方法，因为还要保存《项目附件》
     */
    @Override
    @Transactional
    public boolean save(ProjectFinishEntity entity) {

        // 第一步：保存项目信息
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        // 结题教师id
        entity.setTeaId(teacherInfoService.queryTeaIdByUserId(entity.getUserId()));
        super.save(entity);

        // 第二步：修改立项项目为结题状态
        projectCreateService.apply(entity.getPcId(), 1);

        // 第三步：保存附件信息
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId("PF-" + entity.getPfId());
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        // 第四步：redis的count数加1
        redisUtils.incr(RedisKeys.getBigDataAllProjectFinishSum());
        redisUtils.incr(RedisKeys.getBigDataAllBusinessSum());
        redisUtils.incr(RedisKeys.getBigDataBusinessNewAddMonthSum(DateUtils.format(new Date(), "yyyy-MM")));

        return true;
    }

    /**
     * 重写更新方法，因为还要更新《项目附件》
     */
    @Override
    @Transactional
    public boolean updateById(ProjectFinishEntity entity) {

        // 第一步：更新项目信息
        entity.setUpdateTime(new Date());
        super.updateById(entity);

        // 修改立项项目为结题状态
        projectCreateService.apply(entity.getPcId(), 1);

        // 删除项目相关附件信息
        String functionId = "PC-" + entity.getPfId();
        sysAttachService.removeByFunctionId(functionId, false);

        // 第五步：新增附件
        List<SysAttachEntity> sysAttachEntityList = entity.getSysAttachEntityList();
        for (int i = 0; i < sysAttachEntityList.size(); i++) {
            sysAttachEntityList.get(i).setFunctionId(functionId);
        }
        sysAttachService.saveBatch(sysAttachEntityList);

        return true;
    }

    /**
     * 重写查询方法，因为还要查询出《中间表关系、项目附件》
     */
    @Override
    @Transactional
    public ProjectFinishEntity getById(Serializable id) {

        // 第一步：获取基本数据
        ProjectFinishEntity entity = super.getById(id);

        // 第二步：查询项目所有附件
        entity.setSysAttachEntityList(sysAttachService.queryByFunctionId("PF-"+id));

        return entity;
    }

    /**
     * 重写更新方法，因为还要删除附件，及将对应立项项目改变为未结题状态
     */
    @Override
    @Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {

        ProjectFinishEntity finish = null;
        // 立项项目id
        Long pcId = null;
        for (Serializable id : idList) {
            // 删除项目结题附件
            sysAttachService.removeByFunctionId("PF-"+id, true);
            // 查询项目立项id
            finish = super.getById(id);
            if (finish != null) {
                pcId = finish.getPcId();
                // 将对应立项项目状态改为未结题
                if (pcId != null) projectCreateService.apply(pcId ,0);
            }
        }

        // 删除项目结题信息
        return super.removeByIds(idList);
    }

    /**
     * 根据项目立项id删除结题信息、附件
     */
    @Override
    @Transactional
    public void removeByPcId(Serializable pcId) {

        // 查询结题信息
        QueryWrapper<ProjectFinishEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pc_id", pcId);
        queryWrapper.select("pf_id");
        ProjectFinishEntity finish = super.getOne(queryWrapper);

        // 如果结题了
        if (finish != null) {
            Long pfId = finish.getPfId();
            // 删除结题附件
            sysAttachService.removeByFunctionId("PF-"+pfId, true);
            // 删除结题信息
            super.removeById(pfId);
        }
    }
}