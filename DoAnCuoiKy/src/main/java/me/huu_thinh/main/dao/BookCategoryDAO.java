package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.BookCategory;

public class BookCategoryDAO {
	public static List<BookCategory> getAll() {
	    List<BookCategory> list = new ArrayList<>();

	    String sql = "SELECT category_id, name, description FROM book_category";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            BookCategory category = new BookCategory(
	                rs.getInt("category_id"),
	                rs.getString("name"),
	                rs.getString("description")
	            );
	            list.add(category);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	public static void insert(BookCategory category) {
		
	}
	public static void update(BookCategory category) {
		
	}
	public static void delete(String id) {
		
	}
	public static BookCategory findById(String id) {
		return null;
	}
}
