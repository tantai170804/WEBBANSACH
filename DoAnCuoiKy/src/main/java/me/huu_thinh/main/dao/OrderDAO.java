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



	public Order getOrderById(int orderId) {
		Order order = null;
		String sql = "SELECT * FROM orders WHERE order_id = ?";

		String sqlItems = "SELECT oi.*, b.title, b.image_url FROM order_items oi "
				+ "JOIN books b ON oi.book_id = b.book_id " + "WHERE oi.order_id = ?";

		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();

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
				order.setStatus(rs.getString("status"));
				
				PreparedStatement psItem = conn.prepareStatement(sqlItems);
				psItem.setInt(1, orderId);
				psItem.executeQuery();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, null, null);
		}
		return order;
	}

	public List<OrderItem> getOrderItems(int orderId) {
		List<OrderItem> list = new ArrayList<>();

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

				item.setBookName(rs.getString("title"));
				item.setBookImage(rs.getString("image_url")); 

				list.add(item);
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
				e.printStackTrace();
			}
		}
		return list;
	}

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

	public List<Order> findWithPagination(int limit, int offset) {
		List<Order> list = new ArrayList<>();
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