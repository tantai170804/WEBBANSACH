package me.huu_thinh.main.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.CartService;

@WebFilter("/*")
public class CartUpdateNumberFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession(false);
        if(session != null) {
        Object userobj = (session != null)
                ? session.getAttribute("currentUser") //Session có attribute là user đã đăng nhập hiện tại
                : null;

        int numberInCart = 0;
        if (userobj != null) {
        	User user = (User) userobj;
        	CartService cartservice = new CartService();
        	numberInCart = cartservice.getAllCartFromUser(user.getUserId()).size();
        } 
        session.setAttribute("numberInCart", numberInCart);
        }
        chain.doFilter(request, response);
	}

}
