package utils;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private final StringProperty firstName;
	private final StringProperty lastName;
	private StringProperty dataFile;

	public Person(String firstName, String lastName, String dataFile) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.dataFile = new SimpleStringProperty(dataFile);
	}

	@XmlElement(name = "name")
	public String getFirstName() {
		return firstName.get();
	}

	@XmlElement(name = "surname")
	public String getLastName() {
		return lastName.get();
	}

	@XmlElement(name = "dataFile")
	public String getDataFile() {
		return dataFile.get();
	}

	public void setDataFile(String dataFile) {
		this.dataFile = new SimpleStringProperty(dataFile);
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
}
