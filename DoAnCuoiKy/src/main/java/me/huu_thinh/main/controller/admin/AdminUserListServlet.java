package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;

@WebServlet("/admin/users")
public class AdminUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDAO dao = new UserDAO();

		// 1. Thiết lập cấu hình phân trang
		int page = 1; // Mặc định là trang 1
		int limit = 10; // Số dòng trên mỗi trang (bạn có thể sửa thành 10 tùy ý)

		// Kiểm tra nếu URL có tham số ?page=...
		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
				if (page < 1)
					page = 1; // Không cho phép trang âm
			} catch (NumberFormatException e) {
				page = 1; // Nếu người dùng nhập linh tinh (?page=abc) thì về trang 1
			}
		}

		// 2. Tính toán Offset
		int offset = (page - 1) * limit;

		try {
			// 3. Gọi DAO để lấy dữ liệu
			int totalItems = dao.countAll(); // Đếm tổng số user trong DB
			List<User> list = dao.findAll(limit, offset); // Lấy danh sách user của trang hiện tại

			// 4. Tính tổng số trang (Total Pages)
			// Math.ceil: làm tròn lên (ví dụ 11 item / 5 = 2.2 => làm tròn thành 3 trang)
			int totalPages = (int) Math.ceil((double) totalItems / limit);

			// 5. Đẩy dữ liệu ra JSP
			request.setAttribute("users", list); // Danh sách user hiển thị
			request.setAttribute("currentPage", page); // Trang hiện tại
			request.setAttribute("totalPages", totalPages); // Tổng số trang

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi lấy dữ liệu: " + e.getMessage());
		}

		// 6. Chuyển hướng về trang giao diện
		// Lưu ý: Hãy đảm bảo đường dẫn JSP đúng với cấu trúc dự án của bạn
		request.getRequestDispatcher("/html/admin/user-list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}