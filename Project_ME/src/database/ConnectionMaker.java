package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker {
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/test", "root", "me2015");//"jdbc:mysql://39.112.39.53:3306/test", "root", "me2015");
		return c;
	}
}
