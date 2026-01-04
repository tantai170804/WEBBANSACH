package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.model.Book;
import me.huu_thinh.main.model.BookCategory;

@WebServlet("/admin/books/create")
public class AdminBookCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BookCategory> categories = BookCategoryDAO.getAll();
		request.setAttribute("categories", categories);
		// dùng chung 1 form cho create/edit
		request.setAttribute("mode", "create");
		request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

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

		double price = 0;
		int qty = 0;
		Integer categoryId = null;
		boolean canShow = "1".equals(canShowStr);

		try {
			if (priceStr != null && !priceStr.isBlank())
				price = Double.parseDouble(priceStr.trim());
			if (qtyStr != null && !qtyStr.isBlank())
				qty = Integer.parseInt(qtyStr.trim());
			if (categoryIdStr != null && !categoryIdStr.isBlank())
				categoryId = Integer.valueOf(categoryIdStr.trim());
		} catch (NumberFormatException ex) {
			Book b = new Book();
			b.setBookCode(bookCode);
			b.setTitle(title);
			b.setAuthor(author);
			b.setPublisher(publisher);
			b.setImageUrl(imageUrl);
			b.setDescription(description);
			b.setCategoryId(categoryId);
			b.setCanShow(canShow);

			request.setAttribute("error", "Giá / số lượng / thể loại không hợp lệ!");
			request.setAttribute("mode", "create");
			request.setAttribute("book", b);
			request.setAttribute("categories", BookCategoryDAO.getAll());
			request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
			return;
		}

		Book b = new Book();
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

		boolean ok = BookDAO.insert(b);
		if (!ok) {
			request.setAttribute("error", "Tạo sách thất bại!");
			request.setAttribute("mode", "create");
			request.setAttribute("book", b);
			request.setAttribute("categories", BookCategoryDAO.getAll());
			request.getRequestDispatcher("/html/admin/book-form.jsp").forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/admin/books");
	}

}
