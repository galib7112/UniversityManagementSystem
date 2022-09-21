package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class subjectAllocation {
	
	public subjectAllocation(String no, String department, String teacherName,String course, String batch,String subject) {
		super();
		this.no = new SimpleStringProperty(no);
		this.course = new SimpleStringProperty(course);
		this.batch = new SimpleStringProperty(batch);
		this.subject = new SimpleStringProperty(subject);
		this.department = new SimpleStringProperty(department);
		this.teacherName = new SimpleStringProperty(teacherName);
		Edit = new Button("EDIT");
	}
	public Button getEdit() {
		Edit.setStyle("-fx-background-color: #1a73e8");
		Edit.setTextFill(Color.WHITE);
		Edit.setMinHeight(25);
		Edit.setMaxWidth(50);
		return Edit;
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
	public String getDepartment() {
		return department.get();
	}
	public String getTeacherName() {
		return teacherName.get();
	}
	public void setEdit(Button edit) {
		this.Edit = edit;
	}
	
	private Button Edit;
	private final SimpleStringProperty no;
	private final SimpleStringProperty course;
	private final SimpleStringProperty batch;
	private final SimpleStringProperty subject;
	private final SimpleStringProperty department;
	private final SimpleStringProperty teacherName;
}
