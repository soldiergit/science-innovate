package com.soldier.modules.innovate;

import com.soldier.common.utils.R;
import com.soldier.common.utils.RedisKeys;
import com.soldier.common.utils.RedisUtils;
import com.soldier.modules.sys.controller.AbstractController;
import com.soldier.modules.sys.entity.SysUserEntity;
import com.soldier.modules.sys.form.SysLoginForm;
import com.soldier.modules.sys.service.SysUserService;
import com.soldier.modules.sys.service.SysUserTokenService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author soldier
 * @Date 20-12-1 下午5:17
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:用于配合双创系统(http://47.112.103.217:8001/)的相关接口
 */
@RestController
public class InnovateController extends AbstractController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 2020-11-30：实现从双创系统直接登录过来,只需要账号即可
     *      第一步：将当前教师用户token存入redis中
     */
    @PostMapping("/innovate/setUserTokenToRedis")
    public R setUserTokenToRedis(@RequestBody SysLoginForm form)throws IOException {
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在
        if(user == null) {
            return R.error("尊敬的教师您好！您未在科创管理信息系统中创建账号，请联系本学院管理员为您在科创系统中创建账号～");
        }

        //账号锁定
        if(user.getStatus() == 0){
            return R.error("账号已被锁定,请联系管理员");
        }

        // 如果一切正常，生成token，并保存到数据库和redis中
        R r = sysUserTokenService.createToken(user.getUserId());
        // 一分钟内有效
        redisUtils.set(RedisKeys.getUserToken(), r.get("token"), 60);

        return R.ok();
    }

    /**
     * 2020-11-30：实现从双创系统直接登录过来,只需要账号即可
     *      第二步：获取用户token
     */
    @GetMapping("/innovate/getUserToken")
    public R innovateComeHereLogin() {
        // 查询token，并删除
        String token = redisUtils.get(RedisKeys.getUserToken());
        redisUtils.delete(RedisKeys.getUserToken());

        if (StringUtils.isEmpty(token)) return R.error();

        // 返回浏览器，并设置12小时后过期
        return R.ok().put("token", token).put("expire", 3600 * 12);
    }
}
