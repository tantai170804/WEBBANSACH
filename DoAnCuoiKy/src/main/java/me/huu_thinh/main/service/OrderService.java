package me.huu_thinh.main.service;

import java.util.List;

import me.huu_thinh.main.dao.OrderDAO;
import me.huu_thinh.main.dao.OrderItemDAO;
import me.huu_thinh.main.dto.CartItemDTO;
import me.huu_thinh.main.model.Order;
import me.huu_thinh.main.model.User;

public class OrderService {

	private OrderDAO orderdao;
	private OrderItemDAO orderitemdao;
	private CartService cartservice;
	private int addtionPrice = 30000;
	
	public OrderService() {
		this.orderdao = new OrderDAO();
		this.orderitemdao = new OrderItemDAO();
		this.cartservice = new CartService();
	}
	
	public boolean checkout(User user,Order order) {
		if(cartservice.isCartEmpty(user.getUserId())) {
			return false;
		}
		List<CartItemDTO> carts = cartservice.getAllCartItemFromUser(user.getUserId());
		double totalBookPrice = carts.stream().mapToDouble(CartItemDTO::getTotalPrice).sum();
		order.setTotalPrice(totalBookPrice + addtionPrice);
		int orderId = orderdao.insert(order);
		if(orderId > 0) {
			for(CartItemDTO cart : carts) {
				orderitemdao.insert(orderId,cart);
			}
			cartservice.removeAllCart(user.getUserId());
			return true;
		}
		
		return false;
	}
}
