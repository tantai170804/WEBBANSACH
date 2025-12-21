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
import me.huu_thinh.main.model.Book;

public class BookDAO {
	public static List<Book> getAll(){
		Connection conn = null;
		List<Book> resultList = new ArrayList<Book>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM books;");
		  while (result.next()) {
			int bookid = result.getInt("book_id");
			String bookcode = result.getString("book_code");
			String title = result.getString("title");
			String author = result.getString("author");
			String publisher = result.getString("publisher");
			int price = result.getInt("price");
			int quantity_in_stock = result.getInt("quantity_in_stock");
			String image_url = result.getString("image_url");
			String description = result.getString("description");
			int category_id = result.getInt("category_id");
			boolean canshow = result.getBoolean("can_show");
			Date create_at = result.getDate("created_at");
			Date update_at = result.getDate("updated_at");
			Book student = new Book(bookid, bookcode, title, author, publisher,
					price, quantity_in_stock, image_url, description, category_id, canshow, create_at, update_at);
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
	public static Book findById(int id) {
		Connection conn = null;
		List<Book> resultList = new ArrayList<Book>();
		try {
			conn = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM books WHERE book_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet result = ps.executeQuery();
		  while (result.next()) {
			  int bookid = result.getInt("book_id");
				String bookcode = result.getString("book_code");
				String title = result.getString("title");
				String author = result.getString("author");
				String publisher = result.getString("publisher");
				int price = result.getInt("price");
				int quantity_in_stock = result.getInt("quantity_in_stock");
				String image_url = result.getString("image_url");
				String description = result.getString("description");
				int category_id = result.getInt("category_id");
				boolean canshow = result.getBoolean("can_show");
				Date create_at = result.getDate("created_at");
				Date update_at = result.getDate("updated_at");
				Book student = new Book(bookid, bookcode, title, author, publisher,
						price, quantity_in_stock, image_url, description, category_id, canshow, create_at, update_at);
				resultList.add(student);
		  }
		  ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			} finally {
			  try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			}
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}
}
