package com.soldier;

import com.soldier.common.utils.RedisUtils;
import com.soldier.modules.business.entity.TeacherOfStucEntity;
import com.soldier.modules.business.service.SysDeptAdminInfoService;
import com.soldier.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherBusinessTest {
	@Autowired
	private SysDeptAdminInfoService sysDeptAdminInfoService;

	@Test
	public void contextLoads() {
		System.out.println(sysDeptAdminInfoService.getDeptIdByUserId(2L));
	}

	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<>();
		params.put("addDisabled", 1);

		Object haveDisabled = params.get("addDisabled");
		boolean addDisabled = false;
		if (haveDisabled != null && haveDisabled != "") addDisabled = true;

		System.out.println(addDisabled ? 2222222 : 333333333);
	}
}
