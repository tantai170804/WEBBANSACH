package me.huu_thinh.main.dto;

public class CartItemDTO {

	 private int bookId;
	 private String bookTitle;
	 private String bookImageUrl;
	
	 private int quantity;
	 private double price;
	 private double totalPrice;
	public CartItemDTO(int bookId, String bookTitle, String bookImage, int quantity, double price) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookImageUrl = bookImage;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = quantity * price;
	}
	public int getBookId() {
		return bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public String getBookImageUrl() {
		return bookImageUrl;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	
}
