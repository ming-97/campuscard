package com.pctc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pctc.entity.User;
import com.pctc.entity.UserRole;
import com.pctc.entity.UserRoleExample;
import com.pctc.entity.UserRoleExample.Criteria;
import com.pctc.mapper.UserRoleMapper;
import com.pctc.service.UserRoleService;
import com.pctc.service.UserService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserService userService;

	@Override
	public UserRole getByUid(Integer uid) {
		UserRoleExample userRoleExample = new UserRoleExample();
		Criteria criteria = userRoleExample.createCriteria();
		criteria.andUidEqualTo(uid);

		return userRoleMapper.selectByExample(userRoleExample).get(0);
	}
	
	@Override
	public List<User> getByRid(Integer rid) {
		UserRoleExample userRoleExample = new UserRoleExample();
		userRoleExample.createCriteria().andRidEqualTo(rid);
		List<UserRole> userRoles=userRoleMapper.selectByExample(userRoleExample);
		List<User> users=new ArrayList<User>();
		for(UserRole userRole:userRoles) {
			User user=userService.getUserByUid(userRole.getUid());
			users.add(user);
		}
		return users;
	}

	@Override
	public int add(UserRole userRole) {
		int count = 0;
		if (userRole != null) {
			count = userRoleMapper.insert(userRole);
		}

		return count;
	}

	@Override
	public int update(UserRole userRole) {
		int count = 0;
		if (userRole != null) {
			count = userRoleMapper.updateByPrimaryKey(userRole);
		}

		return count;
	}

	@Override
	public void deleteByUser(Integer uid) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUidEqualTo(uid);
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		if (!userRoles.isEmpty()) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteByPrimaryKey(userRole.getId());
			}
		}
	}

	@Override
	public void deleteByRole(Integer rid) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andRidEqualTo(rid);
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		if (!userRoles.isEmpty()) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteByPrimaryKey(userRole.getId());
			}
		}
	}

	

}
