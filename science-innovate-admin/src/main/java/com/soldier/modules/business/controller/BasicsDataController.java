package com.soldier.modules.business.controller;

import com.soldier.common.utils.R;
import com.soldier.modules.business.entity.BasicsDataVo;
import com.soldier.modules.business.service.BasicsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author soldier
 * @Date 20-9-15 下午4:27
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:基础数据controller
 */
@RestController
@RequestMapping("business/basics")
public class BasicsDataController  {

    @Autowired
    private BasicsDataService basicsDataService;

    /**
     * 列表
     */
    @RequestMapping("/datas")
    public R datas(){
        BasicsDataVo dataVo = basicsDataService.datas();
        return R.ok().put("dataVo", dataVo);
    }
}
