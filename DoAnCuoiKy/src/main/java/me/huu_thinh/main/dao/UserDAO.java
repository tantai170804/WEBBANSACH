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
import me.huu_thinh.main.dto.UserViewDTO;
import me.huu_thinh.main.model.User;

public class UserDAO {

	public List<User> findAll(int limit, int offset) {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM users ORDER BY user_id DESC LIMIT ? OFFSET ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, limit);
			ps.setInt(2, offset);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					User u = new User();
					u.setUserId(rs.getInt("user_id"));
					u.setUserName(rs.getString("username"));
					u.setEmail(rs.getString("email"));
					u.setPhone(rs.getString("phone"));
					u.setRole(rs.getString("role"));
					u.setPassword(rs.getString("password_hash"));
					u.setAddress(rs.getString("address"));
					u.setCreateAt(rs.getDate("created_at"));

					list.add(u);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean delete(int id) {
		String sql = "DELETE FROM users WHERE user_id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean update(User u) {
		String sql = """
				    UPDATE users
				    SET email=?, phone=?, address=?, role=?
				    WHERE user_id=?
				""";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPhone());
			ps.setString(3, u.getAddress());
			ps.setString(4, u.getRole());
			ps.setInt(5, u.getUserId());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean updatePass(User u, String newpass_hash) {
		String sql = " UPDATE users SET password_hash = ? WHERE user_id = ?";
	try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

		ps.setString(1, newpass_hash);
		ps.setInt(2, u.getUserId());

		return ps.executeUpdate() > 0;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	}
	public static User findById(int id) {
		String sql = """
				    SELECT user_id, username, email, password_hash, phone, address, role, created_at
				    FROM users
				    WHERE user_id = ?
				""";

		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),
							rs.getString("password_hash"),
							rs.getString("phone"), rs.getString("address"), rs.getString("role"),
							rs.getDate("created_at")
					);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<UserViewDTO> getAllForAdmin() {
		List<UserViewDTO> list = new ArrayList<>();
		String sql = "SELECT user_id, username, email, phone, role, created_at " + "FROM users "
				+ "ORDER BY user_id DESC";

		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(new UserViewDTO(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),
						rs.getString("phone"), rs.getString("role"), rs.getDate("created_at")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

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
				String password = result.getString("password_hash");
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

	public boolean createUser(String username, String password_hash, String gmail, String phone, String address) {
		Connection conn = null;

		try {
			conn = DatabaseConnection.getConnection();

			String sql = "INSERT INTO users (username, password_hash, email, phone, address, role, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password_hash);
			ps.setString(3, gmail);
			ps.setString(4, phone);
			ps.setString(5, address);
			ps.setString(6, "customer"); 
			ps.setDate(7, new Date(System.currentTimeMillis()));

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

	public User findByUsernameAndPassword(String username, String password_hash) {
		String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password_hash); 

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int userid = rs.getInt("user_id");
					String uname = rs.getString("username");
					String pass = rs.getString("password_hash");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String address = rs.getString("address");
					String role = rs.getString("role");

					Timestamp ts = rs.getTimestamp("created_at"); 
					Date createAt = (ts != null) ? new Date(ts.getTime()) : null;

					return new User(userid, uname, email,pass, phone, address, role, createAt);

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean insert(User user) {
		String sql = "INSERT INTO users (username, password_hash, email, phone, address, role, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getRole() != null ? user.getRole() : "customer");
			ps.setDate(7, new Date(System.currentTimeMillis()));

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	

	public static User findByUserName(String username) {
		String sql = """
			    SELECT user_id, username, email, password_hash, phone, address, role, created_at
			    FROM users
			    WHERE username = ?
			""";

	try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

		ps.setString(1, username);

		try (ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password_hash"),
						rs.getString("phone"), rs.getString("address"), rs.getString("role"),
						rs.getDate("created_at")
				);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	}
}