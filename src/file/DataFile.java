package file;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a dataFile contains one block of hours and one of months.
 *
 * @author Giovanni Iodice
 */
@XmlRootElement(name = "datafile")
public class DataFile {

	Hours hours;
	Months months;

	@XmlElements({ @XmlElement(name = "hours", type = Hours.class) })
	public Hours getHours() {
		return hours;
	}

	public void setHours(Hours hours) {
		this.hours = hours;
	}

	@XmlElements({ @XmlElement(name = "month", type = Months.class) })
	public Months getMonths() {
		return months;
	}

	public void setMonths(Months months) {
		this.months = months;
	}
}