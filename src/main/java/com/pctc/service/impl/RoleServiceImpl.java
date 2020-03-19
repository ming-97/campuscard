package com.pctc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pctc.entity.Role;
import com.pctc.entity.RoleExample;
import com.pctc.mapper.RoleMapper;
import com.pctc.service.RoleService;

@Service("roleService")
@Transactional // 事务处理
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(readOnly = true) // 只读，不做事务处理。
	public List<Role> getRoles(RoleExample roleExample) {
		List<Role> roles=roleMapper.selectByExample(roleExample);
		return roles;
	}

	@Transactional(readOnly = true) // 只读，不做事务处理。
	public Role getRole(Integer rid) {
		Role role=null;
		if (rid!=null) {
			role=roleMapper.selectByPrimaryKey(rid);
		}
		return role;
	}

	@Override
	public int addRole(Role role) {
		int count=0;
		if (role!=null) {
			count=roleMapper.insert(role);
		}
		return count;
	}

	@Override
	public int updateRole(Role role) {
		int count=0;
		if (role!=null) {
			count=roleMapper.updateByPrimaryKey(role);
		}
		return count;
	}

	@Override
	public int deleteRole(Integer rid) {
		int count=0;
		Role role=null;
		role=roleMapper.selectByPrimaryKey(rid);
		if (role!=null) {
			role.setDeleteFlag(true);
			count=roleMapper.updateByPrimaryKey(role);
		}
		return count;
	}

}
