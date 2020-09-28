package com.manipal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manipal.exception.QBankAlreadyAttemptedException;
import com.manipal.model.Admin;
import com.manipal.model.Exam;
import com.manipal.model.User;
import com.manipal.proxy.UserProxy;
import com.manipal.proxy.ExamProxy;
import com.manipal.service.IAdminService;

@CrossOrigin("http://localhost:3000")
@RestController
public class AdminController {

	@Autowired
	IAdminService adminService;

	@Autowired
	UserProxy proxy;

	@Autowired
	ExamProxy proxxy;

	@PostMapping("admin-service/admin")
	public String addAdmin(@RequestBody Admin admin) {
		Admin dummy = adminService.getAdminById(admin.getAdminId());
		if (dummy != null) {
			return "Admin already exists";
		}
		adminService.addAdmin(admin);
		return "Admin added successfully";
	}

	@PostMapping("admin-service/admin/check")
	public boolean checkAdmin(@RequestBody Admin admin) {
		Admin dummy = adminService.getAdminById(admin.getAdminId());
		if (dummy == null) {
			return false;
		}
		if (dummy.getAdminName().equals(admin.getAdminName())) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("admin-service/exam")
	public String addExam(@RequestBody Exam exam) {
		proxxy.addExam(exam);
		return "Exam added successfully";
	}

	@PutMapping("admin-service/exam")
	public void updateExam(@RequestBody Exam exam) {
		proxxy.updateExam(exam);
	}

	@GetMapping("admin-service/admin")
	public List<Admin> showAllAdmin() {
		return adminService.showAdmin();
	}

	@GetMapping("admin-service/{adminId}/alluser")
	public List showAllUser(@PathVariable int adminId) {
		Admin admin = adminService.getAdminById(adminId);
		if (admin == null) {
			throw new QBankAlreadyAttemptedException("ALERT!! YOU ARE NOT AN ADMIN");
		}
		List<User> userList = proxy.showAllUser();
		List<Object> viewList = new ArrayList<Object>();
		for (User user : userList) {
			viewList.add("USER ID: " + user.getUserId());
			viewList.add("NAME: " + user.getName());
			viewList.add("EMAIL ID: " + user.getEmail());
			viewList.add("USER QUIZ SCORES : " + user.getScoreList());
			viewList.add("User QUIZ ANSWERS : " + user.getAnswerList());
			viewList.add("===================================");
		}
		return viewList;
	}
	
	@GetMapping("admin-service/admin/{adminId}")
	public Admin showAdminById(@PathVariable int adminId) {
		Admin admin = adminService.getAdminById(adminId);
		if (admin == null) {
			throw new QBankAlreadyAttemptedException("Admin Id is invalid");
		}
		return adminService.getAdminById(adminId);
	}
	

	@GetMapping("admin-service/allexam")
	public List showAllExam() {
		List<Exam> examList = proxxy.showAllExam();
		List<Object> viewList = new ArrayList<Object>();
		for (Exam exam : examList) {
			viewList.add("QUESTION BANK ID: " + exam.getqBankId() + "TOPIC: " + exam.getTitle());
			viewList.add("Q1. " + exam.getQ1());
			viewList.add(exam.getOp11());
			viewList.add(exam.getOp12());
			viewList.add(exam.getOp13());
			viewList.add(exam.getOp14());
			viewList.add("A1. " + exam.getA1());
			
			viewList.add("Q2. " + exam.getQ2());
			viewList.add(exam.getOp21());
			viewList.add(exam.getOp22());
			viewList.add(exam.getOp23());
			viewList.add(exam.getOp24());
			viewList.add("A2. " + exam.getA2());
			
			viewList.add("Q3. " + exam.getQ3());
			viewList.add(exam.getOp31());
			viewList.add(exam.getOp32());
			viewList.add(exam.getOp33());
			viewList.add(exam.getOp34());
			viewList.add("A3. " + exam.getA3());
			
			viewList.add("Q4. " + exam.getQ4());
			viewList.add(exam.getOp41());
			viewList.add(exam.getOp42());
			viewList.add(exam.getOp43());
			viewList.add(exam.getOp44());
			viewList.add("A4. " + exam.getA4());
			
			viewList.add("Q5. " + exam.getQ5());
			viewList.add(exam.getOp51());
			viewList.add(exam.getOp52());
			viewList.add(exam.getOp53());
			viewList.add(exam.getOp54());
			viewList.add("A5. " + exam.getA5());
			viewList.add("======================");
		}
		return viewList;

	}

	// ONLY FOR ADMIN USE

	@DeleteMapping("admin-service/user/{userId}")
	public String deleteUser(@PathVariable int userId) {
			return proxy.deleteUser(userId);
		
	}

	@DeleteMapping("admin-service/exam/{qBankId}")
	public String deleteExam(@PathVariable int qBankId) {
		
			Exam exam = proxxy.getExamById(qBankId);
			if (exam != null) {
				proxxy.deleteExam(qBankId);
				proxy.deleteAnsAndScore(qBankId);
				return "Hi Admin,exam Deleted successfully";
			} else {
				return "There is no such question bank!!!Be careful";
			}
		
	}
	
	//admin login new part
		@GetMapping("admin-service/admin/check/{adminId}/{adminPin}")
		public boolean checkAdmin(@PathVariable int adminId,@PathVariable int adminPin) {
			Admin dummy = adminService.getAdminById(adminId);
			if (dummy == null) {
				return false;
			}
			if (dummy.getAdminPin()==adminPin) {
				return true;
			} else {
				return false;
			}
		}

}