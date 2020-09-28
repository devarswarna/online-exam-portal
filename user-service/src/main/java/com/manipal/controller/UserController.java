package com.manipal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manipal.exception.QBankAlreadyAttemptedException;
import com.manipal.model.Exam;
import com.manipal.model.User;
import com.manipal.proxy.ExamProxy;
import com.manipal.service.IUserService;

@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	IUserService userService;

	@Autowired
	ExamProxy proxy;

	@PostMapping("user-service/user")
	public String addUser(@RequestBody User user) {
		User dummy = userService.getUserById(user.getUserId());
		if (dummy != null) {
			dummy.setUserAns1(user.getUserAns1());
			dummy.setUserAns2(user.getUserAns2());
			dummy.setUserAns3(user.getUserAns3());
			dummy.setUserAns4(user.getUserAns4());
			dummy.setUserAns5(user.getUserAns5());
			userService.addUser(dummy);
			return "User already exists";
		} else {
			userService.addUser(user);
			return "User added successfully";
		}
	}

	@PostMapping("user-service/user/answer")
	public String addUserAnswer(@RequestBody User user) {
		User dummy = userService.getUserById(user.getUserId());
		if (dummy != null) {
			dummy.setUserAns1(user.getUserAns1());
			dummy.setUserAns2(user.getUserAns2());
			dummy.setUserAns3(user.getUserAns3());
			dummy.setUserAns4(user.getUserAns4());
			dummy.setUserAns5(user.getUserAns5());
			userService.addUser(dummy);
			return "Answers updated";
		} else {
			return "User does not exist";
		}
	}

	@GetMapping("user-service/showalluser")
	public List<User> showAllUser() {
		return userService.showUser();
	}
	
	@GetMapping("user-service/userbyid/{userId}")
	public User showUser(@PathVariable int userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new QBankAlreadyAttemptedException("User Id is invalid");
		}
		return user;
	}

	@GetMapping("user-service/user/{userId}")
	public List showUserById(@PathVariable int userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new QBankAlreadyAttemptedException("User Id is invalid");
		}
		List<Object> userList = new ArrayList<Object>();
		Exam exam;
		for (Integer i : user.getScoreList().keySet()) {
			userList.add(" ");
			userList.add("Quiz " + i + " Score : " + user.getScoreList().get(i));
			userList.add("User Answers : " + user.getAnswerList().get(i));
			if (user.getScoreList().get(i) != null) {
				exam = proxy.getExam(i);
				userList.add("Topic : " +exam.getTitle());
				userList.add("Q1: "+exam.getQ1());
				userList.add(exam.getOp11());
				userList.add(exam.getOp12());
				userList.add(exam.getOp13());
				userList.add(exam.getOp14());
				userList.add("A1: "+exam.getA1());
				
				userList.add("Q2: "+exam.getQ2());
				userList.add(exam.getOp21());
				userList.add(exam.getOp22());
				userList.add(exam.getOp23());
				userList.add(exam.getOp24());
				userList.add("A2: "+exam.getA2());
				
				userList.add("Q3: "+exam.getQ3());
				userList.add(exam.getOp31());
				userList.add(exam.getOp32());
				userList.add(exam.getOp33());
				userList.add(exam.getOp34());
				userList.add("A3: "+exam.getA3());
				
				userList.add("Q4: "+exam.getQ4());
				userList.add(exam.getOp41());
				userList.add(exam.getOp42());
				userList.add(exam.getOp43());
				userList.add(exam.getOp44());
				userList.add("A4: "+exam.getA4());
				
				userList.add("Q5: "+exam.getQ5());
				userList.add(exam.getOp51());
				userList.add(exam.getOp52());
				userList.add(exam.getOp53());
				userList.add(exam.getOp54());
				userList.add("A5: "+exam.getA5());
			}
		}

		return userList;
	}

	@GetMapping("user-service/{userId}/allexam")
	public List showAllExam(@PathVariable int userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new QBankAlreadyAttemptedException("User does not exist");
		}
		List<Exam> examList = proxy.showAllExam();
		List<Object> viewList = new ArrayList<Object>();
		for (Exam exam : examList) {
			viewList.add("Question Bank: "+exam.getqBankId());
			viewList.add("Q1: "+exam.getQ1());
			viewList.add("Q2: "+exam.getQ2());
			viewList.add("Q3: "+exam.getQ3());
			viewList.add("Q4: "+exam.getQ4());
			viewList.add("Q5: "+exam.getQ5());
		}
		return viewList;
	}

	@GetMapping("user-service/{userId}/exam/{qBankId}")
	public User getMarks(@PathVariable int userId, @PathVariable int qBankId) {
		User user = userService.getUserById(userId);
		if(user==null) {
			throw new QBankAlreadyAttemptedException("No such user ID exists");
		}
		Exam bean = proxy.getExam(qBankId);
		if (bean == null) {
			throw new QBankAlreadyAttemptedException("No such Question Bank Exists");
		}
		int score = 0;
		if (user.getUserAns1().equals(bean.getA1())) {
			score++;
		}
		if (user.getUserAns2().equals(bean.getA2())) {
			score++;
		}
		if (user.getUserAns3().equals(bean.getA3())) {
			score++;
		}
		if (user.getUserAns4().equals(bean.getA4())) {
			score++;
		}
		if (user.getUserAns5().equals(bean.getA5())) {
			score++;
		}
		HashMap<Integer, String> temp = new HashMap<>();
		if (user.getScoreList() == null) {
			user.setScoreList(temp);
		}

		HashMap<Integer, String> tempAns = new HashMap<>();
		if (user.getAnswerList() == null) {
			user.setAnswerList(tempAns);
		}
		String strAns = "1." + user.getUserAns1() + " 2." + user.getUserAns2() + " 3." + user.getUserAns3() + " 4."
				+ user.getUserAns4() + " 5." + user.getUserAns5();

		if (user.getAnswerList().get(qBankId) == null) {
			tempAns = user.getAnswerList();
			tempAns.put(qBankId, strAns);
			user.setAnswerList(tempAns);
		}
		if (user.getScoreList().get(qBankId) == null) {
			temp = user.getScoreList();
			temp.put(qBankId, Integer.toString(score));
			user.setScoreList(temp);

		} else {
			user.setUserAns1(user.getUserAns1());
			user.setUserAns2(user.getUserAns2());
			user.setUserAns3(user.getUserAns3());
			user.setUserAns4(user.getUserAns4());
			user.setUserAns5(user.getUserAns5());
			user.setScoreList(user.getScoreList());
			user.setAnswerList(user.getAnswerList());
			userService.addUser(user);
			throw new QBankAlreadyAttemptedException("You have already attempted this Question Set");
		}

		user.setUserAns1(user.getUserAns1());
		user.setUserAns2(user.getUserAns2());
		user.setUserAns3(user.getUserAns3());
		user.setUserAns4(user.getUserAns4());
		user.setUserAns5(user.getUserAns5());
		user.setScoreList(user.getScoreList());
		user.setAnswerList(user.getAnswerList());
		userService.addUser(user);
		return user;
	}

	@GetMapping("user-service/{userId}/questionbank/{qBankId}")
	public String getUserQuestions(@PathVariable int qBankId, @PathVariable int userId) {
		Exam bean = proxy.getExam(qBankId);
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new QBankAlreadyAttemptedException("No such UserId Exists");
		}
		if (bean == null) {
			throw new QBankAlreadyAttemptedException("No such Question Bank Exists");
		}
		return (bean.getTitle()+"\n"
		+ bean.getQ1() +"\n"+ bean.getOp11() +"\n"+ bean.getOp12() +"\n"+ bean.getOp13() +"\n"+ bean.getOp14() + "\n" 
		+ bean.getQ2()+"\n"+ bean.getOp21() +"\n"+ bean.getOp22() +"\n"+ bean.getOp23() +"\n"+ bean.getOp24()  + "\n"
		+ bean.getQ3()+"\n"+ bean.getOp31() +"\n"+ bean.getOp32() +"\n"+ bean.getOp33() +"\n"+ bean.getOp34() + "\n" 
		+ bean.getQ4()+"\n"+ bean.getOp41() +"\n"+ bean.getOp42() +"\n"+ bean.getOp43() +"\n"+ bean.getOp44() + "\n" 
		+ bean.getQ5()+"\n"+ bean.getOp51() +"\n"+ bean.getOp52() +"\n"+ bean.getOp53() +"\n"+ bean.getOp54());
	}
	
	@DeleteMapping("user-service/deleteuser/{userId}")
	public String deleteUser(@PathVariable int userId) {
		User user = userService.getUserById(userId);
		if (user != null) {
			userService.deleteUser(userId);
			return "Hi Admin, User Deleted successfully";
		} else {
			return ("Hi Admin, No such User Exists");
		}
	}
	
	@DeleteMapping("user-service/deleteans/{qBankId}")
	public void deleteAnsAndScore(@PathVariable int qBankId) {
		List<User> userList = userService.showUser();
		for (User user : userList) {
			if (user.getAnswerList().containsKey(qBankId)) {
				user.getAnswerList().remove(qBankId);
				user.getScoreList().remove(qBankId);
				user.setAnswerList(user.getAnswerList());
				user.setScoreList(user.getScoreList());
				userService.addUser(user);
			}
		}
	}
	
	//USER LOGIN NEW
		@GetMapping("user-service/user/check/{userId}/{password}")
		public boolean checkAdmin(@PathVariable int userId,@PathVariable String password) {
			User dummy = userService.getUserById(userId);
			if (dummy == null) {
				return false;
			}
			if (dummy.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		}

}
