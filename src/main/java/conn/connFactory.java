package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connFactory {

	private static final Connection conn = createConnection();

	private static Connection createConnection() {

		String urlStr = "jdbc:sqlserver://localhost:1433; databaseName=project2; user=sa; password=a29751101";
		try {
			
			Connection conn = DriverManager.getConnection(urlStr);
			return conn;
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
	}

}