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

import me.huu_thinh.main.dto.CartItemDTO;
import me.huu_thinh.main.model.Book;
import me.huu_thinh.main.model.Cart;
import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.BookService;
import me.huu_thinh.main.service.CartService;



@WebServlet("/cart")
public class CartServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CartService cartService;
	private BookService bookService;

	public void init() throws ServletException {
	        super.init();
	        cartService  = new CartService(); 
	        bookService  = new BookService();
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession();
    	 User user = (User) session.getAttribute("currentUser");
    	 List<Cart> carts = cartService.getAllCartFromUser(user.getUserId());
    	 List<CartItemDTO> items = new ArrayList<>();
    	 double cartTotalPrice = 0;
    	 for(Cart c : carts) {
    		  Book b = bookService.findById(c.getBookId());
    		  if(b != null) {
    			  CartItemDTO item = new CartItemDTO(
    				        b.getBookId(),
    				        b.getTitle(),
    				        b.getImageUrl(),
    				        c.getQuantity(),
    				        b.getPrice()
    				    );
    			  items.add(item);
    			  cartTotalPrice += item.getTotalPrice();
    		  }
    	 }
    	 double totalAllPrice = cartTotalPrice + 30000;
    	 request.setAttribute("cartItems", items);
    	 request.setAttribute("cartTotalBookPrice", cartTotalPrice);
    	 request.setAttribute("cartTotalAllPrice", totalAllPrice);
    	 request.getRequestDispatcher("/html/cart.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	loadCartAction(request,response);
    }

	private void loadCartAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
	      String action = request.getParameter("action");
	      if (action == null) {
	        response.sendRedirect("cart.jsp");
	        return;
	      }
	      switch (action) {
          case "add":
              addToCart(request,response);
              break;
          case "delete":
              deleteFromCart(request, response);
              break;
          case "update":
              updateQuantity(request, response);
              break;
	      }
	      
	}
	private void updateQuantity(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 int bid = Integer.parseInt(request.getParameter("bid"));
	     HttpSession session = request.getSession();
	     User user = (User) session.getAttribute("currentUser");
	     Book b = bookService.findById(bid);
	     if(b == null) {
	    	 session.setAttribute("snackbarMsg", "Lỗi không có sách, hãy tải lại trang ngay!");
	    	 session.setAttribute("snackbarType", "error");
	     } else {
	    	 boolean check = cartService.removeCart(user.getUserId(), bid);
	    	 if(check) {
	    		 session.setAttribute("snackbarMsg", "Đã xóa khỏi giỏ hàng thành công!");
	    		 session.setAttribute("snackbarType", "success");
	    	 } else {
	    		 session.setAttribute("snackbarMsg", "Lỗi khi cố xóa khỏi giỏ hàng");
	    		 session.setAttribute("snackbarType", "error");
	    	 }
	     }
	     response.sendRedirect(request.getContextPath() + "/html/home");
	}

	//Them sach vao cart
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 int bid = Integer.parseInt(request.getParameter("bid"));
	     HttpSession session = request.getSession();
	     User user = (User) session.getAttribute("currentUser");
	     Book b = bookService.findById(bid);
	     if(b == null) {
	    	 session.setAttribute("snackbarMsg", "Lỗi không có sách, hãy tải lại trang ngay!");
	    	 session.setAttribute("snackbarType", "error");
	     } else {
	    	 boolean check = cartService.addCart(user.getUserId(), bid, 1);
	    	 if(check) {
	    		 session.setAttribute("snackbarMsg", "Đã thêm vào giỏ hàng thành công!");
	    		 session.setAttribute("snackbarType", "success");
	    	 } else {
	    		 session.setAttribute("snackbarMsg", "Lỗi khi cố xóa khỏi giỏ hàng!");
	    		 session.setAttribute("snackbarType", "error");
	    	 }
	     }
	     response.sendRedirect(request.getContextPath() + "/html/home");
	}

	
	
}
