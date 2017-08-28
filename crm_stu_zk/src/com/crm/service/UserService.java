package com.crm.service;

import com.crm.model.User;

public interface UserService {

	User findUser(String name, String password);

}
