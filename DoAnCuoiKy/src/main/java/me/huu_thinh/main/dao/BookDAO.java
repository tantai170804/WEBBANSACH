package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.dto.BookViewDTO;
import me.huu_thinh.main.model.Book;

public class BookDAO {
	
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM books";
		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			return rs.next() ? rs.getInt(1) : 0;
		}
	}

	public static int countAllForAdmin() {
		String sql = "SELECT COUNT(*) FROM books";
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static List<BookViewDTO> getPageForAdmin(int offset, int limit) {
		List<BookViewDTO> list = new ArrayList<>();

		String sql = "SELECT b.book_id, b.book_code, b.title, b.price, b.quantity_in_stock, b.can_show, "
				+ "       c.name AS category_name " + "FROM books b "
				+ "LEFT JOIN book_category c ON b.category_id = c.category_id " + "ORDER BY b.book_id DESC "
				+ "LIMIT ? OFFSET ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, limit);
			ps.setInt(2, offset);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					BookViewDTO dto = new BookViewDTO();
					dto.setBookId(rs.getInt("book_id"));
					dto.setBookCode(rs.getString("book_code"));
					dto.setTitle(rs.getString("title"));
					dto.setPrice(rs.getDouble("price"));
					dto.setQuantityInStock(rs.getInt("quantity_in_stock"));
					dto.setCanShow(rs.getBoolean("can_show"));
					dto.setCategoryName(rs.getString("category_name"));
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<BookViewDTO> getAllForAdmin() {
		List<BookViewDTO> list = new ArrayList<>();

		String sql = "SELECT b.book_id, " + "       b.book_code, " + "       b.title, " + "       b.price, "
				+ "       b.quantity_in_stock, " + "       b.can_show, " + "       c.name AS category_name "
				+ "FROM books b " + "LEFT JOIN book_category c " + "       ON b.category_id = c.category_id "
				+ "ORDER BY b.book_id DESC";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				BookViewDTO dto = new BookViewDTO(rs.getInt("book_id"), rs.getString("book_code"),
						rs.getString("title"), rs.getDouble("price"), rs.getInt("quantity_in_stock"),
						rs.getString("category_name"), rs.getBoolean("can_show"));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<Book> getAll() {
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
				Book student = new Book(bookid, bookcode, title, author, publisher, price, quantity_in_stock, image_url,
						description, category_id, canshow, create_at, update_at);
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

	public static Book findById(int id) {
		Connection conn = null;
		List<Book> resultList = new ArrayList<Book>();
		try {
			conn = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM books WHERE book_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
				Book student = new Book(bookid, bookcode, title, author, publisher, price, quantity_in_stock, image_url,
						description, category_id, canshow, create_at, update_at);
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
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	public static boolean delete(int id) {
		String sql = "DELETE FROM books WHERE book_id = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0; // true nếu xóa được 1 dòng

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean update(Book b) {
		String sql = "UPDATE books SET " + "book_code = ?, " + "title = ?, " + "author = ?, " + "publisher = ?, "
				+ "price = ?, " + "quantity_in_stock = ?, " + "image_url = ?, " + "description = ?, "
				+ "category_id = ?, " + "can_show = ? " + "WHERE book_id = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, b.getBookCode());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getPublisher());
			ps.setDouble(5, b.getPrice());
			ps.setInt(6, b.getQuantityInStock());
			ps.setString(7, b.getImageUrl());
			ps.setString(8, b.getDescription());

			// category_id có thể NULL
			if (b.getCategoryId() != null) {
				ps.setInt(9, b.getCategoryId());
			} else {
				ps.setNull(9, java.sql.Types.INTEGER);
			}

			ps.setBoolean(10, b.isCanShow());

			ps.setInt(11, b.getBookId()); // WHERE book_id = ?

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean insert(Book b) {
		String sql = "INSERT INTO books " + "(book_code, title, author, publisher, price, quantity_in_stock, "
				+ "image_url, description, category_id, can_show) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, b.getBookCode());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getPublisher());
			ps.setDouble(5, b.getPrice());
			ps.setInt(6, b.getQuantityInStock());
			ps.setString(7, b.getImageUrl());
			ps.setString(8, b.getDescription());

			if (b.getCategoryId() != null) {
				ps.setInt(9, b.getCategoryId());
			} else {
				ps.setNull(9, Types.INTEGER);
			}

			ps.setBoolean(10, b.isCanShow());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// các hàm khác: getAll(), findById(), update(), delete()...
}
