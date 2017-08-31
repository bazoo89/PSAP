package file.entity;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TitledPaneYear {

	private IntegerProperty id;

	public TitledPaneYear() {
		this(0);
	}

	public TitledPaneYear(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	@XmlElement(name = "id")
	public int getId() {
		return id.get();
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
}
