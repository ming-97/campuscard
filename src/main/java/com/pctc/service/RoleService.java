package com.pctc.service;

import java.util.List;
import java.util.Set;

import com.pctc.entity.Role;
import com.pctc.entity.RoleExample;

public interface RoleService {

	public List<Role> getRoles(RoleExample roleExample);
	
	public List<Role> getRoles(Integer userNumber);
	
	public Role getRole(Integer rid);
	
	public Role getRole(String roleName);
	
	public int addRole(Role role);
	
	public int updateRole(Role role);
	
	public int deleteRole(Integer rid);
	
	public Set<String> listRoleNames(Integer userNumber);
}
