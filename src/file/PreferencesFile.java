package file;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import file.entity.CustomButton;
import file.entity.TitledPaneYear;

/**
 * Helper class to wrap a file with user preferences
 *
 * @author Giovanni Iodice
 */
@XmlRootElement(name = "preferences")
public class PreferencesFile {

	List<CustomButton> buttons;
	List<TitledPaneYear> titledPane;

	@XmlElementWrapper(name = "buttons")
	@XmlElement(name = "button", type = CustomButton.class)
	public List<CustomButton> getCustomButtons() {
		return buttons;
	}

	public void setCustomButtons(List<CustomButton> preferences) {
		this.buttons = preferences;
	}

	@XmlElementWrapper(name = "titledPanes")
	@XmlElement(name = "titledPane", type = TitledPaneYear.class)
	public List<TitledPaneYear> getTitledPanesYears() {
		return titledPane;
	}

	public void setTitledPanesYears(List<TitledPaneYear> preferences) {
		this.titledPane = preferences;
	}
}
