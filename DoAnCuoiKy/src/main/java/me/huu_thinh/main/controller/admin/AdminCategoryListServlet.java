package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.model.BookCategory;

@WebServlet("/admin/categories")
public class AdminCategoryListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BookCategoryDAO categoryDAO = new BookCategoryDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		try {
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		int limit = 10; 
		int offset = (page - 1) * limit;

		List<BookCategory> list = categoryDAO.findAll(limit, offset);

		int totalItems = categoryDAO.countAll();

		int totalPages = (int) Math.ceil((double) totalItems / limit);

		request.setAttribute("categories", list); // Danh sách đã cắt (5 phần tử)
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

		request.getRequestDispatcher("/html/admin/category-list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}