package com.cts.outreach.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User {

	//@org.springframework.data.annotation.Id
	@Id
	@Column(length=25)
	private String userId;
	@Column(length=25)
	private String userPassword;
	@Column(length=25)
	private String userName;
	@Column(length=25)
	private String role;
	@Column(name = "time_stamp")
	private Date time_stamp;

	public User() {
		this.time_stamp = new Date();
	}

	public User(String userId, String userPassword, String userName, String role, Date timestamp) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.role = role;
		this.time_stamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Date time_stamp) {
		this.time_stamp = time_stamp;
	}

	

}
