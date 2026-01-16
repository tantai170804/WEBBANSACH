package me.huu_thinh.main.model;

public class OrderItem {

	private int id; 
	private int orderId; 
	private int bookId; 
	private int quantity; 
	private double price; 

	private String bookName;
	private String bookImage;

	public OrderItem() {
	}

	public OrderItem(int orderId, int bookId, int quantity, double price) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.price = price;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
}