package com.soldier.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.RedisKeys;
import com.soldier.common.utils.RedisUtils;
import com.soldier.modules.sys.dao.SysRoleMenuDao;
import com.soldier.modules.sys.entity.SysRoleMenuEntity;
import com.soldier.modules.sys.service.SysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RedisUtils redisUtils;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//先删除角色与菜单关系
		deleteBatch(new Long[]{roleId});
		// 再删除redis
		removeRedisData();

		if(menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		for(Long menuId : menuIdList){
			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setMenuId(menuId);
			sysRoleMenuEntity.setRoleId(roleId);

			this.save(sysRoleMenuEntity);
		}
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return baseMapper.queryMenuIdList(roleId);
	}

	@Override
	public int deleteBatch(Long[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}

	/**
	 * 删除redis中关于菜单全部的数据:science_innovate_sys_menu_开头的
	 */
	private void removeRedisData() {
		redisUtils.deleteBatch(RedisKeys.getSysMenuPublicKey());
		logger.info("删除redis中关于菜单全部的数据");
	}
}
