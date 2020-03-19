package com.pctc.service;

import java.util.List;

import com.pctc.entity.Role;
import com.pctc.entity.RoleExample;

public interface RoleService {

	public List<Role> getRoles(RoleExample roleExample);
	
	public Role getRole(Integer rid);
	
	public int addRole(Role role);
	
	public int updateRole(Role role);
	
	public int deleteRole(Integer rid);
	
	
}
