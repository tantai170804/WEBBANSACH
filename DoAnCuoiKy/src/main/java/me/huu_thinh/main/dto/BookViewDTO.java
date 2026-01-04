package me.huu_thinh.main.dto;

public class BookViewDTO {

    private int bookId;
    private String bookCode;
    private String title;
    private double price;
    private int quantityInStock;
    private String categoryName; // từ bảng book_category
    private boolean canShow;

    public BookViewDTO(int bookId, String bookCode, String title,
                       double price, int quantityInStock,
                       String categoryName, boolean canShow) {
        this.bookId = bookId;
        this.bookCode = bookCode;
        this.title = title;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.categoryName = categoryName;
        this.canShow = canShow;
    }

    // Getter
    public int getBookId() { return bookId; }
    public String getBookCode() { return bookCode; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getQuantityInStock() { return quantityInStock; }
    public String getCategoryName() { return categoryName; }
    public boolean isCanShow() { return canShow; }
}
