package me.huu_thinh.main.service;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;

public class LoginService {

	    private final UserDAO userDAO;

	    public LoginService() {
	        this.userDAO = new UserDAO();
	    }

	    /**
	     * Đăng nhập: trả về User nếu đúng, ngược lại trả null.
	     */
	    public User login(String username, String password) {
	        String u = normalize(username);
	        String p = normalize(password);

	        if (u == null || p == null) return null;

	        // Gọi DAO kiểm tra DB
	        return userDAO.findByUsernameAndPassword(u, p);
	    }

	    /**
	     * Chuẩn hoá input: null -> null, trim, rỗng -> null
	     */
	    private String normalize(String s) {
	        if (s == null) return null;
	        s = s.trim();
	        return s.isEmpty() ? null : s;
	    }
	}


