package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.LoginBean;


public class LoginDao {
	
	private DataSource dataSource;
	
	public LoginDao() {
		try {
			Context initContext = new InitialContext();
			 dataSource =(DataSource)initContext.lookup("java:comp/env/jdbc/project2");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		
		boolean status =false;
		
		try (Connection connection = dataSource.getConnection();
		
			PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? ")){ 
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

			}catch (SQLException e) {
			// process sql exception
			System.err.println("商品新增失敗");
			e.printStackTrace();
		}
		return status;
	
		}
	}


	