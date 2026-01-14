package me.huu_thinh.main.dto;

import me.huu_thinh.main.model.Book;

public class BookViewInCartDTO {
	private Book book;
	private boolean inCart;
	public BookViewInCartDTO(Book book) {
		this.book = book;
		this.inCart = false;
	}
	public BookViewInCartDTO(Book book, boolean inCart) {
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
