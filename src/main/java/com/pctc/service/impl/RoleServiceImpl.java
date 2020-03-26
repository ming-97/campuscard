package com.pctc.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pctc.entity.Role;
import com.pctc.entity.RoleExample;
import com.pctc.entity.User;
import com.pctc.entity.UserRole;
import com.pctc.entity.UserRoleExample;
import com.pctc.entity.UserRoleExample.Criteria;
import com.pctc.mapper.RoleMapper;
import com.pctc.mapper.UserRoleMapper;
import com.pctc.service.RoleService;
import com.pctc.service.UserService;

@Service("roleService")
@Transactional // 事务处理
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Transactional(readOnly = true) // 只读，不做事务处理。
	public List<Role> getRoles(RoleExample roleExample) {
		List<Role> roles = roleMapper.selectByExample(roleExample);
		return roles;
	}

	@Transactional(readOnly = true) // 只读，不做事务处理。
	public Role getRole(Integer rid) {
		Role role = null;
		if (rid != null) {
			role = roleMapper.selectByPrimaryKey(rid);
		}
		return role;
	}

	public Role getRole(String roleName) {
		Role role = null;
		RoleExample example = new RoleExample();
		example.createCriteria().andNameEqualTo(roleName);
		List<Role> roles = roleMapper.selectByExample(example);
		// System.out.println(roles.isEmpty());
		if (!roles.isEmpty()) {
			role = roles.get(0);
		}
		return role;
	}

	@Override
	public int addRole(Role role) {
		int count = 0;
		if (role != null) {
			count = roleMapper.insert(role);
		}
		return count;
	}

	@Override
	public int updateRole(Role role) {
		int count = 0;
		if (role != null) {
			count = roleMapper.updateByPrimaryKey(role);
		}
		return count;
	}

	@Override
	public int deleteRole(Integer rid) {
		int count = 0;
		Role role = null;
		role = roleMapper.selectByPrimaryKey(rid);
		if (role != null) {
			count = roleMapper.deleteByPrimaryKey(rid);
		}
		return count;
	}

	@Override
	public Set<String> listRoleNames(Integer userNumber) {
		Set<String> result = new HashSet<>();
		List<Role> roles = getRoles(userNumber);

		for (Role role : roles) {
			result.add(role.getName());
		}

		return result;
	}

	@Override
	public List<Role> getRoles(Integer userNumber) {
		List<Role> roles = new ArrayList<>();
		User user = userService.getUserByUserNumber(userNumber);
		if (null != user) {
			UserRoleExample userRoleExample = new UserRoleExample();
			Criteria criteria = userRoleExample.createCriteria();
			criteria.andUidEqualTo(user.getUid());
			List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
			if (!userRoles.isEmpty()) {
				for (UserRole userRole : userRoles) {
					Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
					roles.add(role);
				}
			}

		}
		return roles;
	}

}
