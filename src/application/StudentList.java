package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class StudentList {
	
	public StudentList(String no, String iD, String studentName,String email,String admissionDate, String batch, String attandence,String result) {
		super();
		this.no = new SimpleStringProperty(no);
		ID = new SimpleStringProperty(iD);
		StudentName = new SimpleStringProperty(studentName);
		Email = new SimpleStringProperty(email);
		AdmissionDate = new SimpleStringProperty(admissionDate);
		Batch = new SimpleStringProperty(batch);
		Attandence = new SimpleStringProperty(attandence);
		this.result = new SimpleStringProperty(result);
		Edit = new Button("EDIT");
	}
	
	public String getNo() {
		return no.get();
	}
	public String getID() {
		return ID.get();
	}
	public String getStudentName() {
		return StudentName.get();
	}
	public String getEmail() {
		return Email.get();
	}
	public String getAdmissionDate() {
		return AdmissionDate.get();
	}
	public String getBatch() {
		return Batch.get();
	}
	public String getAttandence() {
		return Attandence.get();
	}
	public String getResult() {
		return result.get();
	}
	public Button getEdit() {
		Edit.setStyle("-fx-background-color: #1a73e8");
		Edit.setTextFill(Color.WHITE);
		Edit.setMinHeight(25);
		Edit.setMaxWidth(50);
		return Edit;
	}
	public void setEdit(Button edit) {
		
		this.Edit = edit;
	}
	
	private Button Edit;
	private final SimpleStringProperty no;
	private final SimpleStringProperty ID;
	private final SimpleStringProperty StudentName;
	private final SimpleStringProperty AdmissionDate;
	private final SimpleStringProperty Batch;
	private final SimpleStringProperty Attandence;
	private final SimpleStringProperty result;
	private final SimpleStringProperty Email;
}
