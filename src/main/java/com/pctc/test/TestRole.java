package com.pctc.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pctc.entity.Role;
import com.pctc.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRole {

	@Autowired
	private RoleService roleService;
	
	@Test
	public void addRole() {
		Role role=new Role("超级管理员", "拥有最高权限", new Date(), null, false);
		System.out.println(roleService.addRole(role));
	}
	
	@Test
	public void getRole() {
		System.out.println(roleService.getRole(1));
	}
	
	@Test
	public void update() {
		
	}
	
	@Test
	public void delete() {
		System.out.println(roleService.deleteRole(1));
	}
	
	@Test
	public void listRoleNames() {
		System.out.println(roleService.listRoleNames(11));
	}
	
	@Test
	public void getRoles() {
		System.out.println(roleService.getRoles(888888));
	}
}
