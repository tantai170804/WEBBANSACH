package me.huu_thinh.main.model;

public class CartItem {
    private int bookId;
    private String title;
    private double unitPrice;
    private int quantity;

    public CartItem() {}

    public CartItem(int bookId, String title, double unitPrice, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getLineTotal() { return unitPrice * quantity; }
}
