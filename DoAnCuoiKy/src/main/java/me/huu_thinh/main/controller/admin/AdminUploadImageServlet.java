package me.huu_thinh.main.controller.admin;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/admin/image/upload")
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 5   // 5MB
)
public class AdminUploadImageServlet extends HttpServlet {

	private static final String SAVE_DIR = "book_img";
	private static final long serialVersionUID = 7243497318572502633L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("/html/admin/image-upload.jsp").forward(request, response);
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part part = request.getPart("image");
        if (part == null || part.getSize() == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Chưa chọn file");
            return;
        }
        if (!part.getContentType().startsWith("image/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File phải là hình ảnh");
            return;
        }
        
        String uploadPath = request.getServletContext().getRealPath("/"+SAVE_DIR);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String fileName = part.getSubmittedFileName();

        part.write(uploadPath + File.separator + fileName);

        
        response.sendRedirect(request.getContextPath() + "/admin/image");
    }
}

