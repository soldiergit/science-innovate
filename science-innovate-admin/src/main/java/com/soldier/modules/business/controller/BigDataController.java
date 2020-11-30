package com.soldier.modules.business.controller;

import com.soldier.common.utils.R;
import com.soldier.modules.business.entity.BigDataVo;
import com.soldier.modules.business.service.BigDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author soldier
 * @Date 20-8-27 上午9:56
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:系统首页数据屏展示
 */
@RestController
@RequestMapping("business/bigdata")
public class BigDataController {

    @Autowired
    private BigDataService bigDataService;

    /**
     * 列表
     */
    @RequestMapping("/allSum")
    public R allSum(){
        BigDataVo bigData = bigDataService.allSum();
        return R.ok().put("bigData", bigData);
    }
}
