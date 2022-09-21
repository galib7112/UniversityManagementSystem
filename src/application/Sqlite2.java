package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Sqlite2 {
	public static Connection tm_Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:TaskManager.db");
			return connection;
		}catch (Exception e) {
			return null;
		}
	}
}
