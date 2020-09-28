package com.manipal.service;

import java.util.List;

import com.manipal.model.User;

public interface IUserService {

	void addUser(User user);
	
	List<User> showUser();
	
	User getUserById(int userId);
	
	void deleteUser(int userId);
}
