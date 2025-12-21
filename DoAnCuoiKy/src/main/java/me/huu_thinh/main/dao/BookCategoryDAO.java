package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.BookCategory;

public class BookCategoryDAO {
	public static List<BookCategory> getAll(){
		Connection conn = null;
		List<BookCategory> resultList = new ArrayList<BookCategory>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM book_category;");
		  while (result.next()) {
			int category_id = result.getInt("category_id");
			String name = result.getString("name");
			String description = result.getString("description");
			BookCategory student = new BookCategory(category_id, name, description);
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
