package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn_Model {
	 Connection connection;
	 public LogIn_Model() {
	 	connection = SQlite.St_Connector();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
	}
	 public boolean isConnected() {
		 try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	 }
	public boolean isLogin(String ID, String pass) throws SQLException {
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String query = "select * from Student where ID = ? and Password = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ID);
			preparedStatement.setString(2, pass);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
}
