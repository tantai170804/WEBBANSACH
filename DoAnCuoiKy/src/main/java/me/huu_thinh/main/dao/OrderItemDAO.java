package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.dto.CartItemDTO;
import me.huu_thinh.main.model.OrderItem;

public class OrderItemDAO {

	// Helper method để convert từ CartItemDTO sang OrderItem và insert
	public void insert(int orderId, CartItemDTO cart) {
		OrderItem orderitem = new OrderItem();
		orderitem.setOrderId(orderId);
		orderitem.setBookId(cart.getBookId());
		orderitem.setPrice(cart.getPrice());
		orderitem.setQuantity(cart.getQuantity());
		insert(orderitem);
	}

	// Hàm insert chính xuống DB
	public boolean insert(OrderItem item) {
		String sql = "INSERT INTO order_items (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, item.getOrderId());
			ps.setInt(2, item.getBookId());
			ps.setInt(3, item.getQuantity());
			ps.setDouble(4, item.getPrice());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Hàm này lấy danh sách sản phẩm của 1 đơn hàng cụ thể (Dùng cho Admin xem chi
	// tiết)
	public List<OrderItem> getByOrderId(int orderId) {
		List<OrderItem> list = new ArrayList<>();

		// SQL JOIN với bảng books để lấy thêm tên sách và hình ảnh
		String sql = "SELECT oi.*, b.name, b.image " + "FROM order_items oi " + "JOIN books b ON oi.book_id = b.id "
				+ "WHERE oi.order_id = ?";

		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderItem item = new OrderItem();

				// Map dữ liệu từ bảng order_items
				// (Nếu bảng order_items có cột id khóa chính thì thêm
				// item.setId(rs.getInt("id")) )
				item.setOrderId(rs.getInt("order_id"));
				item.setBookId(rs.getInt("book_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPrice(rs.getDouble("price"));

				// Map thêm thông tin sách (Lấy từ bảng books nhờ lệnh JOIN)
				// Hai dòng này bây giờ sẽ chạy tốt vì bạn đã update Model OrderItem
				item.setBookName(rs.getString("name"));
				item.setBookImage(rs.getString("image"));

				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}