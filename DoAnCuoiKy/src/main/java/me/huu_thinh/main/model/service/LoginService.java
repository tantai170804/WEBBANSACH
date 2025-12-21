package me.huu_thinh.main.model.service;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;

public class LoginService {

    private final UserDAO userDAO;

    public LoginService() {
        this.userDAO = new UserDAO();
    }

    // Xử lý đăng nhập
    public User login(String username, String password) {

        // Validate dữ liệu đầu vào
        if (username == null || password == null) {
            return null;
        }
        username = username.trim();
        password = password.trim();

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        // Gọi DAO kiểm tra DB

        return userDAO.findByUsernameAndPassword(username, password);
    }
}
