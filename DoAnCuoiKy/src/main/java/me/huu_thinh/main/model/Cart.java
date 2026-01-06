package me.huu_thinh.main.model;

import java.sql.Date;

public class Cart {
	
	private int cartId;
	private int userId;
	private int bookId;
	private int quantity;
	private Date createAt;
	private Date updateAt;
	
	
	
	public Cart(int cartId, int userId, int bookId, int quantity, Date createAt, Date updateAt) {
		this.cartId = cartId;
		this.userId = userId;
		this.bookId = bookId;
		this.quantity =quantity;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCartId() {
		return cartId;
	}
	public int getUserId() {
		return userId;
	}
	public int getBookId() {
		return bookId;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	
	
	
}
