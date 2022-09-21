package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Sqlite3 {
	public static Connection C_Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite: Course.db");
			return connection;
		}catch (Exception e) {
			return null;
		}
	}
	public static Connection C_Connector1() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite: Subject.db");
			return connection;
		}catch (Exception e) {
			return null;
		}
	}
}
