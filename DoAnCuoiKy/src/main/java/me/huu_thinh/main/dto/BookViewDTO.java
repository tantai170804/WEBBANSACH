package me.huu_thinh.main.dto;

public class BookViewDTO {

	private int bookId;
	private String bookCode;
	private String title;
	private double price;
	private int quantityInStock;
	private String categoryName; // từ bảng book_category
	private boolean canShow;

	public BookViewDTO() {

	}

	public BookViewDTO(int bookId, String bookCode, String title, double price, int quantityInStock,
			String categoryName, boolean canShow) {
		this.bookId = bookId;
		this.bookCode = bookCode;
		this.title = title;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.categoryName = categoryName;
		this.canShow = canShow;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCanShow(boolean canShow) {
		this.canShow = canShow;
	}

	// Getter
	public int getBookId() {
		return bookId;
	}

	public String getBookCode() {
		return bookCode;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public boolean isCanShow() {
		return canShow;
	}
}
