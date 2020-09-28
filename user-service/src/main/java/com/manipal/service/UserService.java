package com.manipal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manipal.model.User;
import com.manipal.repository.IUserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepository repository;
	
	@Override
	public void addUser(User user) {
		repository.save(user);
	}

	@Override
	public List<User> showUser() {
		return repository.findAll();
	}

	@Override
	public User getUserById(int userId) {
		return repository.findById(userId).orElse(null);
	}

	@Override
	public void deleteUser(int userId) {
		repository.deleteById(userId);
		
	}
}
