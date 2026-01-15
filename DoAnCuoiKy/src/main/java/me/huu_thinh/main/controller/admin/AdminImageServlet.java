package me.huu_thinh.main.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/image")
public class AdminImageServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7762675248323948217L;
	
	
	public void init() throws ServletException{
		super.init();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String path = getServletContext().getRealPath("/book_img");
        File folder = new File(path);

        List<String> images = new ArrayList<>();
        if(folder.exists() && folder.isDirectory()) {
        	for(File f : folder.listFiles()) {
        		 String name = f.getName().toLowerCase();
                 if (name.endsWith(".jpg") || name.endsWith(".png")
                         || name.endsWith(".jpeg") || name.endsWith(".webp")
                         || name.endsWith(".gif")) {
                     images.add(f.getName());
                 }
        	}
        	
        }
        request.setAttribute("images", images);
        request.getRequestDispatcher("/html/admin/image.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String filename = request.getParameter("image");
		if(filename.isBlank()) {
		   response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lỗi khi xóa file do file bị trống");
			return;
		}
		String path = getServletContext().getRealPath("/book_img");
        File file = new File(path+"/"+filename);
        if(file.exists()) {
        	file.delete();
        }
        response.sendRedirect(request.getContextPath() + "/admin/image");
	}
	

}
