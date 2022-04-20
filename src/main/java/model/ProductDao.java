package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import connectionFac.ConnectionFactory;

public class ProductDao  {

	public static void main(String[] args) {
//		ProductDao dao = new ProductDao();
//		List<ProductDto> tests = dao.selectAllProductFromDB();
//		tests.forEach((y)->System.out.println(y));
	}
	
	private DataSource dataSource;
	

	public ProductDao() {
		try {
			Context initContext = new InitialContext();
			 dataSource =(DataSource)initContext.lookup("java:comp/env/jdbc/project2");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public void create(ProductDto product) {
		//目前沒新增商品描述
		String sqlString = "Insert into  product(prodId,stock,price,prodName,descript,category,petkind)values(?,?,?,?,?,?,?)   ";
		try( Connection conn  =  dataSource.getConnection();) {
			conn.setAutoCommit(false);
			
			PreparedStatement preState = conn.prepareStatement(sqlString);
			preState.setInt(1, product.getProdId());
			preState.setInt(2, product.getStock());
			preState.setInt(3, product.getPrice());
			preState.setString(4, product.getProdName());
			preState.setString(5,product.getDescript());
			preState.setString(6, product.getCategory());
			preState.setString(7,product.getPetkind());
			
			
			preState.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("商品新增成功");
			preState.close();
			
		} catch (SQLException e) {
			
			System.err.println("商品新增失敗");
			e.printStackTrace();
		}
	}
	
	public List<ProductDto> selectAllProductFromDB(){
		String sqlQuery = "select * from product";
		try( Connection conn = dataSource.getConnection()){
			
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sqlQuery);
			List<ProductDto> products = new ArrayList<ProductDto>();
			while( rs.next() ) {
				ProductDto product = new ProductDto();
				product.setProdId(rs.getInt(1)); 
				product.setStock(rs.getInt(2));
				product.setPrice(rs.getInt(3));
				product.setProdName(rs.getString(4));
				product.setDescript(rs.getString(5));
				product.setCategory(rs.getString(6));
				product.setPetkind(rs.getString(6));
				products.add(product);
			}
			return products;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public ProductDto selectProdById(int id) {
		String sqlQuery = "select * from product where prodId = ?";
		try( Connection conn = dataSource.getConnection()){
			PreparedStatement preState = conn.prepareStatement(sqlQuery);
			preState.setInt(1, id);
			ResultSet rs = preState.executeQuery();
			ProductDto product = new ProductDto();
			while( rs.next() ) {
				
				product.setProdId(rs.getInt(1)); 
				product.setStock(rs.getInt(2));
				product.setPrice(rs.getInt(3));
				product.setProdName(rs.getString(4));
				product.setDescript(rs.getString(5));
				product.setCategory(rs.getString(6));
				product.setPetkind(rs.getString(6));
				
			}
			return product;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public Integer getProdStock(int prodId) {
		String sqlQuery = "select stock from product where prodId = ?";
		try(Connection conn = dataSource.getConnection()) {
			
			PreparedStatement preState = conn.prepareStatement(sqlQuery);
			preState.setInt(1,prodId);
			ResultSet rs = preState.executeQuery();
			int stock = 0;
			while(rs.next()) {
				stock = rs.getInt(1);
			}
			return stock;
			
			
		} catch (SQLException e) {
			
			return null;
		}
		
	}
	
	public void updateProdStock(int prodId,int stock,int qty) {
		String sqlQuery = "update product set stock = ? where prodId =? ";
		try(Connection conn = dataSource.getConnection()){
			
			stock -=  qty;
			
			PreparedStatement preState = conn.prepareStatement(sqlQuery);
			preState.setInt(1, stock);
			preState.setInt(2, prodId);
			preState.executeUpdate();
			System.out.println("更新庫存成功");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
