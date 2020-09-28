package com.manipal.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@Column(name ="admin_id")
	private int adminId;
	@Column(name ="admin_Name")
	private String adminName;
	@Column(name ="admin_Pin")
	private int adminPin;
	@Column(name ="admin_Phone")
	private String adminPhone;
	/*@Column(name ="user_id",insertable = false, updatable = false)
	private int userId;*/
	
	/*
	 * @Embedded
	 *  private Exam exam;
	 */
	
	/* public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/

	 
	 

	

	
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public int getAdminPin() {
		return adminPin;
	}

	public void setAdminPin(int adminPin) {
		this.adminPin = adminPin;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPin=" + adminPin + ", adminPhone="
				+ adminPhone + "]";
	}
	
	

	/*
	 * public Exam getExam() { return exam; }
	 * 
	 * public void setExam(Exam exam) { this.exam = exam; }
	 * 
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 */
	
	

}
