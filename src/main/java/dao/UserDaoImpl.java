package dao;

import java.nio.file.attribute.AclEntryPermission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import javax.websocket.OnError;

import model.User;

/**
 * This DAO class provides CRUD database operations for the table todos in the
 * database.
 * 
 * @author Ramesh Fadatare
 *
 */



public class UserDaoImpl implements UserDao {
	
	
	
	private DataSource dataSource;

	
	public  UserDaoImpl()  {
		try {
			Context initContext = new InitialContext();
			 dataSource =(DataSource)initContext.lookup("java:comp/env/jdbc/project2");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static final String INSERT_USER_SQL = "INSERT INTO users"
			+ "  (name,email,username,password) VALUES " + " (?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select UserID,name,email,Username,Password from users where UserID =?";
	private static final String SELECT_ALL_USER = "select * from users";
	private static final String DELETE_USER_BY_ID = "delete from users where userID = ?;";
	private static final String UPDATE_USER = "update users set name = ?, email= ?, Username =?,  Password=? where UserID = ?;";
	private static final String FINDBYKEYWORD =  "select * from users where concat(userId,name,email,username,password) like concat('%',?,'%') ;";



	@Override
	public void insertUser(User user) throws SQLException {
		

		
		// try-with-resource statement will auto close the connection.
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
			preparedStatement.setString(1, user.getname());
			preparedStatement.setString(2, user.getemail());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// process sql exception
			System.err.println("會員新增失敗");
			
			e.printStackTrace();
			
		}
		return;
	}

	@Override
	public User selectUser(long UserID) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = dataSource.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setLong(1, UserID);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long ID = rs.getLong("UserID");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String username = rs.getString("username");
//				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				String password = rs.getString("password");
				user = new User(ID, name, email, username,  password);
				System.out.println(rs);
			}
		} catch (SQLException e) {
			// process sql exception
			System.err.println("商品新增失敗");
			e.printStackTrace();
			
		}
		return user;
	}

	@Override
	public List<User> selectAllUser() {


		List<User> user = new ArrayList<>();


		try (Connection connection = dataSource.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);) {
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long UserID = rs.getLong("UserID");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String username = rs.getString("username");
//				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				String password = rs.getString("password");
			
				System.out.println("tsttt :)");
				user.add(new User(UserID,name, email, username, password));
			}
		} catch (SQLException e) {
			// process sql exception
			System.err.println("helloworld");
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public boolean updateUser(User user) throws SQLException {
		System.out.println("hohohelolo!");

		boolean rowUpdated;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER);) {
			statement.setString(1, user.getname());
			statement.setString(2, user.getemail());
			statement.setString(3, user.getUsername());
//			statement.setDate(4, JDBCUtils.getSQLDate(Profiles.getBirthday()));
			statement.setString(4, user.getPassword());
			statement.setLong(5, user.getUserID());
			rowUpdated = statement.executeUpdate() > 0;
			System.out.println("");
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteUser(long UserID) throws SQLException {
		
		System.out.println("wow");
		boolean rowDeleted;
		System.out.println("成功");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID);) {
			statement.setLong(1, UserID);
			rowDeleted = statement.executeUpdate() > 0;
			
		}
		return rowDeleted;
		
		
		// TODO Auto-generated method stub
	}
	
	
	@Override	
	public List<User> findByKeyword(User user) throws SQLException {
		
		List<User> userList = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(FINDBYKEYWORD);) {

		preparedStatement.setString(1,user.getname());
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			long UserID = rs.getLong("UserID");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String username = rs.getString("username");
//			LocalDate birthday = rs.getDate("birthday").toLocalDate();
			String password = rs.getString("password");
			userList.add(new User(UserID,name, email, username, password));

		}
		} catch (SQLException e) {
			// process sql exception
			System.err.println("helloworld");
			e.printStackTrace();
		}
		return userList;
	}
	
	
	//gewei code here
	public model.User getUserByUserAccAndPwd(String account ,String pwd){
		
		String sqlQuery = "Select * from users where userName = ? and  password =?";
		
		try(Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);	){
			
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, pwd);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			User user = new User();
			user.setUserID(rs.getInt(1));
			user.setname(rs.getString(2));
			user.setemail(rs.getString(3));
			user.setUsername(rs.getString(4));
			user.setPassword(rs.getString(5));
			
			return user;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}


}