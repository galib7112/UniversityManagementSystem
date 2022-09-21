package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class task_manager {
	public String getNo() {
		return no.get();
	}
	public String getTaskD() {
		return TaskD.get();
	}
	public String getPri() {
		return Pri.get();
	}
	public String getAssignBy() {
		return AssignBy.get();
	}
	public String getTaskdate() {
		return Taskdate.get();
	}
	public String getStatus() {
		return Status.get();
	}
	public task_manager(String no, String taskD, String pri, String AB, String taskdate, String status) {
		super();
		this.no = new SimpleStringProperty(no);
		TaskD = new SimpleStringProperty(taskD);
		Pri = new SimpleStringProperty(pri);
		AssignBy = new SimpleStringProperty(AB);
		Taskdate = new SimpleStringProperty(taskdate);
		Status = new SimpleStringProperty(status);
		Edit = new Button("EDIT");
	}
	private final SimpleStringProperty no;
	private final SimpleStringProperty TaskD;
	private final SimpleStringProperty Pri;
	private final SimpleStringProperty AssignBy;
	private final SimpleStringProperty Taskdate;
	private final SimpleStringProperty Status;
	private Button Edit;
	public Button getEdit() {
		return Edit;
	}
	public void setEdit(Button edit) {
		this.Edit = edit;
	}

}
