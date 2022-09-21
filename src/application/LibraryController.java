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

public class LibraryController extends Admin_dashboard_controller implements Initializable{
	Connection connection,connection1;
	public LibraryController(){
	 	connection = SQlite4.Li_Connector();
		if(connection==null) {
			if(connection==null)
				System.exit(1);
		}
	}
	@FXML
	private HBox MainHbox;
	@FXML
	private VBox mainVbox;
	TableView<AddBookCategory> table;
	TableView<bookList> table1;
	public ObservableList<AddBookCategory> list =  FXCollections.observableArrayList();
	Separator sp;
	VBox Vbox1,Vbox2,Vbox3;
	Label label,Tlabel1,Tlabel2,Tlabel3,Tlabel4,Tlabel5,Tlabel6,Tlabel7,Tlabel8,Tlabel9,Tlabel10,Tlabel11,Tlabel12,Tlabel13,Tlabel14,Tlabel15,space,space1,titallabel;
	TextField CategoryName1,sectionCode1,title,edition,publisher,author,selfNo,billNo,bookNo,bookCost,noOfCopies,language,bookPosition,bookISBNNo;
	DatePicker purchaseDate;
	ComboBox< String> bookCategore,bookCondition;
	public ObservableList<bookList> list1 =  FXCollections.observableArrayList();
	
