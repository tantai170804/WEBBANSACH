package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.User;

public class UserDAO {

	// Login: tìm user theo username + password
	public User findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, username);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("id"));
					user.setUserName(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setPhone(rs.getString("phone"));
					user.setAddress(rs.getString("address"));
					user.setRole(rs.getString("role"));
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Tìm user theo id
	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("id"));
					user.setUserName(rs.getString("username")); // ✅ sửa lỗi ở đây
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setAddress(rs.getString("address"));
					user.setRole(rs.getString("role"));
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean existsByUsername(String username) {
		String sql = "SELECT 1 FROM users WHERE username = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false; // hoặc throw runtime tùy bạn
		}
	}

	public boolean createUser(String username, String password, String fullName) {
		String sql = "INSERT INTO users (username, password, full_name) VALUES (?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, fullName);

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
