package me.huu_thinh.main.dao;

import java.sql.Connection;
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
			String bookname = result.getString("book_name");
			String author = result.getString("author");
			int price = result.getInt("price");
			Book student = new Book(bookname,author,price);
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
	public static  void update(Book student) {
		
	}
	public static void delete(String id) {
		
	}
	public static Book findById(String id) {
		return null;
	}
}
