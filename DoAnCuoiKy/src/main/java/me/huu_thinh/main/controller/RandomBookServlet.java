package me.huu_thinh.main.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.model.Book;
import me.huu_thinh.main.service.BookService;

@WebServlet("/randombook")
public class RandomBookServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2237555271493442087L;
	private BookService bookservice;

	public void init() throws ServletException {
		super.init();
		this.bookservice = new BookService();
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = bookservice.getAllBook();

        if (books == null || books.isEmpty()) {
            return;
        }

       
        Random random = new Random();
        Book randomBook = books.get(random.nextInt(books.size()));

        request.setAttribute("book", randomBook);
        request.getRequestDispatcher("/html/bookdetail.jsp").forward(request, response);
    }
}
