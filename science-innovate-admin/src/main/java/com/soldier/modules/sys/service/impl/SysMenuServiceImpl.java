package com.soldier.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldier.common.utils.Constant;
import com.soldier.common.utils.MapUtils;
import com.soldier.common.utils.RedisKeys;
import com.soldier.common.utils.RedisUtils;
import com.soldier.modules.sys.dao.SysMenuDao;
import com.soldier.modules.sys.entity.SysMenuEntity;
import com.soldier.modules.sys.service.SysMenuService;
import com.soldier.modules.sys.service.SysRoleMenuService;
import com.soldier.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private RedisUtils redisUtils;

	/**
	 * 2020-08-14利用redis优化：所有菜单列表
	 */
	@Override
	public List<SysMenuEntity> list() {

		// 先查询缓存中是否有数据
		long start = System.currentTimeMillis();
		List<SysMenuEntity> list = redisUtils.getList(RedisKeys.getSysMenuAllList(), SysMenuEntity.class);

		if (list != null && list.size() != 0) {
			logger.info("SysMenu-list：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
			// 如果有数据 直接返回
			return list;
		} else {
			// 如果没有 则先保存到redis再返回
			List<SysMenuEntity> menuList = super.list();
			for(SysMenuEntity sysMenuEntity : menuList){
				SysMenuEntity parentMenuEntity = super.getById(sysMenuEntity.getParentId());
				if(parentMenuEntity != null){
					sysMenuEntity.setParentName(parentMenuEntity.getName());
				}
			}

			// 不设置过期时长 永久有效
			redisUtils.set(RedisKeys.getSysMenuAllList(), menuList, RedisUtils.NOT_EXPIRE);
			logger.info("SysMenu-list：查询了redis缓存，没查到数据～～～～～～～～～消耗时间：{}", System.currentTimeMillis() - start);
			return menuList;
		}
	}
	
	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	/**
	 * 2020-08-14利用redis优化：获取不包含按钮的菜单列表
	 */
	@Override
	public List<SysMenuEntity> queryNotButtonList() {

		// 先查询缓存中是否有数据
		long start = System.currentTimeMillis();
		List<SysMenuEntity> list = redisUtils.getList(RedisKeys.getSysMenuNotButtonList(), SysMenuEntity.class);

		if (list != null && list.size() != 0) {
			logger.info("SysMenu-queryNotButtonList：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
			// 如果有数据 直接返回
			return list;
		} else {
			// 如果没有 则先保存到redis再返回
			List<SysMenuEntity> notButtonList = baseMapper.queryNotButtonList();

			// 不设置过期时长 永久有效
			redisUtils.set(RedisKeys.getSysMenuNotButtonList(), notButtonList, RedisUtils.NOT_EXPIRE);
			logger.info("SysMenu-queryNotButtonList：查询了redis缓存，没查到数据～～～～～～～～～消耗时间：{}", System.currentTimeMillis() - start);
			return notButtonList;
		}
	}

	/**
	 * 2020-08-14利用redis优化：获取用户菜单列表
	 */
	@Override
	public List<SysMenuEntity> getUserMenuList(Long userId) {

		// 先查询缓存中是否有数据
		long start = System.currentTimeMillis();
		List<SysMenuEntity> list = redisUtils.getList(RedisKeys.getSysMenuUserMenuList(userId), SysMenuEntity.class);

		if (list != null && list.size() != 0) {
			logger.info("SysMenu-getUserMenuList：查询了redis缓存，有数据！消耗时间：{}", System.currentTimeMillis() - start);
			// 如果有数据 直接返回
			return list;
		} else {
			// 如果没有 则先保存到redis再返回
			//系统管理员，拥有最高权限
			if (userId == Constant.SUPER_ADMIN) {
				List<SysMenuEntity> menuList = getAllMenuList(null);

				// 不设置过期时长 永久有效
				redisUtils.set(RedisKeys.getSysMenuUserMenuList(userId), menuList, RedisUtils.NOT_EXPIRE);
				logger.info("SysRoleMenu-getUserMenuList：查询了redis缓存，没查到数据，是系统管理员@@@@@@@@@@消耗时间：{}", System.currentTimeMillis() - start);
				return menuList;

			}

			//用户菜单列表
			List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
			List<SysMenuEntity> menuList = getAllMenuList(menuIdList);

			// 不设置过期时长 永久有效
			redisUtils.set(RedisKeys.getSysMenuUserMenuList(userId), menuList, RedisUtils.NOT_EXPIRE);
			logger.info("SysRoleMenu-getUserMenuList：查询了redis缓存，没查到数据，不是系统管理员#########消耗时间：{}", System.currentTimeMillis() - start);
			return menuList;
		}
	}

	/**
	 * 新增、修改、删除时，都要删除redis缓存
	 */
	@Override
	public boolean save(SysMenuEntity entity) {

		// 先保存mysql
		super.save(entity);

		// 再删除redis
		removeRedisData();

		return true;
	}

	@Override
	public boolean updateById(SysMenuEntity entity) {

		// 先更新mysql
		super.updateById(entity);

		// 再删除redis
		removeRedisData();

		return true;
	}

	@Override
	public void delete(Long menuId){
		//删除菜单
		this.removeById(menuId);
		//删除菜单与角色关联
		sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));

		// 删除redis
		removeRedisData();
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
		
		for(SysMenuEntity entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}

	/**
	 * 删除redis中关于菜单全部的数据:science_innovate_sys_menu_开头的
	 */
	private void removeRedisData() {
		redisUtils.deleteBatch(RedisKeys.getSysMenuPublicKey());
		logger.info("删除redis中关于菜单全部的数据");
	}
}
