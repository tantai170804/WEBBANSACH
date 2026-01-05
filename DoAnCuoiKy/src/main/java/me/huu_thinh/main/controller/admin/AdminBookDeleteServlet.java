package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import me.huu_thinh.main.service.BookService;

@WebServlet("/admin/books/delete")
public class AdminBookDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = parseInt(request.getParameter("id"));
        if (id != null) {
            bookService.delete(id);
        }

        response.sendRedirect(request.getContextPath() + "/admin/books");
    }

    private Integer parseInt(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
