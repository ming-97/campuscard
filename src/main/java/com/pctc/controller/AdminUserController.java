package com.pctc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pctc.entity.Role;
import com.pctc.entity.RoleExample;
import com.pctc.entity.User;
import com.pctc.entity.UserExample;
import com.pctc.entity.UserExample.Criteria;
import com.pctc.entity.UserRole;
import com.pctc.service.RoleService;
import com.pctc.service.UserRoleService;
import com.pctc.service.UserService;

@Controller
public class AdminUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	// 查询所有用户
	@RequestMapping("userList")
	public String userList(Map<String, Object> map) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andDeleteFlagEqualTo(false);
		List<User> users = userService.getUsers(userExample);
		map.put("users", users);
		RoleExample roleExample = null;
		List<Role> roles = roleService.getRoles(roleExample);
		map.put("roles", roles);
		return "user/user-list";
	}

	// 条件查询所有用户
	@RequestMapping("selectUser")
	public String selectUser(@RequestParam(value = "datemin", required = false) Date datemin,
			@RequestParam(value = "datemax", required = false) Date datemax,
			@RequestParam(value = "userNumber", required = false) Integer userNumber,
			@RequestParam(value = "roleName", required = false) String roleName, Map<String, Object> map) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andDeleteFlagEqualTo(false);
		if (datemin != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(datemin);
		}
		if (datemax != null) {
			criteria.andCreateTimeLessThanOrEqualTo(datemax);
		}
		if (userNumber != null) {
			criteria.andUserNumberEqualTo(userNumber);
		}
		 List<User> users = userService.getUsers(userExample);
		if (!"".equals(roleName)) {
			Integer rid = roleService.getRole(roleName).getRid();
			
			List<User> users1 = userRoleService.getByRid(rid);
			
			/*Set<User> different = new HashSet<>();
			Set<User> users = new HashSet<>();
			different.addAll(userService.getUsers(userExample));
			for (User user : users1) {
				if (!different.add(user)) {
					users.add(user);
				}
			}
			System.out.println(different);
			System.out.println(users);*/
			System.out.println(users.retainAll(users1));
			map.put("users", users);
		} else {
			//List<User> users = userService.getUsers(userExample);
			map.put("users", users);
		}
		RoleExample roleExample = null;
		List<Role> roles = roleService.getRoles(roleExample);
		map.put("roles", roles);

		return "user/user-list";
	}

	@RequestMapping("toUserAdd")
	public String toUserAdd(Map<String, Object> map) {
		RoleExample roleExample = null;
		List<Role> roles = roleService.getRoles(roleExample);
		map.put("roles", roles);
		return "user/user-add";
	}

	// 添加用户
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public boolean addUser(User user, String roleName) {
		boolean flag = false;
		User user2 = userService.getUserByUserNumber(user.getUserNumber());
		if (user2 != null) {
			flag = true;
		} else {
			user.setCreateTime(new Date());
			user.setUpdateTime(null);
			user.setDeleteFlag(false);
			userService.addUser(user);

			UserRole userRole = new UserRole(userService.getUserByUserNumber(user.getUserNumber()).getUid(),
					roleService.getRole(roleName).getRid(), new Date(), null, false);
			userRoleService.add(userRole);
		}
		return flag;
	}

	@RequestMapping("toUserEdit")
	public String toUserEdit(@RequestParam(value = "uid") Integer uid, Map<String, Object> map) {
		Integer rid = userRoleService.getByUid(uid).getRid();
		Role role = roleService.getRole(rid);
		map.put("role", role);
		User user = userService.getUserByUid(uid);
		map.put("user", user);
		RoleExample roleExample = null;
		List<Role> roles = roleService.getRoles(roleExample);
		map.put("roles", roles);
		return "user/user-edit";
	}

	// 修改用户信息
	@ResponseBody
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public boolean editUser(User user, String roleName) {
		user.setUpdateTime(new Date());
		userService.updateUser(user);

		Integer rid = roleService.getRole(roleName).getRid();
		UserRole userRole = userRoleService.getByUid(user.getUid());
		userRole.setRid(rid);
		userRole.setUpdateTime(new Date());
		userRoleService.update(userRole);

		return true;
	}

	@RequestMapping("toChangePassword")
	public String toChangePassword(@RequestParam(value = "uid") Integer uid, Map<String, Object> map) {
		map.put("uid", uid);
		return "user/user-changepassword";
	}

	// 修改登录密码
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public boolean changePassword(Integer uid, String oldPassword, String newPassword) {
		User user = userService.getUserByUid(uid);
		if (oldPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
			user.setUpdateTime(new Date());
			userService.updateUser(user);
			return true;
		}
		return false;
	}

	// 删除用户
	@ResponseBody
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public boolean deleteUser(Integer uid) {
		userService.deleteUser(uid);
		userRoleService.deleteByUser(uid);
		return true;
	}

	// 表单是时间类型的处理方法
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许表单的值为空
	}

}
