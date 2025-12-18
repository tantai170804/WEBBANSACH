package me.huu_thinh.main.model;

import java.sql.Date;

public class Book {

	private String book_id;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private int quantity_in_stock;
	private int category_id;
	private String description;
	private Date create_at;
	private Date update_at;
	public Book(String book_id, String title, String author, String publisher, int price, int quantity_in_stock, int category_id, String description, Date create_at, Date update_at) {
		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.quantity_in_stock = quantity_in_stock;
		this.category_id = category_id;
		this.description = description;
		this.create_at = create_at;
		this.update_at = update_at;
	}
	public String getBookId() {
		return book_id;
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
		return quantity_in_stock;
	}

	public int getCategoryId() {
		return category_id;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreateAt() {
		return create_at;
	}
	public Date getUpdateAt() {
		return update_at;
	}
	
}
