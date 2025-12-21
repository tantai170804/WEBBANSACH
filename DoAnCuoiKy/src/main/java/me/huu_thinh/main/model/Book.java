package me.huu_thinh.main.model;

import java.sql.Date;

public class Book {

	private int bookId;
	private String bookCode;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private int quantityInStock;
	private String imageUrl;
	private String description;
	private int categoryId;
	private boolean canShow;
	private Date createdAt;
	private Date updatedAt;
	public Book(int book_id, String book_code, String title, String author, String publisher, int price,
			int quantity_in_stock, String image_url, String description, int category_id, boolean can_show,
			Date created_at, Date updated_at) {
		this.bookId = book_id;
		this.bookCode = book_code;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.quantityInStock = quantity_in_stock;
		this.imageUrl = image_url;
		this.description = description;
		this.categoryId = category_id;
		this.canShow = can_show;
		this.createdAt = created_at;
		this.updatedAt = updated_at;
	}
	public int getBookId() {
		return bookId;
	}
	public String getBookCode() {
		return bookCode;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublisher() {
		return publisher;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public boolean isCanShow() {
		return canShow;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}

	
	
}
