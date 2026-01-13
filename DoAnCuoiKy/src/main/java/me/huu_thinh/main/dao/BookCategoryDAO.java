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

	public static List<BookCategory> findAll(int limit, int offset) {
		List<BookCategory> list = new ArrayList<>();

		// Sắp xếp ID giảm dần để dữ liệu mới nhất lên đầu
		String sql = "SELECT category_id, name, description FROM book_category "
				+ "ORDER BY category_id DESC LIMIT ? OFFSET ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, limit);
			ps.setInt(2, offset);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					BookCategory category = new BookCategory(rs.getInt("category_id"), rs.getString("name"),
							rs.getString("description"));
					list.add(category);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int countAll() {
		String sql = "SELECT COUNT(*) FROM book_category";
		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			return rs.next() ? rs.getInt(1) : 0;

		} catch (Exception e) { // Bắt Exception hoặc SQLException
			e.printStackTrace();
			return 0;
		}
	}

	public static List<BookCategory> getAll() {
		List<BookCategory> list = new ArrayList<>();

		String sql = "SELECT category_id, name, description FROM book_category";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				BookCategory category = new BookCategory(rs.getInt("category_id"), rs.getString("name"),
						rs.getString("description"));
				list.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static BookCategory getById(int id) {
		String sql = "SELECT category_id, name, description FROM book_category WHERE category_id = ?";
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new BookCategory(rs.getInt("category_id"), rs.getString("name"),
							rs.getString("description"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean insert(BookCategory c) {
		String sql = "INSERT INTO book_category(name, description) VALUES (?, ?)";
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, c.getName());
			ps.setString(2, c.getDescription());
			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean update(BookCategory c) {
		String sql = "UPDATE book_category SET name = ?, description = ? WHERE category_id = ?";
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, c.getName());
			ps.setString(2, c.getDescription());
			ps.setInt(3, c.getCategoryId());
			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Nếu category đang được books tham chiếu -> sẽ lỗi FK (không xóa được)
	public static boolean delete(int id) {
		String sql = "DELETE FROM book_category WHERE category_id = ?";
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// tiện ích: check category có đang được dùng không
	public static boolean isUsed(int id) {
		String sql = "SELECT COUNT(*) FROM books WHERE category_id = ?";
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static BookCategory findById(String id) {
		return null;
	}
}
