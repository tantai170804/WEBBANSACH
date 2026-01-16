package me.huu_thinh.main.model;

import java.sql.Timestamp; 
public class Order {
	private int id;
	private int userId;
	private String fullName;
	private String address;
	private String phone;
	private String paymentMethod;
	private String status;
	private double totalPrice;
	private Timestamp createdAt; 
	private Timestamp updateAt; 

	public Order() {
	}

	public Order(int userId, String fullName, String address, String phone, String paymentMethod, String status,
			double totalPrice) {
		this.userId = userId;
		this.fullName = fullName;
		this.address = address;
		this.phone = phone;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public Order(int id, int userId, String fullName, String address, String phone, String paymentMethod, String status,
			double totalPrice, Timestamp createdAt, Timestamp updateAt) {
		this.id = id;
		this.userId = userId;
		this.fullName = fullName;
		this.address = address;
		this.phone = phone;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}




	
}