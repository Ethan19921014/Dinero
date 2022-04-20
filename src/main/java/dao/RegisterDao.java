package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.User;

public class RegisterDao {
	private DataSource dataSource;

	
	public RegisterDao() {
		try {
			Context initContext = new InitialContext();
			 dataSource =(DataSource)initContext.lookup("java:comp/env/jdbc/project2");
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
	}

	public int registerEmployee(User employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ "  (name, email, username, password) VALUES "
				+ " (?, ?, ?, ?);";

		int result = 0;
		try (Connection connection = dataSource.getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, employee.getname());
			preparedStatement.setString(2, employee.getemail());
			preparedStatement.setString(3, employee.getUsername());
			preparedStatement.setString(4, employee.getPassword());

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// process sql exception
			System.err.println("會員新增失敗");
			e.printStackTrace();
		}
		return result;
	}

}
