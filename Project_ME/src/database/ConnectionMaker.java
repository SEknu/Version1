package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//데이터 베이스 연결 Class
public class ConnectionMaker {
	// 데이터 베이스 연결 method
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/test", "root", "me2015");
		return c;
	}
}
