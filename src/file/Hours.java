package file;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Helper class to wrap a list of hours. This is used for saving the list of hours to XML.
 *
 * @author Giovanni Iodice
 */
public class Hours {

	List<Hour> hours;

	@XmlElements({ @XmlElement(name = "hour", type = Hour.class) })
	public List<Hour> getHours() {
		return hours;
	}

	public void setHours(List<Hour> hours) {
		this.hours = hours;
	}
}