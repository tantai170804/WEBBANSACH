package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class BookDAO {

    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPrice(rs.getDouble("price"));
                b.setStock(rs.getInt("stock"));
                b.setDescription(rs.getString("description"));
                b.setImageUrl(rs.getString("image_url"));

                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book findById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book b = new Book();
                    b.setId(rs.getInt("id"));
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setPrice(rs.getDouble("price"));
                    b.setStock(rs.getInt("stock"));
                    b.setDescription(rs.getString("description"));
                    b.setImageUrl(rs.getString("image_url"));
                    return b;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
