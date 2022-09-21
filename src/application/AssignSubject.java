package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class AssignSubject {
	public AssignSubject(String no, String course, String batch,String subject) {
		super();
		this.no = new SimpleStringProperty(no);
		this.course = new SimpleStringProperty(course);
		this.batch = new SimpleStringProperty(batch);
		this.subject = new SimpleStringProperty(subject);
		Edit = new Button("EDIT");
	}
	
	public String getNo() {
		return no.get();
	}
	public String getCourse() {
		return course.get();
	}
	public String getBatch() {
		return batch.get();
	}
	public String getSubject() {
		return subject.get();
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
	private final SimpleStringProperty course;
	private final SimpleStringProperty batch;
	private final SimpleStringProperty subject;
}