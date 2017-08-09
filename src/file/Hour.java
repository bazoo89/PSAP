package file;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hour {
	private StringProperty idHour;
	private StringProperty h_entry;
	private StringProperty h_exit;

	public Hour() {
		this(null, null, null);
	}

	public Hour(String id, String hEntry, String hExit) {
		this.idHour = new SimpleStringProperty(id);
		this.h_entry = new SimpleStringProperty(hEntry);
		this.h_exit = new SimpleStringProperty(hExit);
	}

	@XmlElement(name = "id_hour")
	public String getId() {
		return idHour.get().toUpperCase();
	}

	@XmlElement(name = "h_entry")
	public String getHEntry() {
		return h_entry.get().toUpperCase();
	}

	@XmlElement(name = "h_exit")
	public String getHExit() {
		return h_exit.get();
	}

	public StringProperty idProperty() {
		return idHour;
	}

	public StringProperty hEntryProperty() {
		return h_entry;
	}

	public StringProperty hExitProperty() {
		return h_exit;
	}

	public void setId(String id) {
		this.idHour = new SimpleStringProperty(id);
	}

	public void setHEntry(String hEntry) {
		this.h_entry = new SimpleStringProperty(hEntry);
	}

	public void setHExit(String hExit) {
		this.h_exit = new SimpleStringProperty(hExit);
	}
}
