package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.model.Book;
import me.huu_thinh.main.model.BookCategory;
import me.huu_thinh.main.service.BookService;

@WebServlet("/admin/books/create")
public class AdminBookCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<BookCategory> categories = BookCategoryDAO.getAll();
        request.setAttribute("categories", categories);
        request.setAttribute("mode", "create");
        request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Book b = new Book();
        b.setBookCode(trim(request.getParameter("bookCode")));
        b.setTitle(trim(request.getParameter("title")));
        b.setAuthor(trim(request.getParameter("author")));
        b.setPublisher(trim(request.getParameter("publisher")));
        b.setImageUrl(trim(request.getParameter("imageUrl")));
        b.setDescription(trim(request.getParameter("description")));

        // parse số an toàn
        Double price = parseDouble(request.getParameter("price"));
        Integer qty = parseInt(request.getParameter("quantityInStock"));
        Integer categoryId = parseInt(request.getParameter("categoryId"));

        b.setPrice(price != null ? price : 0);
        b.setQuantityInStock(qty != null ? qty : 0);
        b.setCategoryId(categoryId);

        boolean canShow = "1".equals(request.getParameter("canShow"));
        b.setCanShow(canShow);

        // validate + insert qua service
        BookService.CreateResult result = bookService.create(b);

        if (!result.isSuccess()) {
            request.setAttribute("mode", "create");
            request.setAttribute("book", b);
            request.setAttribute("categories", BookCategoryDAO.getAll());
            request.setAttribute("error", String.join(" ", result.getErrors()));
            request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/admin/books");
    }

    private String trim(String s) {
        return s == null ? null : s.trim();
    }

    private Integer parseInt(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDouble(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
