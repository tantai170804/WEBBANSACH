package me.huu_thinh.main.model;

import java.sql.Timestamp;

public class Purchase {

	private int purchaseId;
	private int userId;
	private double totalAmount;
	private OrderStatus status;
	private Timestamp createdAt;

	public Purchase() {
	}

	public Purchase(int purchaseId, int userId, double totalAmount, OrderStatus status, Timestamp createdAt) {
		this.purchaseId = purchaseId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.createdAt = createdAt;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
