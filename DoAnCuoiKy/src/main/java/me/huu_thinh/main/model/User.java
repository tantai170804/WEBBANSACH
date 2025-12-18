package me.huu_thinh.main.model;

public class User {

	private int user_id;
	private String userName;
	private String password;
	private String phone;
	private String address;
	private String role;
	public User(int user_id, String userName, String password, String phone, String address, String role) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	
	
}
