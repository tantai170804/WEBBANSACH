package me.huu_thinh.main.service;

import me.huu_thinh.main.dao.UserDAO;

public class RegisterService {

    private final UserDAO userDAO;

    public RegisterService() {
        this.userDAO = new UserDAO();
    }

    public RegisterResult register(String username,
                                   String password,
                                   String confirmPassword,
                                   String fullName) {

        String u = normalize(username);
        String p = normalize(password);
        String cp = normalize(confirmPassword);
        String fn = normalize(fullName); // optional

        // 1) Validate input
        if (u == null) {
            return RegisterResult.fail("Vui lòng nhập tên đăng nhập.");
        }
        if (p == null) {
            return RegisterResult.fail("Vui lòng nhập mật khẩu.");
        }
        if (cp == null) {
            return RegisterResult.fail("Vui lòng nhập lại mật khẩu.");
        }

        if (u.length() < 3) {
            return RegisterResult.fail("Tên đăng nhập phải có ít nhất 3 ký tự.");
        }
        if (p.length() < 6) {
            return RegisterResult.fail("Mật khẩu phải có ít nhất 6 ký tự.");
        }
        if (!p.equals(cp)) {
            return RegisterResult.fail("Mật khẩu xác nhận không khớp.");
        }

        // (Tuỳ chọn) validate fullName nếu bạn muốn bắt buộc
        // if (fn == null) return RegisterResult.fail("Vui lòng nhập họ tên.");

        // 2) Check trùng username
        if (userDAO.existsByUsername(u)) {
            return RegisterResult.fail("Tên đăng nhập đã tồn tại.");
        }

        // 3) Tạo user trong DB
        boolean created = userDAO.createUser(u, p, fn);

        if (!created) {
            return RegisterResult.fail("Đăng ký thất bại. Vui lòng thử lại.");
        }

        return RegisterResult.success();
    }

    private String normalize(String s) {
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    // DTO trả kết quả cho Servlet
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
