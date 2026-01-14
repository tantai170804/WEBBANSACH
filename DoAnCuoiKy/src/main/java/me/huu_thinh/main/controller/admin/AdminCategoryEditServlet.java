package me.huu_thinh.main.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.model.BookCategory;

@WebServlet("/admin/categories/edit")
public class AdminCategoryEditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5911450895435226311L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		BookCategory c = BookCategoryDAO.getById(id);

		if (c == null) {
			response.sendRedirect(request.getContextPath() + "/admin/categories");
			return;
		}

		request.setAttribute("mode", "edit");
		request.setAttribute("category", c);
		request.getRequestDispatcher("/html/admin/category-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		if (name == null || name.isBlank()) {
			BookCategory c = new BookCategory(id, name, description);
			request.setAttribute("error", "Tên thể loại không được để trống!");
			request.setAttribute("mode", "edit");
			request.setAttribute("category", c);
			request.getRequestDispatcher("/html/admin/category-form.jsp").forward(request, response);
			return;
		}

		BookCategory c = new BookCategory(id, name.trim(), description);
		boolean ok = BookCategoryDAO.update(c);

		if (!ok) {
			request.setAttribute("error", "Cập nhật thất bại! (Có thể trùng tên)");
			request.setAttribute("mode", "edit");
			request.setAttribute("category", c);
			request.getRequestDispatcher("/html/admin/category-form.jsp").forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/admin/categories");
	}

}
