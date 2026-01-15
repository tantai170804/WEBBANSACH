package me.huu_thinh.main.dto;

public class CartTotalResponseDTO {

    private String cartTotalFormatted;
    private String cartShippingPrice;
    private String cartAllTotalFormatted;
    private String itemTotalFormatted;
    
    
	public String getCartTotalFormatted() {
		return cartTotalFormatted;
	}
	public void setCartTotalFormatted(String cartTotalFormatted) {
		this.cartTotalFormatted = cartTotalFormatted;
	}
	public String getCartAllTotalFormatted() {
		return cartAllTotalFormatted;
	}
	public void setCartAllTotalFormatted(String cartAllTotalFormatted) {
		this.cartAllTotalFormatted = cartAllTotalFormatted;
	}
	public String getItemTotalFormatted() {
		return itemTotalFormatted;
	}
	public void setItemTotalFormatted(String itemTotalFormatted) {
		this.itemTotalFormatted = itemTotalFormatted;
	}
	public String getCartShippingPrice() {
		return cartShippingPrice;
	}
	public void setCartShippingPrice(String cartShippingPrice) {
		this.cartShippingPrice = cartShippingPrice;
	}

    
}
