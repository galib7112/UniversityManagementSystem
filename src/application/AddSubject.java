package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class AddSubject {
	
	public AddSubject(String no, String subjectName,String description, String subjectCode) {
		super();
		this.no = new SimpleStringProperty(no);
		SubjectName = new SimpleStringProperty(subjectName);
		Description = new SimpleStringProperty(description);
		SubjectCode = new SimpleStringProperty(subjectCode);
		Edit = new Button("EDIT");
	}
	public String getNo() {
		return no.get();
	}
	public String getSubjectName() {
		return SubjectName.get();
	}
	public String getSubjectCode() {
		return SubjectCode.get();
	}
	public String getDescription() {
		return Description.get();
	}
	private Button Edit;
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
	private final SimpleStringProperty no;
	private final SimpleStringProperty SubjectName;
	private final SimpleStringProperty Description;
	private final SimpleStringProperty SubjectCode;
}
