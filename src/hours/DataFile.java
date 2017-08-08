package hours;

import java.time.Month;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the list of persons to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "datafile")
public class DataFile {

	List<Hour> hours;
	List<Month> months;

	@XmlElements({ @XmlElement(name = "hour", type = Hour.class) })
	public List<Hour> getHours() {
		return hours;
	}

	public void setHours(List<Hour> hours) {
		this.hours = hours;
	}

	@XmlElements({ @XmlElement(name = "months", type = Month.class) })
	public List<Month> getMonths() {
		return months;
	}

	public void setMonths(List<Month> months) {
		this.months = months;
	}
}