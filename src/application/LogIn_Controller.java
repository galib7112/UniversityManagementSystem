package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LogIn_Controller implements Initializable{
	public LogIn_Model logInModel = new LogIn_Model();
	@FXML
	private Label labelStatus;
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField textPassword;
	public void login(ActionEvent event) {
		try {
			if(logInModel.isLogin(userName.getText(), textPassword.getText())) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("Admin_dashboard.fxml"));
					Scene scene = new Scene(root);
					
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage primaryStage =  new Stage();
					primaryStage.setScene(scene);
					/*primaryStage.setTitle("University Managament System");
					primaryStage.getIcons().add(new Image("file:AppIcon.png"));*/
					primaryStage.show();
					((Node)event.getSource()).getScene().getWindow().hide();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			else
				labelStatus.setText("Wrong user name or password");
		} catch (SQLException e) {
			labelStatus.setText("Wrong user name or password");
		}
	}
	public void forgotPass(ActionEvent event) {
		try {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Plese Contact with the admin panal (:");
	
			alert.showAndWait();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
