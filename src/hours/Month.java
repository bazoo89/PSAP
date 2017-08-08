package hours;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Month {
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty dataFile;

	public Month() {
		this(null, null, null);
	}

	public Month(String firstName, String lastName, String dataFile) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.dataFile = new SimpleStringProperty(dataFile);
	}

	@XmlElement(name = "name")
	public String getFirstName() {
		return firstName.get().toUpperCase();
	}

	@XmlElement(name = "lastname")
	public String getLastName() {
		return lastName.get().toUpperCase();
	}

	@XmlElement(name = "dataFile")
	public String getDataFile() {
		return dataFile.get();
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public StringProperty dataFileProperty() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = new SimpleStringProperty(dataFile);
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
}
