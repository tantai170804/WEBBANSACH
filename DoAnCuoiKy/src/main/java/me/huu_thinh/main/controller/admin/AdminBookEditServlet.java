package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.model.Book;

@WebServlet("/admin/books/edit")
public class AdminBookEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/admin/books");
            return;
        }

        int id = Integer.parseInt(idStr);
        Book book = BookDAO.findById(id);
        if (book == null) {
            response.sendRedirect(request.getContextPath() + "/admin/books");
            return;
        }

        request.setAttribute("mode", "edit");
        request.setAttribute("book", book);
        request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int bookId = Integer.parseInt(request.getParameter("bookId"));

        String bookCode = request.getParameter("bookCode");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String priceStr = request.getParameter("price");
        String qtyStr = request.getParameter("quantityInStock");
        String imageUrl = request.getParameter("imageUrl");
        String description = request.getParameter("description");
        String categoryIdStr = request.getParameter("categoryId");
        String canShowStr = request.getParameter("canShow");

        double price = (priceStr == null || priceStr.isBlank()) ? 0 : Double.parseDouble(priceStr);
        int qty = (qtyStr == null || qtyStr.isBlank()) ? 0 : Integer.parseInt(qtyStr);
        Integer categoryId = (categoryIdStr == null || categoryIdStr.isBlank()) ? null : Integer.valueOf(categoryIdStr);
        boolean canShow = (canShowStr == null) ? true : "1".equals(canShowStr) || "true".equalsIgnoreCase(canShowStr);

        Book b = new Book();
        b.setBookId(bookId);
        b.setBookCode(bookCode);
        b.setTitle(title);
        b.setAuthor(author);
        b.setPublisher(publisher);
        b.setPrice(price);
        b.setQuantityInStock(qty);
        b.setImageUrl(imageUrl);
        b.setDescription(description);
        b.setCategoryId(categoryId);
        b.setCanShow(canShow);

        boolean ok = BookDAO.update(b);
        if (!ok) {
            request.setAttribute("error", "Cập nhật thất bại!");
            request.setAttribute("mode", "edit");
            request.setAttribute("book", b);
            request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/admin/books");
    }
}
