package me.huu_thinh.main.model;

import java.sql.Date;

public class User {

	private int userId;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String role;
	private Date createAt;
	
	public User() {	}
	
	
	public User(int user_id, String userName,String email, String password, String phone, String address, String role, Date create_at) {
		super();
		this.userId = user_id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createAt = create_at;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userid) {
		this.userId = userid;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getRole() {
		return role;
	}

	public Date getCreateAt() {
		return createAt;
	}
	
}
