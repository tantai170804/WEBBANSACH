package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.dto.CartItemDTO;
import me.huu_thinh.main.model.OrderItem;

public class OrderItemDAO {

	
	public void insert(int orderId, CartItemDTO cart) {
		OrderItem orderitem = new OrderItem();
		orderitem.setOrderId(orderId);
		orderitem.setBookId(cart.getBookId());
		orderitem.setPrice(cart.getPrice());
		orderitem.setQuantity(cart.getQuantity());
		insert(orderitem);
	}
	public boolean insert(OrderItem item) {
		String sql = "INSERT INTO order_items" + "(order_id, book_id,quantity,price) VALUES (?, ?, ?, ?)";
		Connection conn = null;
		try  {
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
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
