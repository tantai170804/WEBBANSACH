package me.huu_thinh.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.huu_thinh.main.database.DatabaseConnection;

public class PurchaseDAO {
	
	public int countAll() throws Exception {
	    String sql = "SELECT COUNT(*) FROM purchases"; 
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        return rs.next() ? rs.getInt(1) : 0;
	    }
	}

}
