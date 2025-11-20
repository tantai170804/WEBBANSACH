package controller;

import java.io.IOException;
import java.util.List;

import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {

    private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Book> books = bookDAO.findAll();
        req.setAttribute("books", books);

        req.getRequestDispatcher("/WEB-INF/views/list-books.jsp")
                .forward(req, resp);
    }
}
