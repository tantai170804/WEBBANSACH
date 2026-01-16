package me.huu_thinh.main.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.dao.UserDAO;
// 1. Import OrderService
import me.huu_thinh.main.service.OrderService;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO;
    private BookCategoryDAO categoryDAO;
    private UserDAO userDAO;
    
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
        categoryDAO = new BookCategoryDAO();
        userDAO = new UserDAO();
        orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int totalBooks = safeCount(() -> bookDAO.countAll());
        int totalCategories = safeCount(() -> categoryDAO.countAll());
        int totalUsers = safeCount(() -> userDAO.countAll());

        int totalOrders = safeCount(() -> orderService.getTotalOrders());

        req.setAttribute("totalBooks", totalBooks);
        req.setAttribute("totalCategories", totalCategories);
        req.setAttribute("totalUsers", totalUsers);
        req.setAttribute("totalOrders", totalOrders);

        req.setAttribute("activePage", "dashboard");

        req.getRequestDispatcher("/html/admin/dashboard.jsp").forward(req, resp);
    }

    private int safeCount(CountSupplier supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            e.printStackTrace(); 
            return 0;
        }
    }

    @FunctionalInterface
    private interface CountSupplier {
        int get() throws Exception;
    }
}