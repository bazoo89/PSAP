package file.entity;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Month {
	private StringProperty idMonth;
	private StringProperty salary;
	private StringProperty holidaysRes;
	private StringProperty parRes;
	private StringProperty workedDays;
	private StringProperty workedHours;
	private StringProperty sicknessUsedTemp;
	private StringProperty holidaysUsedTemp;
	private StringProperty parUsedTemp;

	public Month() {
		this(null, null, null, null, null, null, null, null, null);
	}

	public Month(String id, String salary, String holidays_res, String par_res, String worked_days, String worked_hours, String sickness_used_temp, String holidays_used_temp, String par_used_temp) {
		this.idMonth = new SimpleStringProperty(id);
		this.salary = new SimpleStringProperty(salary);
		this.holidaysRes = new SimpleStringProperty(holidays_res);
		this.parRes = new SimpleStringProperty(par_res);
		this.workedDays = new SimpleStringProperty(worked_days);
		this.workedHours = new SimpleStringProperty(worked_hours);
		this.sicknessUsedTemp = new SimpleStringProperty(sickness_used_temp);
		this.holidaysUsedTemp = new SimpleStringProperty(holidays_used_temp);
		this.parUsedTemp = new SimpleStringProperty(par_used_temp);
	}

	@XmlElement(name = "id_month")
	public String getId() {
		return idMonth.get();
	}

	@XmlElement(name = "salary")
	public String getSalary() {
		return salary.get();
	}

	@XmlElement(name = "holidays_res")
	public String getHolidaysRes() {
		return holidaysRes.get();
	}

	@XmlElement(name = "par_res")
	public String getParRes() {
		return parRes.get();
	}

	@XmlElement(name = "worked_days")
	public String getWorkedDays() {
		return workedDays.get();
	}

	@XmlElement(name = "worked_hours")
	public String getWorkedHours() {
		return workedHours.get();
	}

	@XmlElement(name = "sickness_used_temp")
	public String getSicknessUsedTemp() {
		return sicknessUsedTemp.get();
	}

	@XmlElement(name = "holidays_used_temp")
	public String getHolidaysUsedTemp() {
		return holidaysUsedTemp.get();
	}

	@XmlElement(name = "par_used_temp")
	public String getParUsedTemp() {
		return parUsedTemp.get();
	}

	public StringProperty idProperty() {
		return idMonth;
	}

	public StringProperty salaryProperty() {
		return salary;
	}

	public StringProperty holidaysResProperty() {
		return holidaysRes;
	}

	public StringProperty parResProperty() {
		return parRes;
	}

	public StringProperty workedDaysProperty() {
		return workedDays;
	}

	public StringProperty workedHoursProperty() {
		return workedHours;
	}

	public StringProperty sicknessUsedTempProperty() {
		return sicknessUsedTemp;
	}

	public StringProperty holidaysUsedTempProperty() {
		return holidaysUsedTemp;
	}

	public StringProperty parUsedTempProperty() {
		return parUsedTemp;
	}

	public void setId(String id) {
		this.idMonth = new SimpleStringProperty(id);
	}

	public void setSalary(String salary) {
		this.salary = new SimpleStringProperty(salary);
	}

	public void setHolidayRes(String holidaysRes) {
		this.holidaysRes = new SimpleStringProperty(holidaysRes);
	}

	public void setParRes(String parRes) {
		this.parRes = new SimpleStringProperty(parRes);
	}

	public void setWorkedDays(String workedDays) {
		this.workedDays = new SimpleStringProperty(workedDays);
	}

	public void setWorkedHours(String workedHours) {
		this.workedHours = new SimpleStringProperty(workedHours);
	}

	public void setSicknessUsedTemp(String sicknessUsedTemp) {
		this.sicknessUsedTemp = new SimpleStringProperty(sicknessUsedTemp);
	}

	public void setHolidaysUsedTemp(String holidaysUsedTemp) {
		this.holidaysUsedTemp = new SimpleStringProperty(holidaysUsedTemp);
	}

	public void setParUsedTemp(String parUsedTemp) {
		this.parUsedTemp = new SimpleStringProperty(parUsedTemp);
	}
}
