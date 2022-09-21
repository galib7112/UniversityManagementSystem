package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQlite4 {
	public static Connection Li_Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:library.db");
			return connection;
		}catch (Exception e) {
			return null;
		}
	}
}
