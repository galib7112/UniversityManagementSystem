package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQlite {
	public static Connection St_Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Student.db");
			return connection;
		}catch (Exception e) {
			return null;
		}
	}
}