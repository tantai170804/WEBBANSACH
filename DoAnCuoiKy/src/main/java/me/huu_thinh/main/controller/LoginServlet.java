package me.huu_thinh.main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/html/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = loginService.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentUser", user);

            // ✅ phân quyền điều hướng
            if ("admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/html/home");
            }
            return;
        }

        request.setAttribute("error", "Sai username hoặc password");
        request.setAttribute("username", username);
        request.getRequestDispatcher("/html/login.jsp").forward(request, response);
    }
}
