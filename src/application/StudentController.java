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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StudentController extends Admin_dashboard_controller implements Initializable {
	Connection connection,connection1,connection2;
	public StudentController(){
	 	connection = Sqlite3.C_Connector();
	 	connection1=Sqlite3.C_Connector1();
	 	connection2 = SQlite.St_Connector();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
		if(connection2==null) {
			if(connection2==null)
				System.exit(1);
		}
		if(connection1==null) {
			if(connection1==null)
				System.exit(1);
		}
	}
	
	
	
	@FXML
	private HBox MainHbox;
	@FXML
	private Label TitalBar;
	TableView<StudentList> table;
	VBox Vbox1,Vbox2;
	Label Tlabel1,Tlabel2,Tlabel3,Tlabel4,Tlabel5,Tlabel6,Tlabel7,Tlabel8,Tlabel9,Tlabel10,space;
	TextField Name,Id,email,age,FatherName,MotherName;
	DatePicker dathOfBirth,admissionDate;
	ComboBox<String> Batch;
	PasswordField password;
	Button save;
	public ObservableList<String> L2 =  FXCollections.observableArrayList();
	
	public ObservableList<StudentList> list =  FXCollections.observableArrayList();
	public void batchCombo() {
		L2.clear();
		try {
			String query = "select Batch from Batch";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				L2.add(resultSet.getString("Batch"));
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public void studentList() {
		
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(table);
		table = new TableView<StudentList>();
		table.setPrefWidth(1230);
		table.setPrefHeight(610);
		list.clear();
		try {
			String query = "select * from Student";
			PreparedStatement preparedStatement = connection2.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list.add(new StudentList(
						resultSet.getString("No"),
						resultSet.getString("ID"),
						resultSet.getString("Name"),
						resultSet.getString("Email"),
						resultSet.getString("AdmissionDate"),
						resultSet.getString("Batch"),
						resultSet.getString("Attandence"),
						resultSet.getString("Result")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		TableColumn<StudentList, String> c1= new TableColumn<StudentList, String> ("#");
		c1.setMinWidth(100);
		TableColumn<StudentList, String> c2= new TableColumn<StudentList, String> ("ID");
		c2.setMinWidth(200);
		TableColumn<StudentList, String> c3= new TableColumn<StudentList, String> ("Student Name");
		c3.setMinWidth(150);
		TableColumn<StudentList, String> c4= new TableColumn<StudentList, String> ("Email");
		c4.setMinWidth(200);
		TableColumn<StudentList, String> c5= new TableColumn<StudentList, String> ("Admission Date");
		c5.setMinWidth(150);
		TableColumn<StudentList, String> c6= new TableColumn<StudentList, String> ("Batch");
		c6.setMinWidth(100);
		TableColumn<StudentList, String> c7= new TableColumn<StudentList, String> ("Attandence");
		c7.setMinWidth(150);
		TableColumn<StudentList, String> c8= new TableColumn<StudentList, String> ("Result");
		c8.setMinWidth(100);
		TableColumn c9= new TableColumn("Manage");
		table.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9);
		c1.setCellValueFactory(new PropertyValueFactory<StudentList, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<StudentList, String>("ID"));
		c3.setCellValueFactory(new PropertyValueFactory<StudentList, String>("StudentName"));
		c4.setCellValueFactory(new PropertyValueFactory<StudentList, String>("Email"));
		c5.setCellValueFactory(new PropertyValueFactory<StudentList, String>("AdmissionDate"));
		c6.setCellValueFactory(new PropertyValueFactory<StudentList, String>("Batch"));
		c7.setCellValueFactory(new PropertyValueFactory<StudentList, String>("Attandence"));
		c8.setCellValueFactory(new PropertyValueFactory<StudentList, String>("result"));
		c9.setCellValueFactory(new PropertyValueFactory<StudentList, String>("Edit"));
		table.setItems(list);
		MainHbox.getChildren().addAll(table);
	}

	public void addStudent() {
		batchCombo();
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(table);
		TitalBar.setText("Add Student");
		Vbox1 = new VBox();
		Vbox1.setPrefWidth(613);
		Vbox1.setPrefHeight(600);
		Vbox1.setPadding(new Insets(25,100,20, 25));
		Vbox1.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		Vbox2 = new VBox();
		Vbox2.setPrefWidth(613);
		Vbox2.setPrefHeight(610);
		Vbox2.setPadding(new Insets(25,100,20, 25));
		Vbox2.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		
		
		Tlabel1 = new Label("Student Name");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMaxWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("ID");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Email");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		Tlabel4 = new Label("Age");
		Tlabel4.setMinHeight(40);
		Tlabel4.setMaxWidth(200);
		Tlabel4.setFont(new Font("Sanserif", 14));
		Tlabel5 = new Label("Father Name");
		Tlabel5.setMinHeight(40);
		Tlabel5.setMaxWidth(200);
		Tlabel5.setFont(new Font("Sanserif", 14));
		Tlabel6 = new Label("Mother Name");
		Tlabel6.setMinHeight(40);
		Tlabel6.setMaxWidth(200);
		Tlabel6.setFont(new Font("Sanserif", 14));
		Tlabel7 = new Label("Date of Birth");
		Tlabel7.setMinHeight(40);
		Tlabel7.setMaxWidth(200);
		Tlabel7.setFont(new Font("Sanserif", 14));
		Tlabel8 = new Label("Date of Admission");
		Tlabel8.setMinHeight(40);
		Tlabel8.setMaxWidth(200);
		Tlabel8.setFont(new Font("Sanserif", 14));
		Tlabel9 = new Label("Batch");
		Tlabel9.setMinHeight(40);
		Tlabel9.setMaxWidth(200);
		Tlabel9.setFont(new Font("Sanserif", 14));
		Tlabel10 = new Label("Password");
		Tlabel10.setMinHeight(40);
		Tlabel10.setMaxWidth(200);
		Tlabel10.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		Name = new TextField();
		Name.setFont(new Font("Sanserif", 14));
		Name.setMinHeight(40);
		Name.setMaxWidth(400);
		Id= new TextField();
		Id.setFont(new Font("Sanserif", 14));
		Id.setMinHeight(40);
		Id.setMaxWidth(400);
		email = new TextField();
		email.setFont(new Font("Sanserif", 14));
		email.setMinHeight(40);
		email.setMaxWidth(400);
		age= new TextField();
		age.setFont(new Font("Sanserif", 14));
		age.setMinHeight(40);
		age.setMaxWidth(400);
		FatherName = new TextField();
		FatherName.setFont(new Font("Sanserif", 14));
		FatherName.setMinHeight(40);
		FatherName.setMaxWidth(400);
		MotherName= new TextField();
		MotherName.setFont(new Font("Sanserif", 14));
		MotherName.setMinHeight(40);
		MotherName.setMaxWidth(400);
		dathOfBirth = new DatePicker();
		dathOfBirth.setPromptText("Date Of Birth");
		dathOfBirth.setMinHeight(40);
		dathOfBirth.setMaxWidth(400);
		admissionDate = new DatePicker();
		admissionDate.setPromptText("Date Of Admission");
		admissionDate.setMinHeight(40);
		admissionDate.setMaxWidth(400);
		Batch = new ComboBox<String>();
		Batch.setPromptText("Plese select");
		Batch.setMinHeight(40);
		Batch.setMaxWidth(400);
		Batch.setItems(L2);
		password=new PasswordField();
		password.setFont(new Font("Sanserif", 14));
		password.setMinHeight(40);
		password.setMaxWidth(400);
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(40);
		save.setMaxWidth(100); 
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox1.getChildren().addAll(Tlabel1,Name,Tlabel2,Id,Tlabel3,email,Tlabel4,age,Tlabel7,dathOfBirth,space,save);
		Vbox2.getChildren().addAll(Tlabel5,FatherName,Tlabel6,MotherName,Tlabel8,Batch,Tlabel9,admissionDate,Tlabel10,password);
		MainHbox.getChildren().addAll(Vbox1,Vbox2);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO Student(Name,ID,Email,Age, DateofBirth,FatherName,MotherName,Batch,AdmissionDate,Password)VALUES(?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection2.prepareStatement(query);
				preparedStatement.setString(1, Name.getText());
				preparedStatement.setString(2, Id.getText());
				preparedStatement.setString(3, email.getText());
				preparedStatement.setString(4, age.getText());
				preparedStatement.setString(5, ((TextField)dathOfBirth.getEditor()).getText());
				preparedStatement.setString(6, FatherName.getText());
				preparedStatement.setString(7, MotherName.getText());
				preparedStatement.setString(8, (String)Batch.getSelectionModel().getSelectedItem());
				preparedStatement.setString(9, ((TextField)admissionDate.getEditor()).getText());
				preparedStatement.setString(10, password.getText());
				
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
		Name.clear();
		Id.clear();
		email.clear();
		age.clear();
		dathOfBirth.setValue(null);
		FatherName.clear();
		MotherName.clear();
		Batch.setValue(null);
		admissionDate.setValue(null);
		password.clear();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TitalBar.setText("Student List");
		 studentList();
		
	}
	Admin_dashboard_controller AdminDC = new Admin_dashboard_controller();
	
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
	public void student(ActionEvent event) {
		TitalBar.setText("Student List");
		AdminDC.studentButtonAction(event);
	}
	public void library(ActionEvent event) {
		AdminDC.libraryButtonAction(event);
	}
	public void event(ActionEvent event) {
		AdminDC.EventAction(event);
	}
}
