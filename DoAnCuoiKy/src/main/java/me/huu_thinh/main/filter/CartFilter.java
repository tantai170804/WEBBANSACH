package me.huu_thinh.main.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Filter cho cart, yêu cầu phải có đăng nhập thì mới có thao tác liên quan tới cart
@WebFilter(urlPatterns = {"/cart"})
public class CartFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        Object user = (session != null)
                ? session.getAttribute("currentUser") //Session có attribute là user đã đăng nhập hiện tại
                : null;

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login");//Đưa về đăng nhập, nếu người dùng chưa đăng nhập.
        } else {
            chain.doFilter(request, response);
        }
	}

}
