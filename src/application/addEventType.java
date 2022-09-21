package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class addEventType {
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
	private final SimpleStringProperty eventType;
	public String getNo() {
		return no.get();
	}
	public String getEvent() {
		return eventType.get();
	}
	public addEventType(String no, String event) {
		super();
		this.no = new SimpleStringProperty(no);
		this.eventType = new SimpleStringProperty(event);
		Edit = new Button("EDIT");
	}
}
