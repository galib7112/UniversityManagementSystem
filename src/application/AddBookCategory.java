package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class AddBookCategory {
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
	public AddBookCategory(String no, String catagoryName,
			String sectionCode) {
		super();
		this.no =new  SimpleStringProperty(no);
		CatagoryName = new  SimpleStringProperty(catagoryName);
		SectionCode = new  SimpleStringProperty(sectionCode);
		Edit = new Button("EDIT");
	}
	private final SimpleStringProperty CatagoryName;
	
	public String getNo() {
		return no.get();
	}
	public String getCatagoryName() {
		return CatagoryName.get();
	}
	public String getSectionCode() {
		return SectionCode.get();
	}
	private final SimpleStringProperty SectionCode;
}
