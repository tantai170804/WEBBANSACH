package me.huu_thinh.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2263433387991613276L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/html/userinfo.jsp").forward(request, response);
	}

}
