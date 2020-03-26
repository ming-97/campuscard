package com.pctc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pctc.entity.UserRole;
import com.pctc.entity.UserRoleExample;
import com.pctc.entity.UserRoleExample.Criteria;
import com.pctc.mapper.UserRoleMapper;
import com.pctc.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public UserRole getByUid(Integer uid) {
		UserRoleExample userRoleExample = new UserRoleExample();
		Criteria criteria = userRoleExample.createCriteria();
		criteria.andUidEqualTo(uid);

		return userRoleMapper.selectByExample(userRoleExample).get(0);
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
