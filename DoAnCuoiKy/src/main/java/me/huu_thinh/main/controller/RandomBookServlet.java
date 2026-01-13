package me.huu_thinh.main.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.model.Book;

@WebServlet("/randombook")
public class RandomBookServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2237555271493442087L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = BookDAO.getAll();

        if (books == null || books.isEmpty()) {
            return;
        }

       
        Random random = new Random();
        Book randomBook = books.get(random.nextInt(books.size()));

        request.setAttribute("book", randomBook);
        request.getRequestDispatcher("/html/bookdetail.jsp").forward(request, response);
    }
}
