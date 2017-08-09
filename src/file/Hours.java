package file;

import java.util.List;

/**
 * Helper class to wrap a list of hours. This is used for saving the list of hours to XML.
 *
 * @author Giovanni Iodice
 */

//@XmlRootElement(name = "hours")
public class Hours {

	List<Hour> hours;

	//	@XmlElement(name = "hour", type = Hour.class)
	public List<Hour> getHours() {
		return hours;
	}

	public void setHours(List<Hour> hours) {
		this.hours = hours;
	}
}