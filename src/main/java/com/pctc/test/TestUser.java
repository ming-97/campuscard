package com.pctc.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pctc.entity.User;
import com.pctc.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {

	@Autowired
	private UserService userService;
	
	@Test
	public void testGetUsers() {
		
	}
	
	@Test
	public void addUser() {
		User user=new User("小明", 168207101, "男", "362425199703210430", "123456", null, new Date(), null, false);
		System.out.println(userService.addUser(user));
	}
	
	@Test
	public void getUserByUid() {
		System.out.println(userService.getUserByUserNumber(1));
	}
}
