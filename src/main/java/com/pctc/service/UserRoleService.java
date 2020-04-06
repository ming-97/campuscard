package com.pctc.service;

import java.util.List;

import com.pctc.entity.User;
import com.pctc.entity.UserRole;

public interface UserRoleService {
	
	public UserRole getByUid(Integer uid);
	
	public List<User> getByRid(Integer rid);

	public int add(UserRole userRole);
	
	public int update(UserRole userRole);
	
	public void deleteByUser(Integer uid);
	
	public void deleteByRole(Integer rid);
}
