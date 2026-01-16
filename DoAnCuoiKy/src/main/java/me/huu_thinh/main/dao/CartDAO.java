package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.database.DatabaseConnection;
import me.huu_thinh.main.model.Cart;

public class CartDAO {


	
	
	public List<Cart> getAllFromUser(int user_id) {
		List<Cart> list = new ArrayList<>();

		String sql = "SELECT * FROM carts WHERE user_id = ?";
		Connection conn = null;
		try  {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int cartid = rs.getInt("cart_id");
				int userid = rs.getInt("user_id");
				int bookid = rs.getInt("book_id");
				int quantity = rs.getInt("quantity");
				Date created_at = rs.getDate("created_at");
				Date updated_at = rs.getDate("updated_at");
				Cart category = new Cart(cartid, userid, bookid, quantity, created_at, updated_at);
				list.add(category);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean insert(int userId, int bookId, int quantity) {
			String sql = "INSERT INTO carts" + "(user_id, book_id, quantity) VALUES (?, ?, ?)";
			Connection conn = null;
			try  {
				conn = DatabaseConnection.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setInt(2, bookId);
				ps.setInt(3, quantity);

				return ps.executeUpdate() > 0;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		
	}

	public boolean update(int userId, int bookId, int quantity) {
		String sql = "UPDATE carts SET quantity = ? WHERE user_id = ? AND book_id = ?";

		Connection conn = null;
		try  {
			conn = DatabaseConnection.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, userId);
			ps.setInt(3, bookId);

			return ps.executeUpdate() > 0; 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean delete(int userId, int bookId) {
		String sql = "DELETE FROM carts WHERE user_id = ? AND book_id = ?";

		Connection conn = null;
		try  {
			conn = DatabaseConnection.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, bookId);

			return ps.executeUpdate() > 0; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean deleteAll(int userId) {
		String sql = "DELETE FROM carts WHERE user_id = ?";

		Connection conn = null;
		try  {
			conn = DatabaseConnection.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			return ps.executeUpdate() > 0; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public int getQuatity(int userId, int bookId) {
		String sql = "SELECT quantity FROM carts WHERE user_id = ? AND book_id = ?";
		Connection conn = null;
		try  {
			conn = DatabaseConnection.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, bookId);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("quantity");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	public boolean isCartEmpty(int userId) {
		return countCartFromUser(userId) == 0;
	}
	public int countCartFromUser(int userId) {
		String sql = "SELECT COUNT(*) FROM carts WHERE user_id = ?";
		Connection conn = null;
		try  {
			conn = DatabaseConnection.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