	public ObservableList<String> L1 =  FXCollections.observableArrayList();
	public ObservableList<String> L2 =  FXCollections.observableArrayList("Avalable","unavalable");
	Button save;
	public void refresh1(){
		list.clear();
		try {
			String query = "select * from addBookCategory";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list.add(new AddBookCategory(
						resultSet.getString("No"),
						resultSet.getString("CategoryName"),
						resultSet.getString("SectionCode")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void addBookCategory() {
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		mainVbox.getChildren().remove(MainHbox);
		MainHbox.getChildren().remove(table1);
		mainVbox.getChildren().remove(titallabel);
		table = new TableView<AddBookCategory>();
		MainHbox.setStyle("-fx-spacing: 15;-fx-pref-height: 670");
		table.setStyle("-fx-background-radius: 5");
		refresh1();
		TableColumn<AddBookCategory, String> c1= new TableColumn<AddBookCategory, String> ("#");
		c1.setMinWidth(50);
		TableColumn<AddBookCategory, String> c2= new TableColumn<AddBookCategory, String> ("Category Name");
		c2.setMinWidth(225);
		TableColumn<AddBookCategory, String> c3= new TableColumn<AddBookCategory, String> ("Section Code");
		c3.setMinWidth(225);
		TableColumn c4= new TableColumn("Manage");
		c4.setMinWidth(75);
		table.getColumns().addAll(c1,c2,c3,c4);
		c1.setCellValueFactory(new PropertyValueFactory<AddBookCategory, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<AddBookCategory, String>("CatagoryName"));
		c3.setCellValueFactory(new PropertyValueFactory<AddBookCategory, String>("SectionCode"));
		c4.setCellValueFactory(new PropertyValueFactory<AddBookCategory, String>("Edit"));
		table.setItems(list);
		
		Vbox1 =  new VBox();
		Vbox1.setPrefWidth(607);
		Vbox1.setPrefHeight(650);
		Vbox1.setPadding(new Insets(5,150,10, 25));
		Vbox1.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		label = new Label("Add Book Category");
		label.setMinHeight(40);
		label.setMinWidth(200);
		label.setFont(new Font("Sanserif", 18));
		sp = new Separator();
		sp.setMaxWidth(400);
		Tlabel1 = new Label("Category Name");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMinWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Section Code");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		space1 =new Label();
		space1.setMinHeight(10);
		
		CategoryName1 = new TextField();
		CategoryName1.setFont(new Font("Sanserif", 14));
		CategoryName1.setMinHeight(40);
		CategoryName1.setMaxWidth(400);
		sectionCode1= new TextField();
		sectionCode1.setFont(new Font("Sanserif", 14));
		sectionCode1.setMinHeight(40);
		sectionCode1.setMaxWidth(400);
	
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(35);
		save.setMaxWidth(80);
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox1.getChildren().addAll(label,sp,space,Tlabel1,CategoryName1,Tlabel2,sectionCode1,space1,save);
		MainHbox.getChildren().addAll(Vbox1,table);
		mainVbox.getChildren().addAll(MainHbox);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO addBookCategory(CategoryName,SectionCode)VALUES(?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, CategoryName1.getText());
				preparedStatement.setString(2, sectionCode1.getText());//(String)pri.getSelectionModel().getSelectedItem());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Book Category added successfully :)");
		
				alert.showAndWait();
				preparedStatement.execute();
				preparedStatement.close();
				//fieldClear1();
				refresh1() ;
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		});
	}
	
	
	
	public void bookCategore() {
		L1.clear();
		try {
			String query = "select CategoryName from addBookCategory";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				L1.add(resultSet.getString("CategoryName"));
			}
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public void AddBook() {
		bookCategore() ;
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(table);
		mainVbox.getChildren().remove(MainHbox);
		MainHbox.getChildren().remove(table1);
		mainVbox.getChildren().remove(titallabel);
		titallabel= new Label("Add Book");
		titallabel.setMinHeight(40);
		titallabel.setMaxWidth(1230);
		titallabel.setFont(new Font("Sanserif", 18));
		titallabel.setPadding(new Insets(0,0,0, 25));
		titallabel.setStyle("-fx-background-color: White; -fx-background-radius: 5;-fx-translate-x: 25");
		Vbox1 =  new VBox();
		Vbox1.setPrefWidth(410);
		Vbox1.setPrefHeight(610);
		Vbox1.setPadding(new Insets(25,100,20, 25));
		Vbox1.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		Vbox2 =  new VBox();
		Vbox2.setPrefWidth(410);
		Vbox2.setPrefHeight(610);
		Vbox2.setPadding(new Insets(25,100,20, 25));
		Vbox2.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		Vbox3 =  new VBox();
		Vbox3.setPrefWidth(410);
		Vbox3.setPrefHeight(610);
		Vbox3.setPadding(new Insets(25,100,20, 25));
		Vbox3.setStyle("-fx-background-color: White; -fx-background-radius: 5");
		
		Tlabel1 = new Label("Title");
		Tlabel1.setMinHeight(40);
		Tlabel1.setMaxWidth(200);
		Tlabel1.setFont(new Font("Sanserif", 14));
		Tlabel2 = new Label("Edition");
		Tlabel2.setMinHeight(40);
		Tlabel2.setMaxWidth(200);
		Tlabel2.setFont(new Font("Sanserif", 14));
		Tlabel3 = new Label("Publisher");
		Tlabel3.setMinHeight(40);
		Tlabel3.setMaxWidth(200);
		Tlabel3.setFont(new Font("Sanserif", 14));
		Tlabel4 = new Label("Author");
		Tlabel4.setMinHeight(40);
		Tlabel4.setMaxWidth(200);
		Tlabel4.setFont(new Font("Sanserif", 14));
		Tlabel5 = new Label("Purchase Date");
		Tlabel5.setMinHeight(40);
		Tlabel5.setMaxWidth(200);
		Tlabel5.setFont(new Font("Sanserif", 14));
		Tlabel6 = new Label("Self No");
		Tlabel6.setMinHeight(40);
		Tlabel6.setMaxWidth(200);
		Tlabel6.setFont(new Font("Sanserif", 14));
		Tlabel7 = new Label("Bill No");
		Tlabel7.setMinHeight(40);
		Tlabel7.setMaxWidth(200);
		Tlabel7.setFont(new Font("Sanserif", 14));
		Tlabel8 = new Label("Book No");
		Tlabel8.setMinHeight(40);
		Tlabel8.setMaxWidth(200);
		Tlabel8.setFont(new Font("Sanserif", 14));
		Tlabel9 = new Label("Book Categore");
		Tlabel9.setMinHeight(40);
		Tlabel9.setMaxWidth(200);
		Tlabel9.setFont(new Font("Sanserif", 14));
		Tlabel10 = new Label("Book Cost");
		Tlabel10.setMinHeight(40);
		Tlabel10.setMaxWidth(200);
		Tlabel10.setFont(new Font("Sanserif", 14));
		Tlabel11 = new Label("No. of Copies ");
		Tlabel11.setMinHeight(40);
		Tlabel11.setMaxWidth(200);
		Tlabel11.setFont(new Font("Sanserif", 14));
		Tlabel12 = new Label("Language");
		Tlabel12.setMinHeight(40);
		Tlabel12.setMaxWidth(200);
		Tlabel12.setFont(new Font("Sanserif", 14));
		Tlabel13 = new Label("Book Position");
		Tlabel13.setMinHeight(40);
		Tlabel13.setMaxWidth(200);
		Tlabel13.setFont(new Font("Sanserif", 14));
		Tlabel14 = new Label("Book ISBN No.");
		Tlabel14.setMinHeight(40);
		Tlabel14.setMaxWidth(200);
		Tlabel14.setFont(new Font("Sanserif", 14));
		Tlabel15 = new Label("Book Condition");
		Tlabel15.setMinHeight(40);
		Tlabel15.setMaxWidth(200);
		Tlabel15.setFont(new Font("Sanserif", 14));
		space =new Label();
		space.setMinHeight(10);
		
		title = new TextField();
		title.setFont(new Font("Sanserif", 14));
		title.setMinHeight(40);
		title.setMaxWidth(400);
		edition= new TextField();
		edition.setFont(new Font("Sanserif", 14));
		edition.setMinHeight(40);
		edition.setMaxWidth(400);
		publisher = new TextField();
		publisher.setFont(new Font("Sanserif", 14));
		publisher.setMinHeight(40);
		publisher.setMaxWidth(400);
		author= new TextField();
		author.setFont(new Font("Sanserif", 14));
		author.setMinHeight(40);
		author.setMaxWidth(400);
		purchaseDate = new DatePicker();
		purchaseDate.setPromptText("Date Of Purchase");
		purchaseDate.setMinHeight(40);
		purchaseDate.setMaxWidth(400);
		selfNo = new TextField();
		selfNo.setFont(new Font("Sanserif", 14));
		selfNo.setMinHeight(40);
		selfNo.setMaxWidth(400);
		billNo= new TextField();
		billNo.setFont(new Font("Sanserif", 14));
		billNo.setMinHeight(40);
		billNo.setMaxWidth(400);
		bookNo= new TextField();
		bookNo.setFont(new Font("Sanserif", 14));
		bookNo.setMinHeight(40);
		bookNo.setMaxWidth(400);
		bookCategore = new ComboBox<String>();
		bookCategore.setPromptText("Plese select");
		bookCategore.setMinHeight(40);
		bookCategore.setMaxWidth(400);
		bookCategore.setItems(L1);
		bookCost = new TextField();
		bookCost.setFont(new Font("Sanserif", 14));
		bookCost.setMinHeight(40);
		bookCost.setMaxWidth(400);
		noOfCopies= new TextField();
		noOfCopies.setFont(new Font("Sanserif", 14));
		noOfCopies.setMinHeight(40);
		noOfCopies.setMaxWidth(400);
		language = new TextField();
		language.setFont(new Font("Sanserif", 14));
		language.setMinHeight(40);
		language.setMaxWidth(400);
		bookPosition= new TextField();
		bookPosition.setFont(new Font("Sanserif", 14));
		bookPosition.setMinHeight(40);
		bookPosition.setMaxWidth(400);
		bookISBNNo= new TextField();
		bookISBNNo.setFont(new Font("Sanserif", 14));
		bookISBNNo.setMinHeight(40);
		bookISBNNo.setMaxWidth(400);
		bookCondition = new ComboBox<String>();
		bookCondition.setPromptText("Plese select");
		bookCondition.setMinHeight(40);
		bookCondition.setMaxWidth(400);
		bookCondition.setItems(L2);
		save = new Button("Save");
		save.setFont(Font.font("SanSerif",15));
		save.setTextFill(Color.WHITE);
		save.setMinHeight(40);
		save.setMaxWidth(100); 
		save.setStyle("-fx-background-color: #1a73e8");
		Vbox1.getChildren().addAll(Tlabel1,title,Tlabel2,edition,Tlabel3,publisher,Tlabel4,author,Tlabel5,purchaseDate,space,save);
		Vbox2.getChildren().addAll(Tlabel6,selfNo,Tlabel7,billNo,Tlabel8,bookNo,Tlabel9,bookCategore,Tlabel10,bookCost);
		Vbox3.getChildren().addAll(Tlabel11,noOfCopies,Tlabel12,language,Tlabel13,bookPosition,Tlabel14,bookISBNNo,Tlabel15,bookCondition);
		
		MainHbox.setStyle("-fx-spacing: 0;-fx-pref-height: 630");
		MainHbox.getChildren().addAll(Vbox1,Vbox2,Vbox3);
		mainVbox.getChildren().addAll(titallabel,MainHbox);
		save.setOnAction(e->{
			try {
				String query = "INSERT INTO BookList(Title,Edition,Publisher,Author, PurchaseDate,SelfNo,BillNo,BookNo,BookCategore,BookCost,NoOfCopies,Language,BookPosition,BookISBNNo,BookCondition)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, title.getText());
				preparedStatement.setString(2, edition.getText());
				preparedStatement.setString(3, publisher.getText());
				preparedStatement.setString(4, author.getText());
				preparedStatement.setString(5, ((TextField)purchaseDate.getEditor()).getText());
				preparedStatement.setString(6, selfNo.getText());
				preparedStatement.setString(7, billNo.getText());
				preparedStatement.setString(8, bookNo.getText());
				
				preparedStatement.setString(9, (String)bookCategore.getSelectionModel().getSelectedItem());
				preparedStatement.setString(10, bookCost.getText());
				preparedStatement.setString(11, noOfCopies.getText());
				preparedStatement.setString(12, language.getText());
				preparedStatement.setString(13, bookPosition.getText());
				preparedStatement.setString(14, bookISBNNo.getText());
				preparedStatement.setString(15, (String)bookCondition.getSelectionModel().getSelectedItem());
				
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
	public void fieldClear(){
		title.clear();
		edition.clear();
		publisher.clear();
		author.clear();
		purchaseDate.setValue(null);
		selfNo.clear();
		billNo.clear();
		bookNo.clear();
		bookCategore.setValue(null);
		bookCondition.setValue(null);
		bookCost.clear();
		noOfCopies.clear();
		language.clear();
		bookPosition.clear();
		bookISBNNo.clear();
	}
	
	public void bookList() {
		MainHbox.getChildren().remove(Vbox1);
		MainHbox.getChildren().remove(Vbox2);
		MainHbox.getChildren().remove(Vbox3);
		MainHbox.getChildren().remove(table);
		MainHbox.getChildren().remove(table1);
		mainVbox.getChildren().remove(titallabel);
		mainVbox.getChildren().remove(MainHbox);
		titallabel= new Label("Book List");
		titallabel.setMinHeight(40);
		titallabel.setMaxWidth(1230);
		titallabel.setFont(new Font("Sanserif", 18));
		titallabel.setPadding(new Insets(0,0,0, 25));
		titallabel.setStyle("-fx-background-color: White; -fx-background-radius: 5;-fx-translate-x: 25");
		table1 = new TableView<bookList>();
		table1.setPrefWidth(1230);
		table1.setPrefHeight(610);
		list1.clear();
		MainHbox.setStyle("-fx-spacing: 0;-fx-pref-height: 630");
		mainVbox.getChildren().addAll(titallabel,MainHbox);
		try {
			String query = "select * from BookList";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				list1.add(new bookList(
						resultSet.getString("No"),
						resultSet.getString("Title"),
						resultSet.getString("Edition"),
						resultSet.getString("Publisher"),
						resultSet.getString("BookCondition"),
						resultSet.getString("BookNo"),
						resultSet.getString("BookISBNNo"),
						resultSet.getString("Author")
						));
			}
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		TableColumn<bookList, String> c1= new TableColumn<bookList, String> ("#");
		c1.setMinWidth(100);
		TableColumn<bookList, String> c2= new TableColumn<bookList, String> ("Book ISBN No");
		c2.setMinWidth(200);
		TableColumn<bookList, String> c3= new TableColumn<bookList, String> ("Book No");
		c3.setMinWidth(150);
		TableColumn<bookList, String> c4= new TableColumn<bookList, String> ("Title");
		c4.setMinWidth(200);
		TableColumn<bookList, String> c5= new TableColumn<bookList, String> ("Author");
		c5.setMinWidth(150);
		TableColumn<bookList, String> c6= new TableColumn<bookList, String> ("Publisher");
		c6.setMinWidth(100);
		TableColumn<bookList, String> c7= new TableColumn<bookList, String> ("Edition");
		c7.setMinWidth(150);
		TableColumn<bookList, String> c8= new TableColumn<bookList, String> ("Status");
		c8.setMinWidth(100);
		TableColumn c9= new TableColumn("Manage");
		table1.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9);
		c1.setCellValueFactory(new PropertyValueFactory<bookList, String>("no"));
		c2.setCellValueFactory(new PropertyValueFactory<bookList, String>("bookISBNNo"));
		c3.setCellValueFactory(new PropertyValueFactory<bookList, String>("bookNo"));
		c4.setCellValueFactory(new PropertyValueFactory<bookList, String>("title"));
		c5.setCellValueFactory(new PropertyValueFactory<bookList, String>("author"));
		c6.setCellValueFactory(new PropertyValueFactory<bookList, String>("publisher"));
		c7.setCellValueFactory(new PropertyValueFactory<bookList, String>("edition"));
		c8.setCellValueFactory(new PropertyValueFactory<bookList, String>("status"));
		c9.setCellValueFactory(new PropertyValueFactory<bookList, String>("Edit"));
		table1.setItems(list1);
		MainHbox.getChildren().addAll(table1);
	}
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		addBookCategory();
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
	public void TaskListAction(ActionEvent event) {
		shedule();	
	}
	public void student(ActionEvent event) {
		AdminDC.studentButtonAction(event);
	}
	public void library(ActionEvent event) {
		AdminDC.libraryButtonAction(event);
	}
	public void event(ActionEvent event) {
		AdminDC.EventAction(event);
	}
}
