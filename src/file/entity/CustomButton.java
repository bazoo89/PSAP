package file.entity;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomButton implements Comparable<CustomButton> {

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

	@Override
	public int compareTo(CustomButton o) {
		return Integer.parseInt(this.getId()) - Integer.parseInt(o.getId());
	}
}
