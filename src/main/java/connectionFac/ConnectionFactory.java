package connectionFac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
//private static final Connection conn = createOneConnection(); 
private static final Connection conn = createConnection(); 
private static DataSource ds = null;
private static InitialContext ctxt = null;

public static void main(String[] args) {
	createConnection();
}
	
	//放進記憶體但不會執行
	private static Connection createConnection() {
		
		
		try {
			  //建立Context Object,連到JNDI Server
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/project2");
		     
		      //向DataSource要Connection
		      Connection  conn = ds.getConnection();
		      System.out.println("data base Connect");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Connection createOneConnection() {
		String urlStr = "jdbc:sqlserver://localhost:1433;databaseName=project2;user=sa;password=a29751101";
		try {
			Connection conn = DriverManager.getConnection(urlStr);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getConnection() {
		return conn;
	}
	
	public static void closeConnection() {
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
