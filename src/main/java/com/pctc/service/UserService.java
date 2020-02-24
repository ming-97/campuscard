package com.pctc.service;

import java.util.List;

import com.pctc.entity.User;
import com.pctc.entity.UserExample;

public interface UserService {

	public List<User> getUsers(UserExample userExample);
	
	public int addUser(User user);
	
	public User getUserByUid(Integer uid);
	
	public User getUserByUserNumber(Integer userNumber);

	public User getUserByUserName(String userName);
	
	public int updateUser(User user);
	
	public int deleteUser(int uid);
	
}
