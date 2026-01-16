package me.huu_thinh.main.service;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;
import me.huu_thinh.main.util.PasswordEncoding;

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
	        
	        String password_hash = PasswordEncoding.encodingPassword(password);
	        
	        return userDAO.findByUsernameAndPassword(u, password_hash);
	    }

	    private String normalize(String s) {
	        if (s == null) return null;
	        s = s.trim();
	        return s.isEmpty() ? null : s;
	    }
	}


