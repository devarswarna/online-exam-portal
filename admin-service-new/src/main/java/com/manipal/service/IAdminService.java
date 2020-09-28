package com.manipal.service;

import java.util.List;

import com.manipal.model.Admin;

public interface IAdminService {
	
	void addAdmin(Admin admin);
	
	List<Admin> showAdmin();

	Admin getAdminById(int adminId);
	
	//Admin getAdminById(int adminId);

}