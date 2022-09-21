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
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class eventController extends Admin_dashboard_controller implements Initializable{
	Connection connection,connection1;
	public eventController(){
	 	connection = SQlite5.E_Connector();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
	}
	@FXML
	private HBox MainHbox;
	TableView<addEventType> table;
	TableView<event> table1;
	VBox Vbox1;
	Label label,Tlabel1,space,space1,Tlabel2,Tlabel3,Tlabel4,Tlabel5,Tlabel6;
	Separator sp;
	Button save;
	TextField eventName,description,organizer;
	DatePicker startDate,endDate;
	ComboBox<String> eventType;
	public ObservableList<addEventType> list =  FXCollections.observableArrayList();
	public ObservableList<event> list1 =  FXCollections.observableArrayList();
	//public ObservableList<event> list2 =  FXCollections.observableArrayList();
	public ObservableList<String> L =  FXCollections.observableArrayList();
	public void refresh1() {
		list.clear();
		try {
			String query = "select * from eventType";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list.add(new addEventType(
						resultSet.getString("No"),
						resultSet.getString("TypeName")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void addEventType() {
		refresh1() ;
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(table1);
		table = new TableView<addEventType>();
		table.setStyle("-fx-background-radius: 5");
		
		TableColumn<addEventType, String> c1= new TableColumn<addEventType, String> ("#");
		c1.setMinWidth(100);
		TableColumn<addEventType, String> c2= new TableColumn<addEventType, String> ("Event Type");
		c2.setMinWidth(300);
		TableColumn c3= new TableColumn("Manage");
		table.getColumns().addAll(c1,c2,c3);
		c1.setCellValueFactory(new PropertyValueFactory<addEventType, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<addEventType, String>("event"));
		c3.setCellValueFactory(new PropertyValueFactory<addEventType, String>("Edit"));
		table.setItems(list);
		MainHbox.setStyle("-fx-spacing: 25;-fx-pref-height: 670");
		
		
		Vbox1 =  new VBox();
		Vbox1.setPrefWidth(607);
		Vbox1.setPrefHeight(650);
		Vbox1.setPadding(new Insets(5,150,10, 25));
		Vbox1.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Add Event Type");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		sp = new Separator();
		sp.setMaxWidth(400);
		Tlabel1 = new Label("Type Name");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		space1 =new Label();
		space1.setMinHeight(10);
		
		eventName = new TextField();
		eventName.setFont(new Font("Sanserif", 14));
		eventName.setMinHeight(40);
		eventName.setMaxWidth(400);
		
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox1.getChildren().addAll(label,sp,space1,Tlabel1,eventName,space,save);
		MainHbox.getChildren().addAll(Vbox1,table);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO eventType(TypeName)VALUES(?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, eventName.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Event Type added successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				fieldClear1();
				refresh1() ;
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	public void AddEvent() {
		eventTypecombo() ;
		 refresh2() ;
		 MainHbox.getChildren().remove(table);
		 MainHbox.getChildren().remove(Vbox1);
		 MainHbox.getChildren().remove(table1);
		table1 = new TableView<event>();
		table1.setStyle("-fx-background-radius: 5");
		refresh1();
		TableColumn<event, String> c1= new TableColumn<event, String> ("#");
		c1.setMinWidth(40);
		TableColumn<event, String> c2= new TableColumn<event, String> ("Event Name");
		c2.setMinWidth(125);
		TableColumn<event, String> c3= new TableColumn<event, String> ("Event Type");
		c3.setMinWidth(100);
		TableColumn<event, String> c4= new TableColumn<event, String> ("Start Date");
		c4.setMinWidth(100);
		TableColumn<event, String> c5= new TableColumn<event, String> ("End Date");
		c5.setMinWidth(100);
		TableColumn<event, String> c6= new TableColumn<event, String> ("Organizer/Incharge");
		c6.setMinWidth(125);
		TableColumn c7= new TableColumn("Manage");
		table1.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7);
		c1.setCellValueFactory(new PropertyValueFactory<event, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<event, String>("eventName"));
		c3.setCellValueFactory(new PropertyValueFactory<event, String>("eventType"));
		c4.setCellValueFactory(new PropertyValueFactory<event, String>("startDate"));
		c5.setCellValueFactory(new PropertyValueFactory<event, String>("endDate"));
		c6.setCellValueFactory(new PropertyValueFactory<event, String>("organizer"));
		c7.setCellValueFactory(new PropertyValueFactory<event, String>("Edit"));
		table1.setItems(list1);
		
		Vbox1 =  new VBox();
		Vbox1.setPrefWidth(607);
		Vbox1.setPrefHeight(650);
		Vbox1.setPadding(new Insets(5,150,10, 25));
		Vbox1.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Add Course");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		Tlabel1 = new Label("Event Name");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Event Type");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Description");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		Tlabel4 = new Label("Star Date");
		Tlabel4.setMinHeight(40);
		Tlabel4.setMaxWidth(300);
		Tlabel4.setFont(new Font("Sanserif", 14));
		Tlabel5 = new Label("End Date");
		Tlabel5.setMinHeight(40);
		Tlabel5.setMaxWidth(200);
		Tlabel5.setFont(new Font("Sanserif", 14));
		Tlabel6 = new Label("Organizer/Incharge Name");
		Tlabel6.setMinHeight(40);
		Tlabel6.setMaxWidth(200);
		Tlabel6.setFont(new Font("Sanserif", 14));
		
		space =new Label();
		space.setMinHeight(10);
		
		eventName = new TextField();
		eventName.setFont(new Font("Sanserif", 14));
		eventName.setMinHeight(40);
		eventName.setMaxWidth(400);
		eventType = new ComboBox<String>();
		eventType.setPromptText("Plese select");
		eventType.setMinHeight(35);
		eventType.setMaxWidth(400);
		eventType.setItems(L);
		
		description= new TextField();
		description.setFont(new Font("Sanserif", 14));
		description.setMinHeight(40);
		description.setMaxWidth(400);
		startDate = new DatePicker();
		startDate.setPromptText("Date Of Purchase");
		startDate.setMinHeight(40);
		startDate.setMaxWidth(400);
		endDate = new DatePicker();
		endDate.setPromptText("Date Of Purchase");
		endDate.setMinHeight(40);
		endDate.setMaxWidth(400);
		organizer= new TextField();
		organizer.setFont(new Font("Sanserif", 14));
		organizer.setMinHeight(40);
		organizer.setMaxWidth(400);
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox1.getChildren().addAll(label,Tlabel1,eventName,Tlabel2,eventType,Tlabel3,description,Tlabel4,startDate,Tlabel5,endDate,Tlabel6,organizer,space,save);
		MainHbox.getChildren().addAll(Vbox1,table1);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO event(EventName,EventType,Description,StartDate,EndDate,Organizer)VALUES(?,?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, eventName.getText());
				preparedStatement.setString(3, description.getText());//(String)pri.getSelectionModel().getSelectedItem());
				preparedStatement.setString(2, (String)eventType.getSelectionModel().getSelectedItem());
				preparedStatement.setString(4, ((TextField)startDate.getEditor()).getText());
				preparedStatement.setString(5, ((TextField)endDate.getEditor()).getText());
				preparedStatement.setString(6, organizer.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Course added successfully :)");
		
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
	public void refresh2() {
		list1.clear();
		try {
			String query = "select * from event";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list1.add(new event(
						resultSet.getString("No"),
						resultSet.getString("EventName"),
						resultSet.getString("Description"),
						resultSet.getString("EventType"),
						resultSet.getString("StartDate"),
						resultSet.getString("EndDate"),
						resultSet.getString("Organizer")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void fieldClear2(){
		eventName.clear();
		organizer.clear();
		description.clear();
		eventType.setValue(null);
		startDate.setValue(null);
		endDate.setValue(null);
	}
	public void eventTypecombo() {
		L.clear();
		try {
			String query = "select TypeName from eventType";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				L.add(resultSet.getString("TypeName"));
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public void fieldClear1() {
		eventName.clear();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addEventType();
		
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
	public void library(ActionEvent event) {
		AdminDC.libraryButtonAction(event);
	}
}
