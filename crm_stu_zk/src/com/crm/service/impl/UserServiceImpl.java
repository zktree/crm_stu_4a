package com.crm.service.impl;

import javax.annotation.Resource;

import com.crm.service.UserService;
import org.springframework.stereotype.Service;

import com.crm.dao.UserDao;
import com.crm.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Override
	public User findUser(String name, String password) {
		return userDao.findUser(name,password);
	}

}
