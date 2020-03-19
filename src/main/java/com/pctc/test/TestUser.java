package com.pctc.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pctc.entity.User;
import com.pctc.entity.UserExample;
import com.pctc.entity.UserExample.Criteria;
import com.pctc.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {

	@Autowired
	private UserService userService;
	
	@Test
	public void GetUsers() {
		UserExample userExample=new UserExample();
		Criteria criteria=userExample.createCriteria();
		criteria.andDeleteFlagEqualTo(false);
		List<User> users=userService.getUsers(userExample);
		for (User user : users) {
			System.out.println(user);
		}
		
	}
	
	@Test
	public void addUser() {
		User user=new User("小明", 168207101, "男", "362425199703210430", "123456", null, new Date(), null, false);
		System.out.println(userService.addUser(user));
	}
	
	@Test
	public void getUserByUid() {
		System.out.println(userService.getUserByUid(1));
	}
	
	@Test
	public void getUserByUserNumber() {
		/*UserExample userExample=new UserExample();
		Criteria criteria=userExample.createCriteria();
		criteria.andUserNumberEqualTo(168207101);
		System.out.println(userService.getUsers(userExample));*/
		System.out.println(userService.getUserByUserNumber(168207101));
	}
	
	@Test
	public void updateUser() {
		User user=userService.getUserByUid(1);
		user.setUserNumber(168207302);
		userService.updateUser(user);
	}
	
	@Test
	public void deleteUser() {
		userService.deleteUser(1);
	}
	
}
