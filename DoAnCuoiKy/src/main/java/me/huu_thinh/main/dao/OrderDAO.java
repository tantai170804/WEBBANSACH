package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.Order;
import me.huu_thinh.main.model.OrderItem;

public class OrderDAO {

	// --- 1. TẠO ĐƠN HÀNG (TRANSACTION) ---
	public boolean createOrder(Order order, List<OrderItem> items) {
		// SQL Insert Order (Không có email, note theo Model mới)
		String sqlOrder = "INSERT INTO orders (user_id, fullname, phone, address, payment_method, status, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";

		// SQL Insert Item (Chỉ lưu ID, giá, số lượng)
		String sqlItem = "INSERT INTO order_items (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement psOrder = null;
		PreparedStatement psItem = null;

		try {
			conn = DatabaseConnection.getConnection();
			conn.setAutoCommit(false); // Bắt đầu giao dịch

			// A. Lưu bảng ORDERS
			psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
			psOrder.setInt(1, order.getUserId());
			psOrder.setString(2, order.getFullName());
			psOrder.setString(3, order.getPhone());
			psOrder.setString(4, order.getAddress());
			psOrder.setString(5, order.getPaymentMethod());
			psOrder.setString(6, getStatusString(0)); // Mặc định 0: Chờ duyệt
			psOrder.setDouble(7, order.getTotalPrice());

			int affectedRows = psOrder.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("Tạo đơn hàng thất bại.");

			// B. Lấy ID đơn hàng vừa tạo
			int orderId = 0;
			try (ResultSet generatedKeys = psOrder.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					orderId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Tạo đơn hàng thất bại, không lấy được ID.");
				}
			}

			// C. Lưu bảng ORDER_ITEMS
			psItem = conn.prepareStatement(sqlItem);
			for (OrderItem item : items) {
				psItem.setInt(1, orderId); // ID đơn hàng
				psItem.setInt(2, item.getBookId());// ID sách
				psItem.setInt(3, item.getQuantity());
				psItem.setDouble(4, item.getPrice());

				psItem.addBatch(); // Gom lệnh
			}

			psItem.executeBatch();
			conn.commit(); // Xác nhận thành công
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
			}
			return false;
		} finally {
			closeResources(conn, psOrder, psItem);
		}
	}

	// --- 2. LẤY CHI TIẾT ĐƠN HÀNG KÈM SẢN PHẨM ---
	public Order getOrderById(int orderId) {
		Order order = null;
		String sql = "SELECT * FROM orders WHERE order_id = ?";

		// Query này JOIN với bảng BOOKS để lấy tên sách và ảnh hiển thị
		String sqlItems = "SELECT oi.*, b.title, b.image_url FROM order_items oi "
				+ "JOIN books b ON oi.book_id = b.book_id " + "WHERE oi.order_id = ?";

		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();

			// A. Lấy thông tin Order
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setFullName(rs.getString("fullname"));
				order.setPhone(rs.getString("phone"));
				order.setAddress(rs.getString("address"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setTotalPrice(rs.getDouble("total_price"));
				order.setCreatedAt(rs.getTimestamp("created_at"));
				order.setUpdateAt(rs.getTimestamp("updated_at"));

				// Convert status int -> String
				order.setStatus(rs.getString("status"));

				// B. Lấy danh sách Items (đã kèm tên sách từ bảng books)
				PreparedStatement psItem = conn.prepareStatement(sqlItems);
				psItem.setInt(1, orderId);
				ResultSet rsItem = psItem.executeQuery();

				// Lưu ý: Class Order của bạn hiện tại KHÔNG có List<OrderItem>
				// Nên ta chỉ return Order, việc lấy Items sẽ xử lý ở Service hoặc 1 hàm riêng
				// nếu cần
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, null, null);
		}
		return order;
	}

	// --- 3. LẤY DANH SÁCH SẢN PHẨM CỦA 1 ĐƠN HÀNG ---
	public List<OrderItem> getOrderItems(int orderId) {
		List<OrderItem> list = new ArrayList<>();

		// SQL CHUẨN: Dùng book_id và image_url
		String sql = "SELECT oi.*, b.title, b.image_url FROM order_items oi "
				+ "JOIN books b ON oi.book_id = b.book_id " + "WHERE oi.order_id = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderItem item = new OrderItem();
				item.setId(rs.getInt("order_item_id"));
				item.setOrderId(rs.getInt("order_id"));
				item.setBookId(rs.getInt("book_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPrice(rs.getDouble("price"));

				// Set dữ liệu hiển thị lấy từ bảng books
				item.setBookName(rs.getString("title"));
				item.setBookImage(rs.getString("image_url")); // Quan trọng: Phải khớp tên cột DB

				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// --- 4. LẤY TẤT CẢ ĐƠN HÀNG (ADMIN) ---
	public List<Order> getAllOrders() {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM orders ORDER BY order_id DESC";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setFullName(rs.getString("fullname"));
				order.setPhone(rs.getString("phone"));
				order.setAddress(rs.getString("address"));
				order.setTotalPrice(rs.getDouble("total_price"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setCreatedAt(rs.getTimestamp("created_at"));
				order.setUpdateAt(rs.getTimestamp("updated_at"));
				order.setStatus(rs.getString("status"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Order> getAllOrdersFromUser(int userId) {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_id DESC";
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setFullName(rs.getString("fullname"));
				order.setPhone(rs.getString("phone"));
				order.setAddress(rs.getString("address"));
				order.setTotalPrice(rs.getDouble("total_price"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setCreatedAt(rs.getTimestamp("created_at"));
				order.setUpdateAt(rs.getTimestamp("updated_at"));
				order.setStatus(rs.getString("status"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// --- 5. CẬP NHẬT TRẠNG THÁI ---
	public void updateStatus(int orderId, int status) {
		String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, getStatusString(status));
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// --- UTILS ---
	

	private void closeResources(Connection conn, PreparedStatement ps1, PreparedStatement ps2) {
		try {
			if (ps1 != null)
				ps1.close();
			if (ps2 != null)
				ps2.close();
			if (conn != null) {
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException e) {
		}
	}

	// 1. Hàm đếm tổng số đơn hàng (Dùng để tính số trang)
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM orders";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 2. Hàm lấy danh sách phân trang (Thay thế cho getAllOrders cũ)
	public List<Order> findWithPagination(int limit, int offset) {
		List<Order> list = new ArrayList<>();
		// Sắp xếp ID giảm dần để đơn mới nhất lên đầu
		String sql = "SELECT * FROM orders ORDER BY order_id DESC LIMIT ? OFFSET ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, limit);
			ps.setInt(2, offset);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setFullName(rs.getString("fullname"));
				order.setPhone(rs.getString("phone"));
				order.setAddress(rs.getString("address"));
				order.setTotalPrice(rs.getDouble("total_price"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setCreatedAt(rs.getTimestamp("created_at"));
				order.setUpdateAt(rs.getTimestamp("updated_at"));
				// Chuyển status từ số sang chữ
				order.setStatus(rs.getString("status"));

				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int insert(Order o) {
		String sql = "INSERT INTO orders"
				+ "(user_id, fullname,address,phone,total_price, payment_method,status) VALUES (?, ?, ?, ?, ?, ?,?)";
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, o.getUserId());
			ps.setString(2, o.getFullName());
			ps.setString(3, o.getAddress());
			ps.setString(4, o.getPhone());
			ps.setDouble(5, o.getTotalPrice());
			ps.setString(6, o.getPaymentMethod());
			ps.setString(7, getStatusString(0));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}
	private String getStatusString(int status) {
		switch (status) {
		case 0:
			return "PENDING";
		case 1:
			return "SHIPPING";
		case 2:
			return "SUCCESS";
		case 3:
			return "CANCEL";
		default:
			return "UNKNOWN";
		}
	}
}