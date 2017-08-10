package file;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import file.entity.Hour;
import file.entity.Month;

/**
 * Helper class to wrap a dataFile contains one block of hours and one of months.
 *
 * @author Giovanni Iodice
 */
@XmlRootElement(name = "datafile")
public class DataFile {

	List<Hour> hours;
	List<Month> months;

	@XmlElementWrapper(name = "hours")
	@XmlElement(name = "hour", type = Hour.class)
	public List<Hour> getHour() {
		return hours;
	}

	public void setHour(List<Hour> hours) {
		this.hours = hours;
	}

	@XmlElementWrapper(name = "months")
	@XmlElement(name = "month", type = Month.class)
	public List<Month> getMonth() {
		return months;
	}

	public void setMonth(List<Month> months) {
		this.months = months;
	}
}