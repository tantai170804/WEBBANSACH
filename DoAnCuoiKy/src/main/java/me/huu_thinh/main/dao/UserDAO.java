package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.User;

public class UserDAO {
	public static List<User> getAll(){
		Connection conn = null;
		List<User> resultList = new ArrayList<User>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM users;");
		  while (result.next()) {
			int userid = result.getInt("user_id");
			String username = result.getString("username");
			String password = result.getString("password_hash");
			String email = result.getString("email");
			String phone = result.getString("phone");
			String address = result.getString("address");
			String role = result.getString("role");
			Date create_at = result.getDate("create_at");
			User student = new User(userid, username, password, email, phone, address, role, create_at);
			resultList.add(student);
			}
			stmt.close();
			} catch (SQLException e) {
			e.printStackTrace();
			} finally {
			  try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			}
		return resultList;
	}
	// Kiểm tra username đã tồn tại chưa
	public boolean existsByUsername(String username) {
	    Connection conn = null;

	    try {
	        conn = DatabaseConnection.getConnection();
	        String sql = "SELECT 1 FROM users WHERE username = ? LIMIT 1";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, username);

	        ResultSet rs = ps.executeQuery();
	        boolean exists = rs.next();

	        rs.close();
	        ps.close();
	        return exists;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (conn != null) {
	            try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
	}

	// Tạo user mới (đơn giản): chỉ tạo username + password_hash + role + create_at
	// fullName hiện DB của bạn KHÔNG có cột full_name, nên tạm thời bỏ qua fullName
	public boolean createUser(String username, String password, String fullName) {
	    Connection conn = null;

	    try {
	        conn = DatabaseConnection.getConnection();

	        String sql = "INSERT INTO users (username, password_hash, role, create_at) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(sql);

	        ps.setString(1, username);
	        ps.setString(2, password);          // đang để plaintext theo yêu cầu "đơn giản"
	        ps.setString(3, "USER");            // role mặc định
	        ps.setDate(4, new Date(System.currentTimeMillis())); // ngày tạo

	        int rows = ps.executeUpdate();
	        ps.close();

	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (conn != null) {
	            try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
	}
	// Dùng cho login
	public User findByUsernameAndPassword(String username, String password) {
	    Connection conn = null;

	    try {
	        conn = DatabaseConnection.getConnection();

	        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, password); // hiện tại đang so plaintext

	        ResultSet result = ps.executeQuery();

	        if (result.next()) {
	            int userid = result.getInt("user_id");
	            String uname = result.getString("username");
	            String pass = result.getString("password_hash");
	            String email = result.getString("email");
	            String phone = result.getString("phone");
	            String address = result.getString("address");
	            String role = result.getString("role");
	            Date create_at = result.getDate("create_at");

	            return new User(userid, uname, pass, email, phone, address, role, create_at);
	        }

	        result.close();
	        ps.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	    }

	    return null; // login thất bại
	}

	

	public static void insert(User user) {
		
	}
	public static void update(User user) {
		
	}
	public static void delete(String id) {
		
	}
	public static User findById(String id) {
		return null;
	}
}
