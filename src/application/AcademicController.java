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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AcademicController extends Admin_dashboard_controller implements Initializable{

	Connection connection,connection1;
	public AcademicController(){
	 	connection = Sqlite3.C_Connector();
	 	connection1=Sqlite3.C_Connector1();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
		if(connection1==null) {
			if(connection1==null)
				System.exit(1);
		}
	}
	
	@FXML
	private HBox MainHbox;
	
	VBox Vbox1,Vbox2,Vbox3,Vbox4,Vbox5,Vbox6;
	Button save;
	int n=0;
	Label Tlabel1,Tlabel2,Tlabel3,Tlabel4,Tlabel5,space,Tlabel6,Tlabel7,label;
	TextField courseName,description,code, map,twd,syllabusName,batch,mns,description1,subjectName,subjectCode;
	ComboBox<String> attendanceType,course,classTeacher,Cbatch,subject,course1,Cbatch1,department;
	public ObservableList<AddCourse> list =  FXCollections.observableArrayList();
	public ObservableList<AddBatch> list1 =  FXCollections.observableArrayList();
	public ObservableList<ClassTeacherAllocation> list2 =  FXCollections.observableArrayList();
	public ObservableList<AddSubject> list3 =  FXCollections.observableArrayList();
	public ObservableList<AssignSubject> list4 =  FXCollections.observableArrayList();
	public ObservableList<subjectAllocation> list5 =  FXCollections.observableArrayList();
	public ObservableList<String> L4 =  FXCollections.observableArrayList();
	public ObservableList<String> L5 =  FXCollections.observableArrayList("CSE","BBA","EEE","SE","CS","EST");
	TableView<AddCourse> table;
	TableView<AddBatch> table1;
	TableView<ClassTeacherAllocation> table2;
	TableView<AddSubject> table3;
	TableView<AssignSubject> table4;
	TableView<subjectAllocation> table5;
	DatePicker Sdate,Edate;
	public ObservableList<String> L =  FXCollections.observableArrayList("Daily","Weekly","Monthly");
	public ObservableList<String> L1 =  FXCollections.observableArrayList();
	public ObservableList<String> L2 =  FXCollections.observableArrayList();
	public ObservableList<String> L3 =  FXCollections.observableArrayList();
	
	//Add course****************
	public void refresh1() {
		list.clear();
		try {
			String query = "select * from courses";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list.add(new AddCourse(
						resultSet.getString("No"),
						resultSet.getString("CourseName"),
						resultSet.getString("Description"),
						resultSet.getString("Code"),
						resultSet.getString("AttandencePercentage"),
						resultSet.getString("AttandenceType"),
						resultSet.getString("TotalWorkingDay"),
						resultSet.getString("SyllabusName")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void AddCourse() {
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(table1);
		MainHbox.getChildren().remove(table2);
		MainHbox.getChildren().remove(table3);
		MainHbox.getChildren().remove(Vbox4);
		MainHbox.getChildren().remove(table4);
		MainHbox.getChildren().remove(Vbox5);
		MainHbox.getChildren().remove(table5);
		MainHbox.getChildren().remove(Vbox6);
		table = new TableView<AddCourse>();
		table.setStyle("-fx-background-radius: 5");
		refresh1();
		TableColumn<AddCourse, String> c1= new TableColumn<AddCourse, String> ("#");
		c1.setMinWidth(40);
		TableColumn<AddCourse, String> c2= new TableColumn<AddCourse, String> ("Course Name");
		c2.setMinWidth(150);
		TableColumn<AddCourse, String> c3= new TableColumn<AddCourse, String> ("Code");
		c3.setMinWidth(100);
		TableColumn<AddCourse, String> c4= new TableColumn<AddCourse, String> ("Minimum Attendance(%)");
		c4.setMinWidth(150);
		TableColumn<AddCourse, String> c5= new TableColumn<AddCourse, String> ("Attendance Type");
		c5.setMinWidth(125);
		TableColumn c6= new TableColumn("Manage");
		table.getColumns().addAll(c1,c2,c3,c4,c5,c6);
		c1.setCellValueFactory(new PropertyValueFactory<AddCourse, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<AddCourse, String>("CourseName"));
		c3.setCellValueFactory(new PropertyValueFactory<AddCourse, String>("Code"));
		c4.setCellValueFactory(new PropertyValueFactory<AddCourse, String>("MAP"));
		c5.setCellValueFactory(new PropertyValueFactory<AddCourse, String>("AT"));
		c6.setCellValueFactory(new PropertyValueFactory<AddCourse, String>("Edit"));
		table.setItems(list);
		
		Vbox1 =  new VBox();
		Vbox1.setPrefWidth(607);
		Vbox1.setPrefHeight(650);
		Vbox1.setPadding(new Insets(5,150,10, 25));
		Vbox1.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Add Course");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		Tlabel1 = new Label("Course Name");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Description");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Course Code");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		Tlabel4 = new Label("Minimum Attendance Percentage");
		Tlabel4.setMinHeight(40);
		Tlabel4.setMaxWidth(300);
		Tlabel4.setFont(new Font("Sanserif", 14));
		Tlabel5 = new Label("Attendance Type");
		Tlabel5.setMinHeight(40);
		Tlabel5.setMaxWidth(200);
		Tlabel5.setFont(new Font("Sanserif", 14));
		Tlabel6 = new Label("Total Working Day");
		Tlabel6.setMinHeight(40);
		Tlabel6.setMaxWidth(200);
		Tlabel6.setFont(new Font("Sanserif", 14));
		Tlabel7 = new Label("Syllabus Name");
		Tlabel7.setMinHeight(40);
		Tlabel7.setMaxWidth(200);
		Tlabel7.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		courseName = new TextField();
		courseName.setFont(new Font("Sanserif", 14));
		courseName.setMinHeight(40);
		courseName.setMaxWidth(400);
		description= new TextField();
		description.setFont(new Font("Sanserif", 14));
		description.setMinHeight(40);
		description.setMaxWidth(400);
		code= new TextField();
		code.setFont(new Font("Sanserif", 14));
		code.setMinHeight(40);
		code.setMaxWidth(400);
		map= new TextField();
		map.setFont(new Font("Sanserif", 14));
		map.setMinHeight(40);
		map.setMaxWidth(400);
		attendanceType = new ComboBox<String>();
		attendanceType.setPromptText("Plese select");
		attendanceType.setMinHeight(35);
		attendanceType.setMaxWidth(400);
		attendanceType.setItems(L);
		twd= new TextField();
		twd.setFont(new Font("Sanserif", 14));
		twd.setMinHeight(40);
		twd.setMaxWidth(400);
		syllabusName= new TextField();
		syllabusName.setFont(new Font("Sanserif", 14));
		syllabusName.setMinHeight(40);
		syllabusName.setMaxWidth(400);
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox1.getChildren().addAll(label,Tlabel1,courseName,Tlabel2,description,Tlabel3,code,Tlabel4,map,Tlabel5,attendanceType,Tlabel6,twd,Tlabel7,syllabusName,space,save);
		MainHbox.getChildren().addAll(Vbox1,table);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO courses(CourseName,Description,Code,AttandencePercentage, AttandenceType,TotalWorkingDay,SyllabusName)VALUES(?,?,?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, courseName.getText());
				preparedStatement.setString(2, description.getText());//(String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(3, code.getText());
				preparedStatement.setString(4, map.getText());
				preparedStatement.setString(5, (String)attendanceType.getSelectionModel().getSelectedItem());
				preparedStatement.setString(6, twd.getText());
				preparedStatement.setString(7, syllabusName.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Course added successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				fieldClear();
				refresh1() ;
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	public void fieldClear() {
		courseName.clear();
		description.clear();
		code.clear();
		map.clear();
		attendanceType.setValue(null);
		twd.clear();
		syllabusName.clear();
		
	}
	
	//Add Batch*******************
	public void refresh2() {
		list1.clear();
		try {
			String query = "select * from Batch";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list1.add(new AddBatch(
						resultSet.getString("No"),
						resultSet.getString("Course"),
						resultSet.getString("Batch"),
						resultSet.getString("MNS"),
						resultSet.getString("StartDate"),
						resultSet.getString("EndDate")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void courseCombo() {
		L1.clear();
		try {
			String query = "select CourseName from courses";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				L1.add(resultSet.getString("CourseName"));
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
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
	public void teacherCombo() {
		L3.clear();
		try {
			String query = "select TeachersName from Teachers";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				L3.add(resultSet.getString("TeachersName"));
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public void AddBatch() {
		courseCombo();
		refresh2();
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(table1);
		MainHbox.getChildren().remove(table2);
		MainHbox.getChildren().remove(table3);
		MainHbox.getChildren().remove(Vbox4);
		MainHbox.getChildren().remove(table4);
		MainHbox.getChildren().remove(Vbox5);
		MainHbox.getChildren().remove(table5);
		MainHbox.getChildren().remove(Vbox6);
		table1 = new TableView<AddBatch>();
		table1.setStyle("-fx-background-radius: 5");
		refresh1();
		TableColumn<AddBatch, String> c1= new TableColumn<AddBatch, String> ("#");
		c1.setMinWidth(40);
		TableColumn<AddBatch, String> c2= new TableColumn<AddBatch, String> ("Course");
		c2.setMinWidth(150);
		TableColumn<AddBatch, String> c3= new TableColumn<AddBatch, String> ("Batch");
		c3.setMinWidth(100);
		TableColumn<AddBatch, String> c4= new TableColumn<AddBatch, String> ("Starting Date");
		c4.setMinWidth(150);
		TableColumn<AddBatch, String> c5= new TableColumn<AddBatch, String> ("Ending Date");
		c5.setMinWidth(125);
		TableColumn c6= new TableColumn("Manage");
		table1.getColumns().addAll(c1,c2,c3,c4,c5,c6);
		c1.setCellValueFactory(new PropertyValueFactory<AddBatch, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<AddBatch, String>("Course"));
		c3.setCellValueFactory(new PropertyValueFactory<AddBatch, String>("batch"));
		c4.setCellValueFactory(new PropertyValueFactory<AddBatch, String>("StartDate"));
		c5.setCellValueFactory(new PropertyValueFactory<AddBatch, String>("EndDate"));
		c6.setCellValueFactory(new PropertyValueFactory<AddBatch, String>("Edit"));
		table1.setItems(list1);
		
		Vbox2 =  new VBox();
		Vbox2.setPrefWidth(607);
		Vbox2.setPrefHeight(650);
		Vbox2.setPadding(new Insets(5,150,10, 25));
		Vbox2.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Add Batch");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		Tlabel1 = new Label("Course");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Batch Name");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Max. No. of Students");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		Tlabel4 = new Label("Starting Date");
		Tlabel4.setMinHeight(40);
		Tlabel4.setMaxWidth(300);
		Tlabel4.setFont(new Font("Sanserif", 14));
		Tlabel5 = new Label("Ending Date");
		Tlabel5.setMinHeight(40);
		Tlabel5.setMaxWidth(200);
		space =new Label();
		space.setMinHeight(10);
		
		batch = new TextField();
		batch.setFont(new Font("Sanserif", 14));
		batch.setMinHeight(40);
		batch.setMaxWidth(400);
		mns= new TextField();
		mns.setFont(new Font("Sanserif", 14));
		mns.setMinHeight(40);
		mns.setMaxWidth(400);
		course = new ComboBox<String>();
		course.setPromptText("Plese select");
		course.setMinHeight(35);
		course.setMaxWidth(400);
		course.setItems(L1);
		Sdate = new DatePicker();
		Sdate.setPromptText("Date");
		Sdate.setMinHeight(40);
		Sdate.setMaxWidth(400);
		Edate = new DatePicker();
		Edate.setPromptText("Date");
		Edate.setMinHeight(40);
		Edate.setMaxWidth(400);
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox2.getChildren().addAll(label,Tlabel1,course,Tlabel2,batch,Tlabel3,mns,Tlabel4,Sdate,Tlabel5,Edate,space,save);
		
		MainHbox.getChildren().addAll(Vbox2,table1);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO Batch(Course,Batch,MNS, StartDate,EndDate)VALUES(?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, (String)course.getSelectionModel().getSelectedItem());
				preparedStatement.setString(2, batch.getText());//(String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(3, mns.getText());
				preparedStatement.setString(4, ((TextField)Sdate.getEditor()).getText());
				preparedStatement.setString(5, ((TextField)Edate.getEditor()).getText());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Batch added successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				fieldClear2();
				refresh2() ;
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	public void fieldClear2() {
		Sdate.setValue(null);
		Edate.setValue(null);
		mns.clear();
		batch.clear();
		course.setValue(null);
	}
	
	//ClassTeacherAllocation**************
	public void refresh3() {
		list2.clear();
		try {
			String query = "select * from classTeacher";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list2.add(new ClassTeacherAllocation(
						resultSet.getString("No"),
						resultSet.getString("Course"),
						resultSet.getString("Batch"),
						resultSet.getString("ClassTeacher")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void classTeacherAllocation() {
		refresh3();
		batchCombo();
		courseCombo();
		teacherCombo();
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(table1);
		MainHbox.getChildren().remove(table2);
		MainHbox.getChildren().remove(table3);
		MainHbox.getChildren().remove(Vbox4);
		MainHbox.getChildren().remove(table4);
		MainHbox.getChildren().remove(Vbox5);
		MainHbox.getChildren().remove(table5);
		MainHbox.getChildren().remove(Vbox6);
		table2 = new TableView<ClassTeacherAllocation>();
		table2.setStyle("-fx-background-radius: 5");
		
		TableColumn<ClassTeacherAllocation, String> c1= new TableColumn<ClassTeacherAllocation, String> ("#");
		c1.setMinWidth(40);
		TableColumn<ClassTeacherAllocation, String> c2= new TableColumn<ClassTeacherAllocation, String> ("Course");
		c2.setMinWidth(150);
		TableColumn<ClassTeacherAllocation, String> c3= new TableColumn<ClassTeacherAllocation, String> ("Batch");
		c3.setMinWidth(100);
		TableColumn<ClassTeacherAllocation, String> c4= new TableColumn<ClassTeacherAllocation, String> ("Class Teacher");
		c4.setMinWidth(150);
		TableColumn c5= new TableColumn("Manage");
		table2.getColumns().addAll(c1,c2,c3,c4,c5);
		c1.setCellValueFactory(new PropertyValueFactory<ClassTeacherAllocation, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<ClassTeacherAllocation, String>("Course"));
		c3.setCellValueFactory(new PropertyValueFactory<ClassTeacherAllocation, String>("batch"));
		c4.setCellValueFactory(new PropertyValueFactory<ClassTeacherAllocation, String>("classTeacher"));
		c5.setCellValueFactory(new PropertyValueFactory<ClassTeacherAllocation, String>("Edit"));
		table2.setItems(list2);
		
		Vbox3 =  new VBox();
		Vbox3.setPrefWidth(607);
		Vbox3.setPrefHeight(650);
		Vbox3.setPadding(new Insets(5,150,10, 25));
		Vbox3.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Class Teacher Allocation");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		Tlabel1 = new Label("Course");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Batch");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Class Teacher");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		course = new ComboBox<String>();
		course.setPromptText("Plese select");
		course.setMinHeight(35);
		course.setMaxWidth(400);
		course.setItems(L1);
		Cbatch = new ComboBox<String>();
		Cbatch.setPromptText("Plese select");
		Cbatch.setMinHeight(35);
		Cbatch.setMaxWidth(400);
		Cbatch.setItems(L2);
		classTeacher = new ComboBox<String>();
		classTeacher.setPromptText("Plese select");
		classTeacher.setMinHeight(35);
		classTeacher.setMaxWidth(400);
		classTeacher.setItems(L3);
		
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox3.getChildren().addAll(label,Tlabel1,course,Tlabel2,Cbatch,Tlabel3,classTeacher,space,save);
		MainHbox.getChildren().addAll(Vbox3,table2);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO classTeacher(Course,Batch,ClassTeacher)VALUES(?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, (String)course.getSelectionModel().getSelectedItem());
				preparedStatement.setString(2, (String)Cbatch.getSelectionModel().getSelectedItem());//(String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(3, (String)classTeacher.getSelectionModel().getSelectedItem());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Class Teacher added successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				fieldClear3();
				refresh3() ;
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	public void fieldClear3() {
		course.setValue(null);
		Cbatch.setValue(null);
		classTeacher.setValue(null);
	}

	//Subjects*************************
	public void refresh4() {
		list3.clear();
		try {
			String query = "select * from subjects";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list3.add(new AddSubject(
						resultSet.getString("No"),
						resultSet.getString("SubjectName"),
						resultSet.getString("SubjectCode"),
						resultSet.getString("Description")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void AddSubject() {
		refresh4();
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(table1);
		MainHbox.getChildren().remove(table2);
		MainHbox.getChildren().remove(table3);
		MainHbox.getChildren().remove(Vbox4);
		MainHbox.getChildren().remove(table4);
		MainHbox.getChildren().remove(Vbox5);
		MainHbox.getChildren().remove(table5);
		MainHbox.getChildren().remove(Vbox6);
		table3 = new TableView<AddSubject>();
		table3.setStyle("-fx-background-radius: 5");
		table3.setPrefWidth(607);
		
		TableColumn<AddSubject, String> c1= new TableColumn<AddSubject, String> ("#");
		c1.setMinWidth(70);
		TableColumn<AddSubject, String> c2= new TableColumn<AddSubject, String> ("Subject Name");
		c2.setMinWidth(200);
		TableColumn<AddSubject, String> c3= new TableColumn<AddSubject, String> ("Subject Code");
		c3.setMinWidth(200);
		
		TableColumn c4= new TableColumn("Manage");
		c4.setMinWidth(70);
		table3.getColumns().addAll(c1,c2,c3,c4);
		c1.setCellValueFactory(new PropertyValueFactory<AddSubject, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<AddSubject, String>("SubjectName"));
		c3.setCellValueFactory(new PropertyValueFactory<AddSubject, String>("SubjectCode"));
		c4.setCellValueFactory(new PropertyValueFactory<AddSubject, String>("Edit"));
		table3.setItems(list3);
		
		Vbox4 =  new VBox();
		Vbox4.setPrefWidth(607);
		Vbox4.setPrefHeight(650);
		Vbox4.setPadding(new Insets(5,150,10, 25));
		Vbox4.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Add Subject");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		Tlabel1 = new Label("Subject Name");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Subject Code");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Description");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		subjectName= new TextField();
		subjectName.setFont(new Font("Sanserif", 14));
		subjectName.setMinHeight(40);
		subjectName.setMaxWidth(400);
		subjectCode= new TextField();
		subjectCode.setFont(new Font("Sanserif", 14));
		subjectCode.setMinHeight(40);
		subjectCode.setMaxWidth(400);
		description1= new TextField();
		description1.setFont(new Font("Sanserif", 14));
		description1.setMinHeight(40);
		description1.setMaxWidth(400);
		
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox4.getChildren().addAll(label,Tlabel1,subjectName,Tlabel2,subjectCode,Tlabel3,description1,space,save);
		MainHbox.getChildren().addAll(Vbox4,table3);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO subjects(SubjectName,SubjectCode,Description)VALUES(?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, subjectName.getText());
				preparedStatement.setString(3, subjectCode.getText());//(String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(2, description1.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Subject added successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				fieldClear4();
				refresh4();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	public void fieldClear4() {
		subjectName.clear();
		subjectCode.clear();
		description1.clear();
	}
	
	//Assign Subject**************
	public void subjectCombo() {
		L4.clear();
		try {
			String query = "select SubjectName from subjects";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				L4.add(resultSet.getString("SubjectName"));
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public void refresh5() {
		list4.clear();
		try {
			String query = "select * from assignSubjects";
			PreparedStatement preparedStatement = connection1.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list4.add(new AssignSubject(
						resultSet.getString("No"),
						resultSet.getString("Course"),
						resultSet.getString("Batch"),
						resultSet.getString("Subject")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void assignsubject() {
		refresh5();
		subjectCombo();
		batchCombo();
		courseCombo();
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(table1);
		MainHbox.getChildren().remove(table2);
		MainHbox.getChildren().remove(table3);
		MainHbox.getChildren().remove(Vbox4);
		MainHbox.getChildren().remove(table4);
		MainHbox.getChildren().remove(Vbox5);
		MainHbox.getChildren().remove(table5);
		MainHbox.getChildren().remove(Vbox6);
		table4 = new TableView<AssignSubject>();
		table4.setStyle("-fx-background-radius: 5");
		table4.setPrefWidth(607);
		
		TableColumn<AssignSubject, String> c1= new TableColumn<AssignSubject, String> ("#");
		c1.setMinWidth(70);
		TableColumn<AssignSubject, String> c2= new TableColumn<AssignSubject, String> ("Course");
		c2.setMinWidth(150);
		TableColumn<AssignSubject, String> c3= new TableColumn<AssignSubject, String> ("Batch");
		c3.setMinWidth(150);
		TableColumn<AssignSubject, String> c4= new TableColumn<AssignSubject, String> ("Subject");
		c4.setMinWidth(150);
		
		TableColumn c5= new TableColumn("Manage");
		c5.setMinWidth(70);
		table4.getColumns().addAll(c1,c2,c3,c4,c5);
		c1.setCellValueFactory(new PropertyValueFactory<AssignSubject, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<AssignSubject, String>("course"));
		c3.setCellValueFactory(new PropertyValueFactory<AssignSubject, String>("batch"));
		c4.setCellValueFactory(new PropertyValueFactory<AssignSubject, String>("subject"));
		c5.setCellValueFactory(new PropertyValueFactory<AssignSubject, String>("Edit"));
		table4.setItems(list4);
		
		Vbox5 =  new VBox();
		Vbox5.setPrefWidth(607);
		Vbox5.setPrefHeight(650);
		Vbox5.setPadding(new Insets(5,150,10, 25));
		Vbox5.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Assign Subject");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		Tlabel1 = new Label("Course");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Batch");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Subject");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		course1 = new ComboBox<String>();
		course1.setPromptText("Plese select");
		course1.setMinHeight(35);
		course1.setMaxWidth(400);
		course1.setItems(L1);
		Cbatch1 = new ComboBox<String>();
		Cbatch1.setPromptText("Plese select");
		Cbatch1.setMinHeight(35);
		Cbatch1.setMaxWidth(400);
		Cbatch1.setItems(L2);
		subject = new ComboBox<String>();
		subject.setPromptText("Plese select");
		subject.setMinHeight(35);
		subject.setMaxWidth(400);
		subject.setItems(L4);
		
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox5.getChildren().addAll(label,Tlabel1,course1,Tlabel2,Cbatch1,Tlabel3,subject,space,save);
		MainHbox.getChildren().addAll(Vbox5,table4);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO assignSubjects(Course,Batch,Subject)VALUES(?,?,?) ";
				PreparedStatement preparedStatement1 = connection1.prepareStatement(query);
				preparedStatement1.setString(1, (String)course1.getSelectionModel().getSelectedItem());
				preparedStatement1.setString(2, (String)Cbatch1.getSelectionModel().getSelectedItem());
				preparedStatement1.setString(3, (String)subject.getSelectionModel().getSelectedItem());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Subject Assign successfully :)");
		
				alert.showAndWait();
				preparedStatement1.execute();
				preparedStatement1.close();
				fieldClear5();
				refresh5();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	
	public void fieldClear5() {
		course1.setValue(null);
		Cbatch1.setValue(null);
		subject.setValue(null);
	}

	//Subject Allocation
	public void refresh6() {
		list5.clear();
		try {
			String query = "select * from SubjectAllocation";
			PreparedStatement preparedStatement = connection1.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list5.add(new subjectAllocation(
						resultSet.getString("No"),
						resultSet.getString("Department"),
						resultSet.getString("TeacherName"),
						resultSet.getString("Course"),
						resultSet.getString("Batch"),
						resultSet.getString("Subject")
						
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void SubjectAllocation() {
		refresh6();
		batchCombo();
		courseCombo();
		teacherCombo();
		subjectCombo();
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(table1);
		MainHbox.getChildren().remove(table2);
		MainHbox.getChildren().remove(table3);
		MainHbox.getChildren().remove(Vbox4);
		MainHbox.getChildren().remove(table4);
		MainHbox.getChildren().remove(Vbox5);
		MainHbox.getChildren().remove(table5);
		MainHbox.getChildren().remove(Vbox6);
		table5 = new TableView<subjectAllocation>();
		table5.setStyle("-fx-background-radius: 5");
		
		TableColumn<subjectAllocation, String> c1= new TableColumn<subjectAllocation, String> ("#");
		c1.setMinWidth(40);
		TableColumn<subjectAllocation, String> c2= new TableColumn<subjectAllocation, String> ("Department");
		c2.setMinWidth(100);
		TableColumn<subjectAllocation, String> c3= new TableColumn<subjectAllocation, String> ("Teacher Name");
		c3.setMinWidth(130);
		TableColumn<subjectAllocation, String> c4= new TableColumn<subjectAllocation, String> ("Course");
		c4.setMinWidth(150);
		TableColumn<subjectAllocation, String> c5= new TableColumn<subjectAllocation, String> ("Batch");
		c5.setMinWidth(60);
		TableColumn<subjectAllocation, String> c6= new TableColumn<subjectAllocation, String> ("Subject");
		c6.setMinWidth(150);
		TableColumn c7= new TableColumn("Manage");
		table5.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7);
		c1.setCellValueFactory(new PropertyValueFactory<subjectAllocation, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<subjectAllocation, String>("department"));
		c3.setCellValueFactory(new PropertyValueFactory<subjectAllocation, String>("teacherName"));
		c4.setCellValueFactory(new PropertyValueFactory<subjectAllocation, String>("course"));
		c5.setCellValueFactory(new PropertyValueFactory<subjectAllocation, String>("batch"));
		c6.setCellValueFactory(new PropertyValueFactory<subjectAllocation, String>("subject"));
		c7.setCellValueFactory(new PropertyValueFactory<subjectAllocation, String>("Edit"));
		table5.setItems(list5);
		
		Vbox6 =  new VBox();
		Vbox6.setPrefWidth(607);
		Vbox6.setPrefHeight(650);
		Vbox6.setPadding(new Insets(5,150,10, 25));
		Vbox6.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Subject Allocation");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		Tlabel1 = new Label("Department");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Teacher Name");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Course");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		Tlabel4 = new Label("Batch");
		Tlabel4.setMinHeight(40);
		Tlabel4.setMaxWidth(200);
		Tlabel4.setFont(new Font("Sanserif", 14));
		Tlabel5 = new Label("Subject");
		Tlabel5.setMinHeight(40);
		Tlabel5.setMaxWidth(200);
		Tlabel5.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		
		department = new ComboBox<String>();
		department.setPromptText("Plese select");
		department.setMinHeight(35);
		department.setMaxWidth(400);
		department.setItems(L5);
		classTeacher = new ComboBox<String>();
		classTeacher.setPromptText("Plese select");
		classTeacher.setMinHeight(35);
		classTeacher.setMaxWidth(400);
		classTeacher.setItems(L3);
		course = new ComboBox<String>();
		course.setPromptText("Plese select");
		course.setMinHeight(35);
		course.setMaxWidth(400);
		course.setItems(L1);
		Cbatch = new ComboBox<String>();
		Cbatch.setPromptText("Plese select");
		Cbatch.setMinHeight(35);
		Cbatch.setMaxWidth(400);
		Cbatch.setItems(L2);
		subject = new ComboBox<String>();
		subject.setPromptText("Plese select");
		subject.setMinHeight(35);
		subject.setMaxWidth(400);
		subject.setItems(L4);
		
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox6.getChildren().addAll(label,Tlabel1,department,Tlabel2,classTeacher,Tlabel3,course,Tlabel4,Cbatch,Tlabel5,subject,space,save);
		MainHbox.getChildren().addAll(Vbox6,table5);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO SubjectAllocation(Department,TeacherName,Course,Batch,Subject)VALUES(?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection1.prepareStatement(query);
				preparedStatement.setString(1, (String)department.getSelectionModel().getSelectedItem());
				preparedStatement.setString(2, (String)classTeacher.getSelectionModel().getSelectedItem());//(String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(3, (String)course.getSelectionModel().getSelectedItem());
				preparedStatement.setString(4, (String)Cbatch.getSelectionModel().getSelectedItem());//(String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(5, (String)subject.getSelectionModel().getSelectedItem());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Subject Allocation successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				fieldClear6();
				refresh6() ;
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	public void fieldClear6() {
		course.setValue(null);
		Cbatch.setValue(null);
		classTeacher.setValue(null);
		department.setValue(null);
		subject.setValue(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		AddCourse();
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
		AdminDC.studentButtonAction(event);
	}
	public void event(ActionEvent event) {
		AdminDC.EventAction(event);
	}
}
