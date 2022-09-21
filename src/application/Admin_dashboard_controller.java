package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin_dashboard_controller extends count  implements Initializable {
	Connection connection;
	@FXML
	private MenuButton userBT;
	@FXML
	private VBox Vbox1,mainVbox;
	TableView<task_manager> table;
	@FXML
	private Button home,academic,student,finance,library,payroll,performance,log_out,settings,event,task,messages,report;
	@FXML
	private Button total_student,total_employees,total_course,total_batch,activity,schedule,fee_reports;
	@FXML
	private Label St_lab,em_lab,co_lab,ba_lab,activity_lable;
	public ObservableList<task_manager> list =  FXCollections.observableArrayList();
	public Admin_dashboard_controller(){
	 	connection = Sqlite2.tm_Connector();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
	}
	count Tc = new count();
	public void home_buttonAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Admin_dashboard.fxml"));
			Scene scene = new Scene(root);
		
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage =  new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("University Managament System");
			primaryStage.getIcons().add(new Image("file:AppIcon.png"));
			primaryStage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void EventAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Event.fxml"));
			Scene scene = new Scene(root);
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage =  new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("University Managament System");
			primaryStage.getIcons().add(new Image("file:AppIcon.png"));
			primaryStage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void taskButtonAction(ActionEvent event) {
		try {
		   
			Parent root = FXMLLoader.load(getClass().getResource("taskManager.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=  new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("University Managament System");
			primaryStage.getIcons().add(new Image("file:AppIcon.png"));
			primaryStage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void studentButtonAction(ActionEvent event) {
		try {
		   
			Parent root = FXMLLoader.load(getClass().getResource("Studen.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=  new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("University Managament System");
			primaryStage.getIcons().add(new Image("file:AppIcon.png"));
			primaryStage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void libraryButtonAction(ActionEvent event) {
		try {
		   
			Parent root = FXMLLoader.load(getClass().getResource("Library.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=  new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("University Managament System");
			primaryStage.getIcons().add(new Image("file:AppIcon.png"));
			primaryStage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void AcademicAction(ActionEvent event) {
		try {
		   
			Parent root = FXMLLoader.load(getClass().getResource("Academic.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=  new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("University Managament System");
			primaryStage.getIcons().add(new Image("file:AppIcon.png"));
			primaryStage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void logout(ActionEvent event) {
		 try {
			    
				Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
				Scene scene = new Scene(root);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage primaryStage=  new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("University Managament System");
				primaryStage.getIcons().add(new Image("file:AppIcon.png"));
				primaryStage.show();
				((Node)event.getSource()).getScene().getWindow().hide();
			} catch(Exception e) {
				e.printStackTrace();
			}
	 }
	public void Activity(ActionEvent event) {
		activity_lable.setText("Activity");
	}
	public void Shedule(ActionEvent event) {
		activity_lable.setText("Task Manager");
		Vbox1.getChildren().remove(table);
		Vbox1.getChildren().add(table);
	}
	public void Fee_reports(ActionEvent event) {
		activity_lable.setText("Fee Reports");
		
	}
	public void shedule() {
		table = new TableView<task_manager>();
		list.clear();
		try {
			String query = "select * from Task_Manager";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list.add(new task_manager(
						resultSet.getString("No"),
						resultSet.getString("TaskDescription"),
						resultSet.getString("Priority"),
						resultSet.getString("AssignBy"),
						resultSet.getString("TaskDate"),
						resultSet.getString("Statues")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		TableColumn<task_manager, String> c1= new TableColumn<task_manager, String> ("#");
		c1.setMinWidth(100);
		TableColumn<task_manager, String> c2= new TableColumn<task_manager, String> ("Task Description");
		c2.setMinWidth(200);
		TableColumn<task_manager, String> c3= new TableColumn<task_manager, String> ("Priority");
		c3.setMinWidth(150);
		TableColumn<task_manager, String> c4= new TableColumn<task_manager, String> ("Task Date");
		c4.setMinWidth(150);
		TableColumn<task_manager, String> c5= new TableColumn<task_manager, String> ("Status");
		c5.setMinWidth(150);
		table.getColumns().addAll(c1,c2,c3,c4,c5);
		c1.setCellValueFactory(new PropertyValueFactory<task_manager, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<task_manager, String>("TaskD"));
		c3.setCellValueFactory(new PropertyValueFactory<task_manager, String>("Pri"));
		c4.setCellValueFactory(new PropertyValueFactory<task_manager, String>("Taskdate"));
		c5.setCellValueFactory(new PropertyValueFactory<task_manager, String>("Status"));
		table.setItems(list);
		Vbox1.getChildren().add(table);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		co_lab.setText(Tc.count1());
		ba_lab.setText(Tc.count2());
		St_lab.setText(Tc.count3());
		activity_lable.setText("Task Manager");
		shedule();	
	}
}