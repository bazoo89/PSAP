package file;

import java.util.List;

/**
 * Helper class to wrap a list of months . This is used for saving the list of months to XML.
 *
 * @author Giovanni Iodice
 */

//@XmlRootElement(name = "months")
public class Months {

	List<Month> months;

	//	@XmlElement(name = "month", type = Month.class)
	public List<Month> getMonths() {
		return months;
	}

	public void setMonths(List<Month> months) {
		this.months = months;
	}
}