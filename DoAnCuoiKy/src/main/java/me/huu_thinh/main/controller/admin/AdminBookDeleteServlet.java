package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import me.huu_thinh.main.dao.BookDAO;

@WebServlet("/admin/books/delete")
public class AdminBookDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isBlank()) {
            int id = Integer.parseInt(idStr);
            BookDAO.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/admin/books");
    }
}
