package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class count {
	Connection connection,connection2;
	public count(){
	 	connection = Sqlite3.C_Connector();
	 	connection2 =  SQlite.St_Connector();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
		if(connection2==null) {
			if(connection2==null)
				System.exit(1);
		}
	}
	public String count1(){
		String n= new String();
		try {
			String query = "select count(No) from courses";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				n= resultSet.getString("count(No)");
				return n;
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return n;
	}
	public String count2(){
		String n= new String();
		try {
			String query = "select count(No) from Batch";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				n= resultSet.getString("count(No)");
				return n;
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return n;
	}
	public String count3(){
		String n= new String();
		try {
			String query = "select count(No) from Student";
			PreparedStatement preparedStatement = connection2.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				n= resultSet.getString("count(No)");
				return n;
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return n;
	}
}
