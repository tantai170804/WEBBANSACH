package me.huu_thinh.main.service;

import java.util.List;

import me.huu_thinh.main.dao.OrderDAO;
import me.huu_thinh.main.dao.OrderItemDAO;
import me.huu_thinh.main.dto.CartItemDTO;
import me.huu_thinh.main.model.Order;
import me.huu_thinh.main.model.OrderItem;
import me.huu_thinh.main.model.User;

public class OrderService {

	private OrderDAO orderDAO;
	private CartService cartservice;
	private OrderItemDAO orderitemdao;
	private int addtionPrice = 30000;

	public OrderService() {
		this.orderDAO = new OrderDAO();
	}

	// 1. Đặt hàng
	public boolean placeOrder(Order order, List<OrderItem> items) {
		// Có thể thêm logic kiểm tra tồn kho ở đây trước khi gọi DAO
		return orderDAO.createOrder(order, items);
	}

	// 2. Lấy danh sách tất cả đơn hàng (Admin)
	public List<Order> getAllOrders() {
		return orderDAO.getAllOrders();
	}

	// 3. Lấy thông tin đơn hàng theo ID
	public Order getOrderById(int id) {
		return orderDAO.getOrderById(id);
	}

	// 4. Lấy chi tiết sản phẩm trong đơn hàng
	// (Vì class Order không chứa List<OrderItem> nên ta cần hàm này riêng)
	public List<OrderItem> getOrderItems(int orderId) {
		return orderDAO.getOrderItems(orderId);
	}

	// 5. Cập nhật trạng thái
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
				orderitemdao.insert(orderId, cart);
			}
			cartservice.removeAllCart(user.getUserId());
			return true;
		}

		return false;
	}

	// Lấy tổng số đơn
	public int getTotalOrders() {
		return orderDAO.countAll();
	}

	// Lấy danh sách phân trang
	public List<Order> getOrdersPagination(int limit, int offset) {
		return orderDAO.findWithPagination(limit, offset);
	}
}