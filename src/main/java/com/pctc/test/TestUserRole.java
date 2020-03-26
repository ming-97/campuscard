package com.pctc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pctc.entity.Role;
import com.pctc.entity.UserRole;
import com.pctc.service.UserRoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRole {

	@Autowired
	UserRoleService userRoleService;
	
	@Test
	public void getByUid() {
		System.out.println(userRoleService.getByUid(888888));
	}
	
	@Test
	public void add() {
		
	}
}
