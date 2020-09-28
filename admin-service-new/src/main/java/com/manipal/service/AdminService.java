package com.manipal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manipal.model.Admin;
import com.manipal.repository.IAdminRepository;


@Service
public class AdminService implements IAdminService{

	@Autowired
	IAdminRepository repository;
	
	
	@Override
	public void addAdmin(Admin admin) {
		repository.save(admin);
	}

	@Override
	public List<Admin> showAdmin() {
		return repository.findAll();
	}

	@Override
	public Admin getAdminById(int adminId) {
		return repository.findById(adminId).orElse(null);
	}


	
}