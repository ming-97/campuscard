package com.pctc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pctc.entity.User;
import com.pctc.entity.UserExample;
import com.pctc.mapper.UserMapper;
import com.pctc.service.UserService;

@Service("userService")
@Transactional // 事务处理
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly = true) // 只读，不做事务处理。
	public List<User> getUsers(UserExample userExample) {
		List<User> users=userMapper.selectByExample(userExample);
		return users;
	}

	
	public int addUser(User user) {
		int count=0;
		if (user!=null) {
			count=userMapper.insert(user);
		}	
		return count;
	}

	@Transactional(readOnly = true) // 只读，不做事务处理。
	public User getUserByUid(Integer uid) {
		User user=null;
		if (uid!=null) {
			user=userMapper.selectByPrimaryKey(uid);
		}
		return user;
	}

	@Transactional(readOnly = true) // 只读，不做事务处理。
	public User getUserByUserNumber(Integer userNumber) {
		User user=null;
		if (userNumber!=null) {
			
		}
		return user;
	}

	
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateUser(User user) {
		
		return userMapper.updateByPrimaryKey(user);
	}

	
	public int deleteUser(int uid) {
		int count=0;
		User user=null;
		user=userMapper.selectByPrimaryKey(uid);
		if (user!=null) {
			user.setDeleteFlag(true);
			count=userMapper.updateByPrimaryKey(user);
		}
		return count;
	}

}
