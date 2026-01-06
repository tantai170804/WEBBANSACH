package me.huu_thinh.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.model.Book;
import me.huu_thinh.main.model.BookView;
import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.BookService;
import me.huu_thinh.main.service.CartService;

@WebServlet("/html/home")
public class HomeServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6259630436033547445L;
	private BookService bookservice;
	private CartService cartservice;
	
	
	public void init() throws ServletException {
		super.init();
		this.bookservice = new BookService();
		this.cartservice = new CartService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BookView> bookviews = loadBookViews(request,response);
		
		request.setAttribute("bookviews", bookviews);
		
		request.getServletContext().getRequestDispatcher("/html/home.jsp").forward(request, response);
	}
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request,response);
//	}
	
	public List<BookView> loadBookViews(HttpServletRequest request, HttpServletResponse response){
		List<BookView> bookviews = new ArrayList<BookView>();
		List<Book> booklist = BookDAO.getAll();
		HttpSession session = request.getSession();
		Object usero = (session != null)
                ? session.getAttribute("currentUser") //Session có attribute là user đã đăng nhập hiện tại
                : null;
		if(usero == null) {
			for(Book book : booklist) {
				bookviews.add(new BookView(book));
			}
		} else {
			User user = (User) usero;
			List<BookView> getbookviews = cartservice.loadBookViewFromUser(user.getUserId(), booklist);
			if(!getbookviews.isEmpty()) {
				bookviews.addAll(getbookviews);
			} else {
				for(Book book : booklist) {
					bookviews.add(new BookView(book));
				}
			}
		}
		return bookviews;
	}
	
}
