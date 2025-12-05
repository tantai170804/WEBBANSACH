package me.huu_thinh.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.model.Book;

@WebServlet("/html/home")
public class Home extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = BookDAO.getAll();
		request.setAttribute("books", books);
		request.getServletContext().getRequestDispatcher("/html/home.jsp").forward(request, response);
	}
//	public void doPost() {
//		
//	}
	
}
