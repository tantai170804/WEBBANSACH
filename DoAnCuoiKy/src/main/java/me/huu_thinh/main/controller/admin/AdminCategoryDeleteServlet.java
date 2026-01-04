package me.huu_thinh.main.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;

@WebServlet("/admin/categories/delete")
public class AdminCategoryDeleteServlet extends HttpServlet {
		
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        int id = Integer.parseInt(request.getParameter("id"));

	        // Nếu category đang được sách dùng -> không xóa
	        if (BookCategoryDAO.isUsed(id)) {
	            // gửi thông báo bằng query param cho nhanh
	            response.sendRedirect(request.getContextPath() + "/admin/categories?msg=used");
	            return;
	        }

	        BookCategoryDAO.delete(id);
	        response.sendRedirect(request.getContextPath() + "/admin/categories");
	    }
	}


