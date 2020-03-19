package com.pctc.service;

import com.pctc.entity.UserRole;

public interface UserRoleService {
	
	public UserRole getByUid(Integer uid);

	public int add(UserRole userRole);
	
	public int update(UserRole userRole);
	
	public int delete(Integer id);
}
