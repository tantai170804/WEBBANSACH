package me.huu_thinh.main.model;

public class OrderItem {

	private int id; // Khóa chính
	private int orderId; // Mã đơn hàng
	private int bookId; // Mã sách
	private int quantity; // Số lượng
	private double price; // Giá bán tại thời điểm mua

	// Các thuộc tính phụ (Dùng để hiển thị ra giao diện, không lưu trực tiếp vào
	// bảng order_items)
	private String bookName;
	private String bookImage;

	// Constructor rỗng
	public OrderItem() {
	}

	// Constructor dùng khi insert (chưa có ID và tên sách)
	public OrderItem(int orderId, int bookId, int quantity, double price) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.price = price;
	}

	// --- GETTER & SETTER ---

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

	// Getter & Setter cho các thuộc tính hiển thị
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