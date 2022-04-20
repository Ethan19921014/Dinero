package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartId;
	private int custId;
	private int cartTotal;
	private List<CartItemDtoBean> items ;
	
	private String tradeDate;
	
	
	public CartDto() {
		
	}
	
	public void setItemCartId() {
		items.stream().forEach(item->item.setCartId(this.cartId));
	}
	
	public CartDto(int custId) {
		this.custId = custId;
		//test
		this.cartTotal = 1;
		this.items = new ArrayList<CartItemDtoBean>();
	}
	///
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(int cartTotal) {
		this.cartTotal = cartTotal;
	}
	
	@Override
	public String toString() {
		
		return "cartId "+cartId + " custId "+custId+" cartTotal "+cartTotal+" date"+tradeDate;
	}

	public List<CartItemDtoBean> getItems() {
		return items;
	}

	public void setItems(List<CartItemDtoBean> items) {
		this.items = items;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
}
