package com.soldier.modules.business.dao;

import com.soldier.modules.business.entity.SysAttachEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统附件
 * @author soldier
 * @email 583403411@qq.com
 * @date 2020-05-28 10:33:27
 */
@Mapper
public interface SysAttachDao extends BaseMapper<SysAttachEntity> {

    /**
     * 根据功能id查询项目所有附件
     */
    List<SysAttachEntity> queryByFunctionId(String functionId);

}
