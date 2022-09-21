package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class event {
	
	public event(String no, String eventName, String eventType,String description, String startDate, String endDate, String organizer) {
		super();
		this.no = new SimpleStringProperty(no);
		this.eventName = new SimpleStringProperty(eventName);
		this.eventType = new SimpleStringProperty(eventType);
		this.description = new SimpleStringProperty(description);
		this.startDate = new SimpleStringProperty(startDate);
		this.endDate = new SimpleStringProperty(endDate);
		this.organizer = new SimpleStringProperty(organizer);
		Edit = new Button("EDIT");
	}
	
	
	public String getNo() {
		return no.get();
	}
	public String getEventName() {
		return eventName.get();
	}
	public String getEventType() {
		return eventType.get();
	}
	public String getDescription() {
		return description.get();
	}
	public String getStartDate() {
		return startDate.get();
	}
	public String getEndDate() {
		return endDate.get();
	}
	public String getOrganizer() {
		return organizer.get();
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
	private final SimpleStringProperty eventName;
	private final SimpleStringProperty eventType;
	private final SimpleStringProperty description;
	private final SimpleStringProperty startDate;
	private final SimpleStringProperty endDate;
	private final SimpleStringProperty organizer;
}
