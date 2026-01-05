package me.huu_thinh.main.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.dao.PurchaseDAO;
import me.huu_thinh.main.dao.UserDAO;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO;
    private BookCategoryDAO categoryDAO;
    private UserDAO userDAO;
    private PurchaseDAO purchaseDAO;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
        categoryDAO = new BookCategoryDAO();
        userDAO = new UserDAO();
        purchaseDAO = new PurchaseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Các thống kê cơ bản
        int totalBooks = safeCount(() -> bookDAO.countAll());
        int totalCategories = safeCount(() -> categoryDAO.countAll());
        int totalUsers = safeCount(() -> userDAO.countAll());

        // 2) Nếu dự án có purchase/order thì bật thêm
        int totalOrders = safeCount(() -> purchaseDAO.countAll());

        // 3) Đẩy sang JSP
        req.setAttribute("totalBooks", totalBooks);
        req.setAttribute("totalCategories", totalCategories);
        req.setAttribute("totalUsers", totalUsers);
        req.setAttribute("totalOrders", totalOrders);

        // Nếu bạn dùng activePage để highlight sidebar:
        req.setAttribute("activePage", "dashboard");

        req.getRequestDispatcher("/html/admin/dashboard.jsp").forward(req, resp);
    }

    // Helper tránh vỡ trang nếu 1 DAO chưa làm xong
    private int safeCount(CountSupplier supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return 0;
        }
    }

    @FunctionalInterface
    private interface CountSupplier {
        int get() throws Exception;
    }
}
