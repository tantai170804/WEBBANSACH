package me.huu_thinh.main.model;

public class BookView {

	private Book book;
	private boolean inCart;
	public BookView(Book book) {
		this.book = book;
		this.inCart = false;
	}
	public BookView(Book book, boolean inCart) {
		super();
		this.book = book;
		this.inCart = inCart;
	}
	public Book getBook() {
		return book;
	}
	public boolean isInCart() {
		return inCart;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}
	
	
}
