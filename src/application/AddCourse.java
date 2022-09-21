package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class AddCourse {
	
	public AddCourse(String no, String courseName, String description,String code, String mAP, String tWD, String aT,String syllabusName) {
		super();
		this.no = new SimpleStringProperty(no);
		CourseName = new SimpleStringProperty(courseName);
		Description = new SimpleStringProperty(description);
		Code = new SimpleStringProperty(code);
		MAP = new SimpleStringProperty(mAP);
		TWD = new SimpleStringProperty(tWD);
		AT = new SimpleStringProperty(aT);
		Edit = new Button("EDIT");
		this.syllabusName = new SimpleStringProperty(syllabusName);
	}
	
	public String getNo() {
		return no.get();
	}
	public String getCourseName() {
		return CourseName.get();
	}
	public String getDescription() {
		return Description.get();
	}
	public String getCode() {
		return Code.get();
	}
	public String getMAP() {
		return MAP.get();
	}
	public String getTWD() {
		return TWD.get();
	}
	public String getAT() {
		return AT.get();
	}
	public String getSyllabusName() {
		return syllabusName.get();
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
	private final SimpleStringProperty CourseName;
	private final SimpleStringProperty Description;
	private final SimpleStringProperty Code;
	private final SimpleStringProperty MAP;
	private final SimpleStringProperty TWD;
	private final SimpleStringProperty AT;
	private final SimpleStringProperty syllabusName;
}
