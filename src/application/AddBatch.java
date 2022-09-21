package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class AddBatch {
	
	public AddBatch(String no, String course, String batch,String mNS, String startDate, String endDate) {
		super();
		this.no = new SimpleStringProperty(no);
		Course = new SimpleStringProperty(course);
		this.batch = new SimpleStringProperty(batch);
		MNS = new SimpleStringProperty(mNS);
		StartDate = new SimpleStringProperty(startDate);
		EndDate = new SimpleStringProperty(endDate);
		Edit = new Button("EDIT");
	}
	
	
	public String getNo() {
		return no.get();
	}
	public String getCourse() {
		return Course.get();
	}
	public String getBatch() {
		return batch.get();
	}
	public String getMNS() {
		return MNS.get();
	}
	public String getStartDate() {
		return StartDate.get();
	}
	public String getEndDate() {
		return EndDate.get();
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
	private final SimpleStringProperty Course;
	private final SimpleStringProperty batch;
	private final SimpleStringProperty MNS;
	private final SimpleStringProperty StartDate;
	private final SimpleStringProperty EndDate;
}
