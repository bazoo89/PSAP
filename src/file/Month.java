package file;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Month {
	private IntegerProperty id;
	private DoubleProperty salary;
	private DoubleProperty holidaysRes;
	private DoubleProperty parRes;
	private DoubleProperty workedDays;
	private DoubleProperty workedHours;
	private DoubleProperty sicknessUsedTemp;
	private DoubleProperty holidaysUsedTemp;
	private DoubleProperty parUsedTemp;

	public Month() {
		this(null, null, null, null, null, null, null, null, null);
	}

	public Month(Integer id, Double salary, Double holidays_res, Double par_res, Double worked_days, Double worked_hours, Double sickness_used_temp, Double holidays_used_temp, Double par_used_temp) {
		this.id = new SimpleIntegerProperty(id);
		this.salary = new SimpleDoubleProperty(salary);
		this.holidaysRes = new SimpleDoubleProperty(holidays_res);
		this.parRes = new SimpleDoubleProperty(par_res);
		this.workedDays = new SimpleDoubleProperty(worked_days);
		this.workedHours = new SimpleDoubleProperty(worked_hours);
		this.sicknessUsedTemp = new SimpleDoubleProperty(sickness_used_temp);
		this.holidaysUsedTemp = new SimpleDoubleProperty(holidays_used_temp);
		this.parUsedTemp = new SimpleDoubleProperty(par_used_temp);
	}

	@XmlElement(name = "id")
	public Integer getId() {
		return id.get();
	}

	@XmlElement(name = "salary")
	public Double getSalary() {
		return salary.get();
	}

	@XmlElement(name = "holidays_res")
	public Double getHolidaysRes() {
		return holidaysRes.get();
	}

	@XmlElement(name = "par_res")
	public Double getParRes() {
		return parRes.get();
	}

	@XmlElement(name = "worked_days")
	public Double getWorkedDays() {
		return workedDays.get();
	}

	@XmlElement(name = "worked_hours")
	public Double getWorkedHours() {
		return workedHours.get();
	}

	@XmlElement(name = "sickness_used_temp")
	public Double getSicknessUsedTemp() {
		return sicknessUsedTemp.get();
	}

	@XmlElement(name = "holidays_used_temp")
	public Double getHolidaysUsedTemp() {
		return holidaysUsedTemp.get();
	}

	@XmlElement(name = "par_used_temp")
	public Double getPar() {
		return parUsedTemp.get();
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public DoubleProperty salaryProperty() {
		return salary;
	}

	public DoubleProperty holidaysResProperty() {
		return holidaysRes;
	}

	public DoubleProperty parResProperty() {
		return parRes;
	}

	public DoubleProperty workedDaysProperty() {
		return workedDays;
	}

	public DoubleProperty workedHoursProperty() {
		return workedHours;
	}

	public DoubleProperty sicknessUsedTempProperty() {
		return sicknessUsedTemp;
	}

	public DoubleProperty holidaysUsedTempProperty() {
		return holidaysUsedTemp;
	}

	public DoubleProperty parUsedTempProperty() {
		return parUsedTemp;
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public void setSalary(Double holidaysRes) {
		this.holidaysRes = new SimpleDoubleProperty(holidaysRes);
	}

	public void setParRes(Double parRes) {
		this.parRes = new SimpleDoubleProperty(parRes);
	}

	public void setWorkedDays(Double workedDays) {
		this.workedDays = new SimpleDoubleProperty(workedDays);
	}

	public void setWorkedHours(Double workedHours) {
		this.workedHours = new SimpleDoubleProperty(workedHours);
	}

	public void setSicknessUsedTemp(Double sicknessUsedTemp) {
		this.salary = new SimpleDoubleProperty(sicknessUsedTemp);
	}

	public void setHolidaysUsedTemp(Double holidaysUsedTemp) {
		this.holidaysUsedTemp = new SimpleDoubleProperty(holidaysUsedTemp);
	}

	public void setParUsedTemp(Double parUsedTemp) {
		this.parUsedTemp = new SimpleDoubleProperty(parUsedTemp);
	}
}
