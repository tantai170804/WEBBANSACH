package me.huu_thinh.main.model;

import java.sql.Date;

public class User {

	private int userid;
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
		this.userid = user_id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createAt = create_at;
	}

	public int getUserId() {
		return userid;
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
