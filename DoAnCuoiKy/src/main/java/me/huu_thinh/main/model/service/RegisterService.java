package me.huu_thinh.main.model.service;

import me.huu_thinh.main.dao.UserDAO;

public class RegisterService {

    private final UserDAO userDAO;

    public RegisterService() {
        this.userDAO = new UserDAO();
    }

    public RegisterResult register(String username, String password, String confirmPassword, String fullName) {

        if (username == null || password == null || confirmPassword == null) {
            return RegisterResult.fail("Thiếu dữ liệu");
        }

        username = username.trim();
        password = password.trim();
        confirmPassword = confirmPassword.trim();
        if (fullName != null) fullName = fullName.trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return RegisterResult.fail("Vui lòng nhập đầy đủ thông tin");
        }

        if (!password.equals(confirmPassword)) {
            return RegisterResult.fail("Mật khẩu nhập lại không khớp");
        }

        if (userDAO.existsByUsername(username)) {
            return RegisterResult.fail("Username đã tồn tại");
        }

        boolean ok = userDAO.createUser(username, password, fullName); // (plaintext) đơn giản
        if (!ok) {
            return RegisterResult.fail("Đăng ký thất bại, thử lại sau");
        }

        return RegisterResult.success();
    }

    // DTO kết quả
    public static class RegisterResult {
        private final boolean success;
        private final String message;

        private RegisterResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public static RegisterResult success() {
            return new RegisterResult(true, null);
        }

        public static RegisterResult fail(String message) {
            return new RegisterResult(false, message);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
