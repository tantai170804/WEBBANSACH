package me.huu_thinh.main.model;

public class Order {

	private int userId;
	private String fullName;
	private String address;
	private String phone;
	private String paymentMethod;
	private String status;
	private double totalPrice;
	
	public Order() {
			
	}
	
	public Order(int userId,String fullName, String address,String phone, String paymentMethod,String status, double totalPrice) {
		this.userId = userId;
		this.fullName = fullName;
		this.address = address;
		this.paymentMethod = paymentMethod;
		this.totalPrice = totalPrice;
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	
	
}
