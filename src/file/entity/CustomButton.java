package file.entity;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomButton {

	private StringProperty id;
	private StringProperty value;

	public CustomButton() {
		this(null, null);
	}

	public CustomButton(String id, String value) {
		this.id = new SimpleStringProperty(id);
		this.value = new SimpleStringProperty(value);
	}

	@XmlElement(name = "id")
	public String getId() {
		return id.get();
	}

	@XmlElement(name = "value")
	public String getValue() {
		return value.get();
	}

	public StringProperty idProperty() {
		return id;
	}

	public StringProperty valueProperty() {
		return value;
	}

	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}

	public void setValue(String value) {
		this.value = new SimpleStringProperty(value);
	}

	//	private StringProperty customButton_1;
	//	private StringProperty customButton_2;
	//	private StringProperty customButton_3;
	//
	//	public CustomButton() {
	//		this(null, null, null);
	//	}
	//
	//	public CustomButton(String customButton_1, String customButton_2, String customButton_3) {
	//		this.customButton_1 = new SimpleStringProperty(customButton_1);
	//		this.customButton_2 = new SimpleStringProperty(customButton_2);
	//		this.customButton_3 = new SimpleStringProperty(customButton_3);
	//	}
	//
	//	@XmlElement(name = "customButton_1")
	//	public String getCustomButton1() {
	//		return customButton_1.get();
	//	}
	//
	//	@XmlElement(name = "customButton_2")
	//	public String getCustomButton2() {
	//		return customButton_2.get();
	//	}
	//
	//	@XmlElement(name = "customButton_3")
	//	public String getCustomButton3() {
	//		return customButton_3.get();
	//	}
	//
	//	public StringProperty customButton1Property() {
	//		return customButton_1;
	//	}
	//
	//	public StringProperty customButton2Property() {
	//		return customButton_2;
	//	}
	//
	//	public StringProperty customButton3Property() {
	//		return customButton_3;
	//	}
	//
	//	public void setCustomButton1(String customButton) {
	//		this.customButton_1 = new SimpleStringProperty(customButton);
	//	}
	//
	//	public void setCustomButton2(String customButton) {
	//		this.customButton_2 = new SimpleStringProperty(customButton);
	//	}
	//
	//	public void setCustomButton3(String customButton) {
	//		this.customButton_3 = new SimpleStringProperty(customButton);
	//	}
}
