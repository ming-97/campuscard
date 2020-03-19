package com.pctc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pctc.entity.User;
import com.pctc.entity.UserExample;
import com.pctc.entity.UserExample.Criteria;
import com.pctc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	//查询所有用户
	@RequestMapping("userList")
	public String userList(Map<String, Object> map) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andDeleteFlagEqualTo(false);
		List<User> users = userService.getUsers(userExample);
		map.put("users", users);
		return "user/user-list";
	}

	//条件查询所有用户
	@RequestMapping("selectUser")
	public String selectUser(@RequestParam(value="datemin", required = false) Date datemin, @RequestParam(value="datemax", required = false) Date datemax,
			@RequestParam(value="userNumber", required = false) Integer userNumber, Map<String, Object> map) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andDeleteFlagEqualTo(false);
		if (datemin!=null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(datemin);
		}
		if (datemax!=null) {
			criteria.andCreateTimeLessThanOrEqualTo(datemax);
		}
		if (userNumber!=null) {
			criteria.andUserNumberEqualTo(userNumber);
		}
		List<User> users = userService.getUsers(userExample);
		map.put("users", users);
		return "user/user-list";
	}

	@RequestMapping("toUserAdd")
	public String toUserAdd() {
		return "user/user-add";
	}

	//添加用户
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public boolean addUser(User user) {
		boolean flag = false;
		User user2=userService.getUserByUserNumber(user.getUserNumber());
		if (user2!=null) {
			flag = true;
		} else {
			user.setCreateTime(new Date());
			user.setUpdateTime(null);
			user.setDeleteFlag(false);
			userService.addUser(user);
		}
		return flag;
	}

	@RequestMapping("toUserEdit")
	public String toUserEdit(@RequestParam(value = "uid") Integer uid, Map<String, Object> map) {
		User user = userService.getUserByUid(uid);
		map.put("user", user);
		return "user/user-edit";
	}

	//修改用户信息
	@ResponseBody
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public boolean editUser(User user) {
		user.setUpdateTime(new Date());
		userService.updateUser(user);
		return true;
	}
	
	
	@RequestMapping("toChangePassword")
	public String toChangePassword(@RequestParam(value = "uid") Integer uid, Map<String, Object> map) {
		map.put("uid", uid);
		return "user/user-changepassword";
	}
	
	//修改登录密码
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public boolean changePassword(Integer uid,String password) {
		User user=userService.getUserByUid(uid);
		user.setPassword(password);
		user.setUpdateTime(new Date());
		userService.updateUser(user);
		return true;
	}

	//删除用户
	@ResponseBody
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public boolean deleteUser(Integer uid) {
		userService.deleteUser(uid);
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
