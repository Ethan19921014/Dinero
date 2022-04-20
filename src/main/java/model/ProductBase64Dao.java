package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductBase64Dao {
private DataSource dataSource;
	

	public ProductBase64Dao() {
		try {
			Context initContext = new InitialContext();
			 dataSource =(DataSource)initContext.lookup("java:comp/env/jdbc/project2");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertIntoDataBase(int prodId,String prodName,String answer) {
		try(Connection conn = dataSource.getConnection();){
			String sqlQuery = "Insert into productBase64(prodId,prodName,answer) values(?,?,?)";
			
			conn.setAutoCommit(false);
			PreparedStatement preState = conn.prepareStatement(sqlQuery);
			preState.setInt(1, prodId);
			preState.setString(2,prodName);
			preState.setString(3,answer);
			
			preState.executeUpdate();			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int prodId) {
		try(Connection conn = dataSource.getConnection();){
			
			conn.setAutoCommit(false);
			
			String sqlQuery = "delete from productBase64 where prodId = ?";

			PreparedStatement preState = conn.prepareStatement(sqlQuery);
			preState.setInt(1, prodId);

			preState.execute();

			System.out.println("刪除成功");
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String selectById(int prodId) {
		try(Connection conn = dataSource.getConnection()){
			String sqlQuery = "select answer from productBase64 where prodId = ?";
			
			
			PreparedStatement preState = conn.prepareStatement(sqlQuery);
			preState.setInt(1, prodId);
			
			ResultSet rs = preState.executeQuery();
			rs.next();
			String answer = rs.getString(1);
			return answer;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private class ProductBase64Bean{
		private int prodId;
		private String prodName;
		private String answer;
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
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		
		
	}
}


