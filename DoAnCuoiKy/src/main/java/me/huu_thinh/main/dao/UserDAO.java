package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.Date;
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
