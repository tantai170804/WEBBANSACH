package me.huu_thinh.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.service.RegisterService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String REGISTER_JSP = "/html/register.jsp";
    private static final String LOGIN_PATH = "/login";

    private final RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(REGISTER_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName"); // optional

        // Giữ lại dữ liệu đã nhập để user khỏi nhập lại khi lỗi
        request.setAttribute("username", username);
        request.setAttribute("fullName", fullName);

        RegisterService.RegisterResult result =
                registerService.register(username, password, confirmPassword, fullName);

        if (result.isSuccess()) {
            // Đăng ký thành công -> về trang login
            response.sendRedirect(request.getContextPath() + LOGIN_PATH);
            return;
        }

        // Đăng ký thất bại -> forward lại + hiển thị lỗi
        request.setAttribute("error", result.getMessage());
        request.getRequestDispatcher(REGISTER_JSP).forward(request, response);
    }
}
