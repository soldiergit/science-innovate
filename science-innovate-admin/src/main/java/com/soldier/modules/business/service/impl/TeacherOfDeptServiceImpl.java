package com.soldier.modules.business.service.impl;

import com.soldier.common.utils.RedisKeys;
import com.soldier.common.utils.RedisUtils;
import com.soldier.modules.business.entity.SysDeptEntity;
import com.soldier.modules.business.entity.TeacherInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.PageUtils;
import com.soldier.common.utils.Query;

import com.soldier.modules.business.dao.TeacherOfDeptDao;
import com.soldier.modules.business.entity.TeacherOfDeptEntity;
import com.soldier.modules.business.service.TeacherOfDeptService;


@Service("teacherOfDeptService")
public class TeacherOfDeptServiceImpl extends ServiceImpl<TeacherOfDeptDao, TeacherOfDeptEntity> implements TeacherOfDeptService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherOfDeptEntity> page = this.page(
                new Query<TeacherOfDeptEntity>().getPage(params),
                new QueryWrapper<TeacherOfDeptEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 2020-08-14利用redis优化：查询已加入部门的教师
     */
    @Override
    public List<TeacherInfoEntity> queryChoiceTeaList(Map<String, Object> params) {
        Object deptId = params.get("deptId");
        Object userId = params.get("userId");

        // 先查询缓存中是否有数据
        long start = System.currentTimeMillis();
        List<TeacherInfoEntity> list = redisUtils.getList(RedisKeys.getTeacherInfoChoiceList(deptId, userId), TeacherInfoEntity.class);

        if (list != null && list.size() != 0) {
            logger.info("TeacherOfDeptService-queryChoiceTeaList：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
            // 如果有数据 直接返回
            return list;
        } else {
            // 如果没有 则先保存到redis再返回
            List<TeacherInfoEntity> teacherInfoEntities = baseMapper.queryChoiceTeaList(deptId, userId);

            // 不设置过期时长 永久有效
            redisUtils.set(RedisKeys.getTeacherInfoChoiceList(deptId, userId), teacherInfoEntities, RedisUtils.NOT_EXPIRE);
            logger.info("TeacherOfDeptService-queryChoiceTeaList：查询了redis缓存，没查到数据，消耗时间：{}", System.currentTimeMillis() - start);
            return teacherInfoEntities;
        }
    }

    /**
     * 2020-08-14利用redis优化：查询已加入部门的教师id
     */
    @Override
    public List<Long> queryChoiceTeaIds(Map<String, Object> params) {
        Object deptId = params.get("deptId");
        Object userId = params.get("userId");

        // 先查询缓存中是否有数据
        long start = System.currentTimeMillis();
        List<Long> list = redisUtils.getList(RedisKeys.getTeacherIdChoiceList(deptId, userId), Long.class);

        if (list != null && list.size() != 0) {
            logger.info("TeacherOfDeptService-queryChoiceTeaIds：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
            // 如果有数据 直接返回
            return list;
        } else {
            // 如果没有 则先保存到redis再返回
            List<Long> teaIds = baseMapper.queryChoiceTeaIds(deptId, userId);

            // 不设置过期时长 永久有效
            redisUtils.set(RedisKeys.getTeacherIdChoiceList(deptId, userId), teaIds, RedisUtils.NOT_EXPIRE);
            logger.info("TeacherOfDeptService-queryChoiceTeaIds：查询了redis缓存，没查到数据，消耗时间：{}", System.currentTimeMillis() - start);
            return teaIds;
        }
    }

    /**
     * 添加、修改、删除时都要删除reids中的数据
     */
    @Override
    public boolean save(TeacherOfDeptEntity entity) {
        // 数据库操作
        super.save(entity);
        // 删除redis
        removeRedisData();
        return true;
    }
    @Override
    public boolean saveBatch(Collection<TeacherOfDeptEntity> entityList) {
        // 数据库操作
        super.saveBatch(entityList);
        // 删除redis
        removeRedisData();
        return true;
    }
    @Override
    public boolean updateById(TeacherOfDeptEntity entity) {
        // 数据库操作
        super.updateById(entity);
        // 删除redis
        removeRedisData();
        return true;
    }
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        // 数据库操作
        super.removeByIds(idList);
        // 删除redis
        removeRedisData();
        return true;
    }
    @Override
    public void removeByDeptId(Long deptId) {
        baseMapper.removeByDeptId(deptId);
        // 删除redis
        removeRedisData();
    }

    /**
     * 删除redis中关于教师全部的数据:science_innovate_teacher_开头的
     */
    private void removeRedisData() {
        redisUtils.deleteBatch(RedisKeys.getTeacherPublicKey());
        logger.info("删除redis中关于教师全部的数据");
    }

}