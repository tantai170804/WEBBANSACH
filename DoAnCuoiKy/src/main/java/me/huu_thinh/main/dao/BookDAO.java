package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.Book;

public class BookDAO {
	public static List<Book> getAll(){
		Connection conn = null;
		List<Book> resultList = new ArrayList<Book>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM book;");
		  while (result.next()) {
			String bookid = result.getString("book_id");
			String title = result.getString("title");
			String author = result.getString("author");
			String publisher = result.getString("title");
			int price = result.getInt("price");
			int quantity_in_stock = result.getInt("quantity_in_stock");
			int category_id = result.getInt("category_id");
			String description = result.getString("description");
			Date create_at = result.getDate("create_at");
			Date update_at = result.getDate("create_at");
			Book student = new Book(bookid, title, author, publisher,
					price, quantity_in_stock, category_id, description, create_at, update_at);
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
	public static void insert(Book student) {
		
	}
	public static void update(Book student) {
		
	}
	public static void delete(String id) {
		
	}
	public static Book findById(String id) {
		return null;
	}
}
