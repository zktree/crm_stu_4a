package com.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.crm.model.User;

public interface UserDao {

	User findUser(@Param("name") String name, @Param("pwd") String password);

}
