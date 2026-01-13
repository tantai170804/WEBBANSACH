package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.BookCategoryDAO;
import me.huu_thinh.main.model.BookCategory;

@WebServlet("/admin/categories")
public class AdminCategoryListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Nên khai báo DAO ở ngoài hoặc dùng Dependency Injection nếu có thể
	private BookCategoryDAO categoryDAO = new BookCategoryDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Lấy trang hiện tại (Mặc định là 1)
		int page = 1;
		try {
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		// 2. Tính toán phân trang
		int limit = 10; // Số dòng trên 1 trang
		int offset = (page - 1) * limit;

		// 3. Gọi DB lấy dữ liệu
		// Lưu ý: Hàm findAll cần nhận limit và offset để LIMIT trong câu SQL
		List<BookCategory> list = categoryDAO.findAll(limit, offset);

		// Đếm tổng số bản ghi để tính tổng số trang
		int totalItems = categoryDAO.countAll();

		// 4. Tính tổng số trang
		int totalPages = (int) Math.ceil((double) totalItems / limit);

		// 5. Đẩy dữ liệu ra JSP
		request.setAttribute("categories", list); // Danh sách đã cắt (5 phần tử)
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

		// Chuyển hướng
		request.getRequestDispatcher("/html/admin/category-list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}