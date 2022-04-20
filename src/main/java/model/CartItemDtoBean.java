package model;

import java.io.Serializable;

public class CartItemDtoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartId;
	private int prodId;
	private int price;
	private int qty;
	private int itemTotal;
	
	public CartItemDtoBean() {
		
	}
	
	public  CartItemDtoBean(CartDto cartDto) {
		
	}
	
	
	public String getCartId() {
		return cartId;
	}



	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	public void setCartId(CartDto cartDto) {
		this.cartId = cartDto.getCartId();
	}

	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getItemTotal() {
		return itemTotal;
	}
	
	//sum = p x q
	public void setItemTotal() {
		this.itemTotal = this.price*this.qty;
	}
	
	public void setItemTotal(int total) {
		this.itemTotal = total;
	}
	
	@Override
	public String toString() {
		
		return "cartId " +this.cartId+ " " +"prodId "+prodId+" "+ "price " +price +" "+ "qty " +qty + " " + "ItemToal "+itemTotal;
	}




}
