package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//������ ���̽� ���� Class
public class ConnectionMaker {
	// ������ ���̽� ���� method
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://121.150.27.246/test", "database", "me2015");
		return c;
	}
}
