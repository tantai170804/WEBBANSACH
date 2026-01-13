package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dto.BookViewDTO;
import me.huu_thinh.main.service.BookService;

@WebServlet("/admin/books")
public class AdminBookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = parseIntOrDefault(request.getParameter("page"), 1);
        int size = parseIntOrDefault(request.getParameter("size"), 10);

        size = bookService.normalizeSize(size);

        int totalItems = bookService.countAllForAdmin();
        int totalPages = bookService.calcTotalPages(totalItems, size);

        page = bookService.clampPage(page, totalPages);

        List<BookViewDTO> books = bookService.getPageForAdmin(page, size);

        request.setAttribute("books", books);
        request.setAttribute("page", page);
        request.setAttribute("size", size);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalItems", totalItems);

        request.getRequestDispatcher("/html/admin/book-list.jsp").forward(request, response);
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
