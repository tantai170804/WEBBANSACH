package me.huu_thinh.main.dto;

import java.util.Date;

public class UserViewDTO {
	private int userId;
	private String userName;
	private String email;
	private String phone;
	private String role;
	private Date createAt;

	public UserViewDTO(int userId, String userName, String email, String phone, String role, Date createAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.createAt = createAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
