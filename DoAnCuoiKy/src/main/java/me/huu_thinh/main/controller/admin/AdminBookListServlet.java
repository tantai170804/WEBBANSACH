package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.dto.BookViewDTO;

@WebServlet("/admin/books")
public class AdminBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;
        int size = 5;

        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");

        if (pageStr != null) {
            try { page = Integer.parseInt(pageStr); } catch (NumberFormatException ignored) {}
        }
        if (sizeStr != null) {
            try { size = Integer.parseInt(sizeStr); } catch (NumberFormatException ignored) {}
        }

        if (page < 1) page = 1;
        if (size < 1) size = 5;
        if (size > 50) size = 50;

        int totalItems = BookDAO.countAllForAdmin();
        int totalPages = (int) Math.ceil(totalItems * 1.0 / size);

        if (totalPages == 0) totalPages = 1;
        if (page > totalPages) page = totalPages;

        int offset = (page - 1) * size;

        List<BookViewDTO> books = BookDAO.getPageForAdmin(offset, size);

        request.setAttribute("books", books);
        request.setAttribute("page", page);
        request.setAttribute("size", size);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalItems", totalItems);

        request.getRequestDispatcher("/html/admin/book-list.jsp").forward(request, response);
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
