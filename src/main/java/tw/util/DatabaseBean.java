package tw.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseBean implements Serializable{
	private DataSource dataSource;
	
	//JNDI
	public DatabaseBean() {
		try {
		Context initContext = new InitialContext();
		Context envContext = (Context)
					initContext.lookup("java:/comp/env");
		dataSource = (DataSource) envContext.lookup("jdbc/project2");
		} catch (NamingException ex) {
			throw new RuntimeException(ex);
		}
	}
	
		public boolean isConnectedOK() {
			try(Connection conn = dataSource.getConnection()) {
				return !conn.isClosed();
				}catch (SQLException e) {
					throw new RuntimeException(e);
		}
	}

		public static Connection getConnection() {
			// TODO Auto-generated method stub
			return null;
		}

}
