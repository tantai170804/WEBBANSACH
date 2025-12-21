package me.huu_thinh.main.model;

import java.sql.Date;

public class Book {

	private int book_id;
	private String book_code;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private int quantity_in_stock;
	private String image_url;
	private String description;
	private int category_id;
	private boolean can_show;
	private Date create_at;
	private Date update_at;
	public Book(int book_id, String book_code, String title, String author, String publisher, int price,
			int quantity_in_stock, String image_url, String description, int category_id, boolean can_show,
			Date create_at, Date update_at) {
		this.book_id = book_id;
		this.book_code = book_code;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.quantity_in_stock = quantity_in_stock;
		this.image_url = image_url;
		this.description = description;
		this.category_id = category_id;
		this.can_show = can_show;
		this.create_at = create_at;
		this.update_at = update_at;
	}
	public int getBookId() {
		return book_id;
	}
	public String getBookCode() {
		return book_code;
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
	public String getImageUrl() {
		return image_url;
	}
	public String getDescription() {
		return description;
	}
	public int getCategoryId() {
		return category_id;
	}
	public boolean isCanShow() {
		return can_show;
	}
	public Date getCreateAt() {
		return create_at;
	}
	public Date getUpdateAt() {
		return update_at;
	}
	
	
	
}
