package me.huu_thinh.main.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.UserService;

@WebServlet("/admin/users/delete")
public class AdminUserDeleteServlet extends HttpServlet {
	private final UserService userService = new UserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User current = (User) request.getSession().getAttribute("currentUser");
		Integer id = parseInt(request.getParameter("id"));

		if (id != null) {
			userService.delete(id, current);
		}
		response.sendRedirect(request.getContextPath() + "/admin/users");
	}

	private Integer parseInt(String s) {
		if (s == null || s.isBlank())
			return null;
		try {
			return Integer.parseInt(s.trim());
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
