package me.huu_thinh.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.huu_thinh.main.model.User;
import me.huu_thinh.main.model.service.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String LOGIN_JSP = "/html/login.jsp";
    private static final String HOME_PATH = "/home";
    private static final int SESSION_TIMEOUT_SECONDS = 30 * 60; // 30 phút

    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Giữ lại username để user khỏi nhập lại khi sai
        request.setAttribute("username", username);

        User user = loginService.login(username, password);

        if (user != null) {
            // Chống session fixation: hủy session cũ (nếu có) rồi tạo session mới
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(SESSION_TIMEOUT_SECONDS);

            // Lưu user (nếu User có field nhạy cảm thì nên lưu userId/role thay vì cả object)
            session.setAttribute("currentUser", user);

            response.sendRedirect(request.getContextPath() + HOME_PATH);
            return;
        }

        // Login thất bại
        request.setAttribute("error", "Sai username hoặc password");
        request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
    }
}
