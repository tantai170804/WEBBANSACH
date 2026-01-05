package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.model.Book;
import me.huu_thinh.main.model.BookCategory;
import me.huu_thinh.main.service.BookService;

@WebServlet("/admin/books/edit")
public class AdminBookEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = parseInt(request.getParameter("id"));
        if (id == null || id <= 0) {
            response.sendRedirect(request.getContextPath() + "/admin/books");
            return;
        }

        Book book = bookService.findById(id);
        if (book == null) {
            response.sendRedirect(request.getContextPath() + "/admin/books");
            return;
        }

        List<BookCategory> categories = BookCategoryDAO.getAll();
        request.setAttribute("categories", categories);
        request.setAttribute("mode", "edit");
        request.setAttribute("book", book);
        request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Integer bookId = parseInt(request.getParameter("bookId"));
        if (bookId == null || bookId <= 0) {
            response.sendRedirect(request.getContextPath() + "/admin/books");
            return;
        }

        Book b = new Book();
        b.setBookId(bookId);
        b.setBookCode(trim(request.getParameter("bookCode")));
        b.setTitle(trim(request.getParameter("title")));
        b.setAuthor(trim(request.getParameter("author")));
        b.setPublisher(trim(request.getParameter("publisher")));
        b.setImageUrl(trim(request.getParameter("imageUrl")));
        b.setDescription(trim(request.getParameter("description")));

        Double price = parseDouble(request.getParameter("price"));
        Integer qty = parseInt(request.getParameter("quantityInStock"));
        Integer categoryId = parseInt(request.getParameter("categoryId"));

        // nếu người dùng nhập sai số → báo lỗi thay vì 500
        if (request.getParameter("price") != null && !request.getParameter("price").isBlank() && price == null) {
            backToForm(request, response, b, "Giá không hợp lệ!");
            return;
        }
        if (request.getParameter("quantityInStock") != null && !request.getParameter("quantityInStock").isBlank() && qty == null) {
            backToForm(request, response, b, "Số lượng không hợp lệ!");
            return;
        }

        b.setPrice(price != null ? price : 0);
        b.setQuantityInStock(qty != null ? qty : 0);
        b.setCategoryId(categoryId);

        String canShowStr = request.getParameter("canShow");
        boolean canShow = (canShowStr == null) ? true : ("1".equals(canShowStr) || "true".equalsIgnoreCase(canShowStr));
        b.setCanShow(canShow);

        BookService.Result result = bookService.update(b);
        if (!result.isSuccess()) {
            backToForm(request, response, b, String.join(" ", result.getErrors()));
            return;
        }

        response.sendRedirect(request.getContextPath() + "/admin/books");
    }

    private void backToForm(HttpServletRequest request, HttpServletResponse response, Book b, String error)
            throws ServletException, IOException {
        List<BookCategory> categories = BookCategoryDAO.getAll();
        request.setAttribute("categories", categories);
        request.setAttribute("error", error);
        request.setAttribute("mode", "edit");
        request.setAttribute("book", b);
        request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
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
