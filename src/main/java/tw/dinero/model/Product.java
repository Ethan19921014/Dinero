package tw.dinero.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Product {
	
	private String jdbcUri;
	private String username;
	private String password;
	
	public boolean isConnectedOK() {
		
		
		try(Connection conn = DriverManager.getConnection(jdbcUri, username, password)) {
			return !conn.isClosed();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int prodId;
	
	private int stock;
	
	private int price;
	
	private String prodName;
	
	
	private String descript;
	
	
	private String category;
	
	private String petkind;
	
//	private int prodImg;
	
//	public enum Category{
//		DRY, WET;
//	}
//	
//	public enum Petkind{
//		DOG, CAT;
//	}
	
	public Product() {
		
	}
	
	public int getProdId() {
		return prodId;
	}
	
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDescript() {
		return descript;
	}
	
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPetkind() {
		return petkind;
	}
	
	public void setPetkind(String petkind) {
		this.petkind = petkind;
	}

//	public int getProdImg() {
//		return prodImg;
//	}
//	
//	public void setProdImg(int prodImg) {
//		this.prodImg = prodImg;
//	}
}
