package me.huu_thinh.main.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.model.BookCategory;


@WebServlet("/admin/categories/create")
public class AdminCategoryCreateServlet extends HttpServlet {
      
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5236581439810969922L;

	@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        request.setAttribute("mode", "create");
	        request.setAttribute("category", new BookCategory());
	        request.getRequestDispatcher("/html/admin/category-form.jsp").forward(request, response);
	    }

	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        request.setCharacterEncoding("UTF-8");
	        String name = request.getParameter("name");
	        String description = request.getParameter("description");

	        if (name == null || name.isBlank()) {
	            request.setAttribute("error", "Tên thể loại không được để trống!");
	            request.setAttribute("mode", "create");
	            BookCategory c = new BookCategory();
	            c.setName(name);
	            c.setDescription(description);
	            request.setAttribute("category", c);
	            request.getRequestDispatcher("/html/admin/category-form.jsp").forward(request, response);
	            return;
	        }

	        BookCategory c = new BookCategory();
	        c.setName(name.trim());
	        c.setDescription(description);

	        boolean ok = BookCategoryDAO.insert(c);
	        if (!ok) {
	            request.setAttribute("error", "Thêm thể loại thất bại! (Có thể trùng tên)");
	            request.setAttribute("mode", "create");
	            request.setAttribute("category", c);
	            request.getRequestDispatcher("/html/admin/category-form.jsp").forward(request, response);
	            return;
	        }

	        response.sendRedirect(request.getContextPath() + "/admin/categories");
	    }
}
