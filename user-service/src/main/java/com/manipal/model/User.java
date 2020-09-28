package com.manipal.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_ans1")
	private String userAns1;
	
	@Column(name="user_ans2")
	private String userAns2;
	
	@Column(name="user_ans3")
	private String userAns3;
	
	@Column(name="user_ans4")
	private String userAns4;
	
	@Column(name="user_ans5")
	private String userAns5;
	
	@Column(name="score_list")
	private HashMap<Integer, String> scoreList;
	
	private HashMap<Integer, String> answerList;
	
	private String name;
	
	private String email;
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public HashMap<Integer, String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(HashMap<Integer, String> answerList) {
		this.answerList = answerList;
	}

	public HashMap<Integer, String> getScoreList() {
		return scoreList;
	}

	public void setScoreList(HashMap<Integer, String> scoreList) {
		this.scoreList = scoreList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAns1() {
		return userAns1;
	}

	public void setUserAns1(String userAns1) {
		this.userAns1 = userAns1;
	}

	public String getUserAns2() {
		return userAns2;
	}

	public void setUserAns2(String userAns2) {
		this.userAns2 = userAns2;
	}

	public String getUserAns3() {
		return userAns3;
	}

	public void setUserAns3(String userAns3) {
		this.userAns3 = userAns3;
	}

	public String getUserAns4() {
		return userAns4;
	}

	public void setUserAns4(String userAns4) {
		this.userAns4 = userAns4;
	}

	public String getUserAns5() {
		return userAns5;
	}

	public void setUserAns5(String userAns5) {
		this.userAns5 = userAns5;
	}	

}
