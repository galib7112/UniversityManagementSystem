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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class TaskManagerController extends Admin_dashboard_controller implements Initializable{
	Admin_dashboard_controller AdminDC = new Admin_dashboard_controller();
	@FXML
	private Button taskList;
	@FXML
	private VBox TMVbox;
	VBox Vbox1;
	@FXML
	private Label label1;
	Label Tlabel1,Tlabel2,Tlabel3,Tlabel4,Tlabel5,space;
	TextField TaskName, AName;
	
	public ObservableList<String> L =  FXCollections.observableArrayList("Highest","Normal","Low");
	public ObservableList<String> L1 =  FXCollections.observableArrayList("Open","Closed");
	DatePicker date;
	Button save;
	ComboBox<String> pri,status;
	public ObservableList<task_manager> list =  FXCollections.observableArrayList();
	private TableView<task_manager> table;
	Connection connection;
	public TaskManagerController() {
		connection = Sqlite2.tm_Connector();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
	}
	public void ADDtask() {
		TMVbox.getChildren().remove(table);
		TMVbox.getChildren().remove(Vbox1);
		label1.setText("Assing Task");
		Vbox1 =  new VBox();
		Vbox1.setMinHeight(500);
		Vbox1.setPadding(new Insets(25, 800, 0, 25));
		Vbox1.setStyle("-fx-background-color: White");
		
		Tlabel1 = new Label("Task Name");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMaxWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Priority");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Enter Your Name");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		Tlabel4 = new Label("Task Date");
		Tlabel4.setMinHeight(40);
		Tlabel4.setMaxWidth(200);
		Tlabel4.setFont(new Font("Sanserif", 14));
		Tlabel5 = new Label("Status");
		Tlabel5.setMinHeight(40);
		Tlabel5.setMaxWidth(200);
		Tlabel5.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		TaskName = new TextField();
		TaskName.setFont(new Font("Sanserif", 14));
		TaskName.setMinHeight(40);
		TaskName.setMaxWidth(400);
		AName= new TextField();
		AName.setFont(new Font("Sanserif", 14));
		AName.setMinHeight(40);
		AName.setMaxWidth(400);
		date = new DatePicker();
		date.setPromptText("Date");
		date.setMinHeight(40);
		date.setMaxWidth(400);
		pri = new ComboBox<String>();
		pri.setPromptText("Plese select");
		pri.setMinHeight(40);
		pri.setMaxWidth(400);
		pri.setItems(L);
		pri.setId("CB1");
		status = new ComboBox<String>();
		status.setPromptText("Plese select");
		status.setMinHeight(40);
		status.setMaxWidth(400);
		status.setItems(L1);
		status.setId("CB2");
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox1.getChildren().addAll(Tlabel1,TaskName,Tlabel2,pri,Tlabel3,AName,Tlabel4,date,Tlabel5,status,space,save);
		TMVbox.getChildren().add(Vbox1);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO Task_Manager(TaskDescription,Priority,AssignBy,TaskDate, Statues)VALUES(?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, TaskName.getText());
				preparedStatement.setString(2, (String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(3, AName.getText());
				preparedStatement.setString(4, ((TextField)date.getEditor()).getText());
				preparedStatement.setString(5, (String)status.getSelectionModel().getSelectedItem());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Task added successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				fieldClear();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	} 
	public void fieldClear() {
		TaskName.clear();
		pri.setValue(null);
		AName.clear();
		status.setValue(null);
		date.setValue(null);
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
		TableColumn<task_manager, String> c4= new TableColumn<task_manager, String> ("Assign By");
		c4.setMinWidth(150);
		TableColumn<task_manager, String> c5= new TableColumn<task_manager, String> ("Task Date");
		c5.setMinWidth(150);
		TableColumn<task_manager, String> c6= new TableColumn<task_manager, String> ("Status");
		c6.setMinWidth(150);
		TableColumn c7= new TableColumn();
		
		table.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7);
		c1 .setCellValueFactory(new PropertyValueFactory<task_manager, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<task_manager, String>("TaskD"));
		c3.setCellValueFactory(new PropertyValueFactory<task_manager, String>("Pri"));
		c4.setCellValueFactory(new PropertyValueFactory<task_manager, String>("AssignBy"));
		c5.setCellValueFactory(new PropertyValueFactory<task_manager, String>("Taskdate"));
		c6.setCellValueFactory(new PropertyValueFactory<task_manager, String>("Status"));
		c7.setCellValueFactory(new PropertyValueFactory<task_manager, String>("Edit"));
		
		table.setItems(list);
		TMVbox.getChildren().add(table);
		
	}
	public void home(ActionEvent event) {
		AdminDC.home_buttonAction(event);
	}
	public void logout(ActionEvent event) {
		AdminDC.logout(event);
	}
	public void taskManager(ActionEvent event) {
		AdminDC.taskButtonAction(event);
	}
	public void academic(ActionEvent event) {
		AdminDC.AcademicAction(event);
	}
	public void TaskListAction(ActionEvent event) {
		label1.setText("Task Manager");
		TMVbox.getChildren().remove(table);
		TMVbox.getChildren().remove(Vbox1);
		shedule();	
	}
	public void student(ActionEvent event) {
		AdminDC.studentButtonAction(event);
	}
	public void library(ActionEvent event) {
		AdminDC.libraryButtonAction(event);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		label1.setText("Task Manager");
		shedule();	
	}
	public void event(ActionEvent event) {
		AdminDC.EventAction(event);
	}
}
