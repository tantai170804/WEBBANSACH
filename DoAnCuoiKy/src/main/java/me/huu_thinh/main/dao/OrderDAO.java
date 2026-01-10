package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.Order;

public class OrderDAO {

	
	public int insert(Order o) {
		String sql = "INSERT INTO orders" + "(user_id, fullname,address,phone,total_price, payment_method,status) VALUES (?, ?, ?, ?, ?, ?,?)";
		Connection conn = null;
		try  {
			conn = DatabaseConnection.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, o.getUserId());
			ps.setString(2, o.getFullName());
			ps.setString(3, o.getAddress());
			ps.setString(4, o.getPhone());
			ps.setDouble(5, o.getTotalPrice());
			ps.setString(6, o.getPaymentMethod());
			ps.setString(7, "PENDING");
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
	
}
