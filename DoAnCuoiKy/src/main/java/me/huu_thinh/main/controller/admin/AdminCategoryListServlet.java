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

/**
 * Servlet implementation class AdminCategoryListServlet
 */

@WebServlet("/admin/categories")
public class AdminCategoryListServlet extends HttpServlet {
	
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        List<BookCategory> categories = BookCategoryDAO.getAll();
	        request.setAttribute("categories", categories);
	        request.getRequestDispatcher("/html/admin/category-list.jsp").forward(request, response);
	    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
