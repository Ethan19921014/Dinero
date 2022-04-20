package dao;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDao {

	void insertUser(User user) throws SQLException;

	User selectUser(long UserID);

	List<User> selectAllUser();

	boolean deleteUser(long UserID) throws SQLException;

	boolean updateUser(User user) throws SQLException;
	
	List<User> findByKeyword(User user) throws SQLException;
	



}