package me.huu_thinh.main.service;

import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.dao.OrderDAO;
import me.huu_thinh.main.dao.OrderItemDAO;
import me.huu_thinh.main.dto.CartItemDTO;
import me.huu_thinh.main.dto.OrderItemViewDTO;
import me.huu_thinh.main.model.Order;
import me.huu_thinh.main.model.OrderItem;
import me.huu_thinh.main.model.User;

public class OrderService {

	private OrderDAO orderDAO;
	private CartService cartservice;
	private OrderItemDAO orderitemDAO;
	private int addtionPrice = 30000;

	public OrderService() {
		this.orderDAO = new OrderDAO();
		this.orderitemDAO = new OrderItemDAO();
		this.cartservice = new CartService();
	}

	// 1. Đặt hàng
	public boolean placeOrder(Order order, List<OrderItem> items) {
		return orderDAO.createOrder(order, items);
	}

	public List<Order> getAllOrders() {
		return orderDAO.getAllOrders();
	}

	public Order getOrderById(int id) {
		return orderDAO.getOrderById(id);
	}

	public List<OrderItem> getOrderItems(int orderId) {
		return orderDAO.getOrderItems(orderId);
	}
	
	public void updateOrderStatus(int orderId, int status) {
		orderDAO.updateStatus(orderId, status);
	}
	public boolean checkout(User user, Order order) {
		if (cartservice.isCartEmpty(user.getUserId())) {
			return false;
		}
		List<CartItemDTO> carts = cartservice.getAllCartItemFromUser(user.getUserId());
		double totalBookPrice = carts.stream().mapToDouble(CartItemDTO::getTotalPrice).sum();
		order.setTotalPrice(totalBookPrice + addtionPrice);
		int orderId = orderDAO.insert(order);
		if (orderId > 0) {
			for (CartItemDTO cart : carts) {
				orderitemDAO.insert(orderId, cart);
			}
			cartservice.removeAllCart(user.getUserId());
			return true;
		}

		return false;
	}
	public List<OrderItemViewDTO> getOrderItemView(int userId){
		List<OrderItemViewDTO> list = new ArrayList<>();
		List<Order> orders = orderDAO.getAllOrdersFromUser(userId);
		for(Order o : orders) {
			List<OrderItem> items = orderitemDAO.getByOrderId(o.getId());
			if(!items.isEmpty()) {
				for(OrderItem item : items) {
					OrderItemViewDTO oiv = new OrderItemViewDTO();
					oiv.setBookTitle(item.getBookName());
					oiv.setImageUrl(item.getBookImage());
					oiv.setCreatedAt(o.getCreatedAt());
					oiv.setUpdatedAt(o.getUpdateAt());
					oiv.setQuantity(item.getQuantity());
					oiv.setStatus(o.getStatus());
					oiv.setTotalPrice(item.getPrice() * item.getQuantity());
					list.add(oiv);
				}
			}
		}
		return list;
	}
	public int getTotalOrders() {
		return orderDAO.countAll();
	}

	public List<Order> getOrdersPagination(int limit, int offset) {
		return orderDAO.findWithPagination(limit, offset);
	}
}