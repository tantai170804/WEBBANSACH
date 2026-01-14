package me.huu_thinh.main.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.huu_thinh.main.dao.CartDAO;
import me.huu_thinh.main.dto.BookViewInCartDTO;
import me.huu_thinh.main.dto.CartItemDTO;
import me.huu_thinh.main.dto.CartTotalResponseDTO;
import me.huu_thinh.main.model.Book;
import me.huu_thinh.main.model.Cart;

public class CartService {

	private CartDAO cartDAO;
	
	public CartService() {
		this.cartDAO = new CartDAO();
	}
	
	public boolean addCart(int userId,int bookId,int quantity) {
		return cartDAO.insert(userId,bookId,quantity);
	}
	public boolean removeCart(int userId, int bookId) {
		return cartDAO.delete(userId,bookId);
	}
	public boolean updateCart(int userId,int bookId,int quantity) {
		return cartDAO.update(userId,bookId,quantity);
	}
	public int getCurrentQuantity(int userId,int bookId) {
		return cartDAO.getQuatity(userId,bookId);
	}
	public List<Cart> getAllCartFromUser(int userId){
		return cartDAO.getAllFromUser(userId);
	}
	public List<CartItemDTO> getAllCartItemFromUser(int userId){
		 List<CartItemDTO> items = new ArrayList<>();
		List<Cart> cartlist =  getAllCartFromUser(userId);
		if(cartlist.isEmpty()) {
			return items;
		}
		BookService bookservice = new BookService();
		 for(Cart c : cartlist) {
   		  Book b = bookservice.findById(c.getBookId());
   		  if(b != null) {
   			  CartItemDTO item = new CartItemDTO(
   				        b.getBookId(),
   				        b.getTitle(),
   				        b.getImageUrl(),
   				        c.getQuantity(),
   				        b.getPrice()
   				    );
   			  items.add(item);
   		  }
		 }
		 return items;
	}
	public List<BookViewInCartDTO> loadBookViewFromUser(int userId,List<Book> books){
		
		List<BookViewInCartDTO> views = new ArrayList<BookViewInCartDTO>();
		if(books.isEmpty()) return views;
		List<Cart> carts = getAllCartFromUser(userId);
		if(carts.isEmpty()) return views;
		for(Book b : books) {
			BookViewInCartDTO bookview = new BookViewInCartDTO(b);
			for(Cart c : carts) {
				if(c.getBookId() == b.getBookId()) {
					bookview.setInCart(true);
					break;
				}
			}
			views.add(bookview);
		}
		return views;	
	}

	public boolean isCartEmpty(int userId) {
		return cartDAO.isCartEmpty(userId);
	}

	public boolean removeAllCart(int userId) {
		return cartDAO.deleteAll(userId);
	}

	public boolean hasInCart(int userId, int bid) {
		return getCurrentQuantity(userId, bid) >= 0;
	}

	public CartTotalResponseDTO updateCartToResponse(int userId,int bookId) {
		List<CartItemDTO> carts = getAllCartItemFromUser(userId);
		double totalPrice = carts.stream().mapToDouble(c -> c.getTotalPrice()).sum();
		double totalAllPrice = totalPrice + 30000;
		CartTotalResponseDTO dto = new CartTotalResponseDTO();
		
	    NumberFormat nf = NumberFormat.getCurrencyInstance(
	    	    new Locale("vi", "VN")
	    	);

	    dto.setCartTotalFormatted(nf.format(totalPrice));
	    dto.setCartAllTotalFormatted(nf.format(totalAllPrice));
		carts.stream()
        .filter(i -> i.getBookId() == bookId)
        .findFirst()
        .ifPresent(i -> {
            dto.setItemTotalFormatted(nf.format(i.getTotalPrice()));
        });
		return dto;
	}
}
