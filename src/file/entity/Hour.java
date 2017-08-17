package file.entity;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hour {
	private StringProperty idHour;
	private StringProperty h_entry;
	private StringProperty h_exit;
	private StringProperty holidaysHoursUsed;
	private StringProperty parHoursUsed;
	private StringProperty sicknessHoursUsed;

	public Hour() {
		this(null, null, null, null, null, null);
	}

	public Hour(String id, String hEntry, String hExit, String holUsed, String parUsed, String sickUsed) {
		this.idHour = new SimpleStringProperty(id);
		this.h_entry = new SimpleStringProperty(hEntry);
		this.h_exit = new SimpleStringProperty(hExit);
		this.holidaysHoursUsed = new SimpleStringProperty(holUsed);
		this.parHoursUsed = new SimpleStringProperty(parUsed);
		this.sicknessHoursUsed = new SimpleStringProperty(sickUsed);
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
	
	@XmlElement(name = "hours_holidays")
	public String getHHolidays() {
		return holidaysHoursUsed.get();
	}
	
	@XmlElement(name = "hours_par")
	public String getHPar() {
		return parHoursUsed.get();
	}
	
	@XmlElement(name = "hours_sickness")
	public String getHSickness() {
		return sicknessHoursUsed.get();
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
	
	public StringProperty holidaysHourProperty() {
		return holidaysHoursUsed;
	}
	
	public StringProperty parHoursProperty() {
		return parHoursUsed;
	}
	
	public StringProperty sicknessHourProperty() {
		return sicknessHoursUsed;
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
	
	public void setHolidaysHourUsed(String holidaysUsed) {
		this.holidaysHoursUsed = new SimpleStringProperty(holidaysUsed);
	}
	
	public void setParHourUsed(String parUsed) {
		this.parHoursUsed = new SimpleStringProperty(parUsed);
	}
	
	public void setSicknessHourUsed(String sicknessUSed) {
		this.sicknessHoursUsed = new SimpleStringProperty(sicknessUSed);
	}
}
