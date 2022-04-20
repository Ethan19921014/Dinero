package model;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionFac.ConnectionFactory;

public class CartItemDao {
	
	Connection conn;
	
	public CartItemDao() {
		this.conn = ConnectionFactory.getConnection();
		//this.conn = ConnectionFactory.createOneConnection();
	}
	
	public static void main(String[] args) {
		CartItemDao dao = new CartItemDao();
		List<CartItemDtoBean> items = dao.selectByOrdId("1323384027654131712");
		items.forEach(System.out::println);
		
	}
	
	public void insertIntoTable(CartItemDtoBean item)  {
//		
//		System.out.println(item.getCartId());
//		System.out.println(item.getProdId());
//		System.out.println(item.getPrice());
//		System.out.println(item.getQty());
//		System.out.println(item.getItemTotal());
		
		PreparedStatement preState = null;
		
		try {
		String sqlString = "insert into  cartItem(cartId,prodId,price,qty,itemTotal) values(?,?,?,?,?)";
		preState = conn.prepareStatement(sqlString);
		preState.setString(1, item.getCartId());
		
		preState.setInt(2, item.getProdId());
		preState.setInt(3, item.getPrice());
		preState.setInt(4, item.getQty());
		preState.setInt(5, item.getItemTotal());
		
		preState.executeUpdate();
		
		System.out.println("new 1 item ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				preState.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteItemByCartId(String cartId) {
		
		PreparedStatement preSate = null;
		
		String sqlQueryString = "delete from CartItem where cartId = '?' ";
		try {
			preSate = conn.prepareStatement(sqlQueryString);
			preSate.setString(1, cartId);
			preSate.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preSate.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public List<CartItemDtoBean> selectByOrdId(String cartId){
		String sqlQueryString = "Select * from cartItem where cartId = ?";
		PreparedStatement preState = null;
		
		try {
			List<CartItemDtoBean> items =  new ArrayList<CartItemDtoBean>();
			
			preState = conn.prepareStatement(sqlQueryString);
			preState.setString(1, cartId);
			ResultSet rs = preState.executeQuery();
			while(rs.next()) {
				CartItemDtoBean item = new CartItemDtoBean();
				item.setCartId(rs.getString(1));
				item.setProdId(rs.getInt(2));
				item.setPrice(rs.getInt(3));
				item.setQty(rs.getInt(4));
				item.setItemTotal(rs.getInt(5));
				items.add(item);
			}
			
			return items;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
