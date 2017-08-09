package file;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Helper class to wrap a list of months . This is used for saving the list of months to XML.
 *
 * @author Giovanni Iodice
 */
public class Months {

	List<Month> months;

	@XmlElements({ @XmlElement(name = "months", type = Month.class) })
	public List<Month> getMonths() {
		return months;
	}

	public void setMonths(List<Month> months) {
		this.months = months;
	}
}