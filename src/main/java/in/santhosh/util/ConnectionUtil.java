package in.santhosh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	private ConnectionUtil() {

	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(System.getenv("spring.datasource.driver-class-name"));
		Connection connection = DriverManager.getConnection(System.getenv("spring.datasource.url"), System.getenv("spring.datasource.username"),System.getenv("spring.datasource.password"));
		System.out.println("Connection Created");
		return connection;
	}

	public static void close(Statement st, Connection con) {
		try {
			if (con != null && st != null) {
				con.close();
				st.close();
				System.out.println("Connection Released");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
