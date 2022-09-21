package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class bookList {
	
	public bookList(String no, String title, String edition,String publisher, String status, String bookNo,	String bookISBNNo, String author) {
		super();
		this.no = new SimpleStringProperty(no);
		this.title = new SimpleStringProperty(title);
		this.edition = new SimpleStringProperty(edition);
		this.publisher = new SimpleStringProperty(publisher);
		this.status = new SimpleStringProperty(status);
		this.bookNo = new SimpleStringProperty(bookNo);
		this.bookISBNNo = new SimpleStringProperty(bookISBNNo);
		this.author = new SimpleStringProperty(author);
		Edit = new Button("EDIT");
	}
	
	public String getNo() {
		return no.get();
	}
	public String getTitle() {
		return title.get();
	}
	public String getEdition() {
		return edition.get();
	}
	public String getPublisher() {
		return publisher.get();
	}
	public String getStatus() {
		return status.get();
	}
	public String getBookNo() {
		return bookNo.get();
	}
	public String getBookISBNNo() {
		return bookISBNNo.get();
	}
	public String getAuthor() {
		return author.get();
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
	private final SimpleStringProperty title;
	private final SimpleStringProperty edition;
	private final SimpleStringProperty publisher;
	private final SimpleStringProperty status;
	private final SimpleStringProperty bookNo;
	private final SimpleStringProperty bookISBNNo;
	private final SimpleStringProperty author;
}
