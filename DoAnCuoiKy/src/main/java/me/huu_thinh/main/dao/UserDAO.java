package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.User;

public class UserDAO {
	
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM users";
		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			return rs.next() ? rs.getInt(1) : 0;
		}
	}

	public static List<User> getAll() {
		Connection conn = null;
		List<User> resultList = new ArrayList<User>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM users;");
			while (result.next()) {
				int userid = result.getInt("userid");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String role = result.getString("role");
				Date create_at = result.getDate("created_at");
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
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Tạo user mới (đơn giản): chỉ tạo username + password_hash + role + create_at
	// fullName hiện DB của bạn KHÔNG có cột full_name, nên tạm thời bỏ qua fullName
	public boolean createUser(String username, String password) {
		Connection conn = null;

		try {
			conn = DatabaseConnection.getConnection();

			String sql = "INSERT INTO users (username, password_hash, role, created_at) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password); // đang để plaintext theo yêu cầu "đơn giản"
			ps.setString(3, "customer"); // role mặc định
			ps.setDate(4, new Date(System.currentTimeMillis())); // ngày tạo

			int rows = ps.executeUpdate();
			ps.close();

			return rows > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public User findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password); // hiện tại so plaintext trong password_hash

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int userid = rs.getInt("user_id"); // ✅ đúng cột DB
					String uname = rs.getString("username"); // ✅ đúng cột DB
					String pass = rs.getString("password_hash"); // ✅ đúng cột DB
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String address = rs.getString("address");
					String role = rs.getString("role");

					Timestamp ts = rs.getTimestamp("created_at"); // DATETIME
					Date createAt = (ts != null) ? new Date(ts.getTime()) : null; // ✅ về java.util.Date

					return new User(userid, uname, pass, email, phone, address, role, createAt);

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
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