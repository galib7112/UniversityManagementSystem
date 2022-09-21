package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQlite5 {
	public static Connection E_Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Event.db");
			return connection;
		}catch (Exception e) {
			return null;
		}
	}
}
