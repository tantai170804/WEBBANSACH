package me.huu_thinh.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.model.Book;

@WebServlet("/bookdetail")
public class BookDetailServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2252249709395162678L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
	    Book book = BookDAO.findById(id);

	    request.setAttribute("book", book);
	    request.getServletContext().getRequestDispatcher("/html/bookdetail.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}

