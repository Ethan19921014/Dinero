package tw.dinero.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.realm.DataSourceRealm;







public class ProductDao {
	private DataSource dataSource;
	
	public ProductDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)
					initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/project2");
		} catch (NamingException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public List<Product> findAll(){
		try(Connection conn=dataSource.getConnection()){
			   String sql="select * from product";
			   Statement State = conn.createStatement();
			   ResultSet rs = State.executeQuery(sql);
			   List<Product> list = new ArrayList<>();
			   while(rs.next()) {
			    Product p = new Product();
			    p.setProdId(rs.getInt("prodId"));
			    p.setStock(rs.getInt("stock"));
			    p.setPrice(rs.getInt("price"));
			    p.setProdName(rs.getString("prodName"));
			    p.setDescript(rs.getString("descript"));
			    p.setCategory(rs.getString("category"));
			    p.setPetkind(rs.getString("petkind"));
//			    p.setProdImg(rs.getInt("prodId"));
			    
			    list.add(p);
			   }
			   rs.close();
			   State.close();
			   return list;
			  } catch (SQLException e) {
			   throw new RuntimeException(e);
			  }	
	}
	
	public void addProduct(Product p) throws SQLException {
		
		try(Connection conn=dataSource.getConnection()){
			   String sql = "insert into product values(?,?,?,?,?,?,?)";
			   PreparedStatement preState = conn.prepareStatement(sql);
				preState.setInt(1, p.getProdId());
				preState.setInt(2, p.getStock());
				preState.setInt(3, p.getPrice());
				preState.setString(4, p.getProdName());
				preState.setString(5, p.getDescript());
				preState.setString(6, p.getCategory());
				preState.setString(7, p.getPetkind());
			
				preState.execute();
				preState.close();
			   
				preState.close();
			  } catch (SQLException e) {
			   throw new RuntimeException(e);
			  }	
	}
	
	public void deleteById(int prodId) throws SQLException {
		try(Connection conn=dataSource.getConnection()){
		String sql = "delete product where Prodid = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, prodId);
		
		preState.execute();
		preState.close();
	    }catch (SQLException e) {
		   throw new RuntimeException(e);
		}
	}
	
	public void updateAllbyId(Product p) throws SQLException {
		try(Connection conn=dataSource.getConnection()){
		String sql = "update product set stock=?, price=?, prodName=?, descript=?, category=?, petkind=? where prodId = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
//		preState.setInt(1, p.getProdId());
		preState.setInt(1, p.getStock());
		preState.setInt(2, p.getPrice());
		preState.setString(3, p.getProdName());
		preState.setString(4, p.getDescript());
		preState.setString(5, p.getCategory());
		preState.setString(6, p.getPetkind());
		preState.setInt(7, p.getProdId());
		
		preState.execute();
		preState.close();
		}catch (SQLException e) {
			   throw new RuntimeException(e);
		}
	}
	
	public List<Product> FindById(int prodId) {
		try (Connection conn = dataSource.getConnection()){
			String sql = "select * from product where prodId=?";
			PreparedStatement preState = conn.prepareStatement(sql);
			preState.setInt(1, prodId);
			ResultSet rs = preState.executeQuery();
			List<Product> list = new ArrayList<>();
			
			while(rs.next()) {
			
			Product p = new Product();
            p.setProdId(rs.getInt("prodId"));
            p.setStock(rs.getInt("stock"));
		    p.setPrice(rs.getInt("price"));
		    p.setProdName(rs.getString("prodName"));
		    p.setDescript(rs.getString("descript"));
		    p.setCategory(rs.getString("category"));
		    p.setPetkind(rs.getString("petkind"));	
            list.add(p);
			}
			
			rs.close();
			preState.close();
			
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Product> FindByProdName(String prodName){
		try (Connection conn = dataSource.getConnection()){
			String sql = "select * from product where prodName like ?";
		    PreparedStatement preState = conn.prepareStatement(sql);
		    preState.setString(1, prodName);
		    ResultSet rs = preState.executeQuery();
		    List<Product> list = new ArrayList<>();
		    
		    while(rs.next()) {
		    	
		    	Product p = new Product();
		    	p.setProdId(rs.getInt("prodId"));
		    	p.setStock(rs.getInt("stock"));
		    	p.setPrice(rs.getInt("price"));
		    	p.setProdName(rs.getString("prodName"));
		    	p.setDescript(rs.getString("descript"));
		    	p.setCategory(rs.getString("category"));
		    	p.setPetkind(rs.getString("petkind"));
		    	list.add(p);
		    }
		    rs.close();
		    preState.close();
		    
		    return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
		
	public boolean isConnectedOK() {
		try(Connection conn = dataSource.getConnection()){
	    	return !conn.isClosed();
	    	}catch (SQLException e) {
	    		throw new RuntimeException(e);
	    	}
	}
	
	

}
