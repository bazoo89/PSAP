package file;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.jfoenix.controls.JFXDatePicker;

import application.MainController;
import file.entity.CustomButton;
import file.entity.Hour;
import file.entity.Month;
import file.entity.TitledPaneYear;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import users.Person;
import users.Persons;
import utils.TempSavedInformation;

public class ToolsForManageFile {

	private static ToolsForManageFile instance;

	final String defaultHour = "00:00";
	final String defaultParFreeSick = "0.0";

	public static ToolsForManageFile getInstance() {
		if (instance == null) {
			instance = new ToolsForManageFile();
		}
		return instance;
	}

	public void writePersonToFile(Person person, ObservableList<Person> personObservableList, File personFile) {
		try {
			JAXBContext context = JAXBContext.newInstance(Persons.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Wrapping our person data.
			Persons wrapper = new Persons();
			personObservableList.add(person);
			wrapper.setPersons(personObservableList);
			// Marshalling and saving XML to the file.
			m.marshal(wrapper, personFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updatePersonToFile(String name, String lastname, String fileNameThisYear, File personFile) {
		try {
			JAXBContext context = JAXBContext.newInstance(Persons.class);
			Unmarshaller um = context.createUnmarshaller();
			Persons wrapper = (Persons) um.unmarshal(personFile);
			// Wrapping our person data.
			for (Person person : wrapper.getPersons()) {
				if (person.getFirstName().equalsIgnoreCase(name) && person.getLastName().equalsIgnoreCase(lastname)) {
					person.setDataFile(fileNameThisYear);
				}
			}
			// Marshalling and saving XML to the file.
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(wrapper, personFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void tempSaveActualPersons(Person person, ObservableList<Person> personObservableList, File personFile) {
		try {
			JAXBContext context = JAXBContext.newInstance(Persons.class);
			Unmarshaller um = context.createUnmarshaller();
			Persons wrapper = (Persons) um.unmarshal(personFile);
			personObservableList.addAll(wrapper.getPersons());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void writeUserLoggedInformation(String loggedUser) {
		File userLogged = TempSavedInformation.getInstance().getIsLogged();
		byte data[] = loggedUser.getBytes();
		Path path = Paths.get(userLogged.getAbsolutePath());
		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, APPEND))) {
			out.write(data, 0, data.length);
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	public String readAndGetUserLoggedInformation() {
		String loggedUser = "anyone";
		File userLogged = TempSavedInformation.getInstance().getIsLogged();
		Path path = Paths.get(userLogged.getAbsolutePath());
		try {
			byte data[] = Files.readAllBytes(path);
			loggedUser = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loggedUser;
	}

	public void initDataFile(File dataFile, int year) {
		ObservableList<Hour> hoursList = createAndGetHourTemplateXML(year);
		ObservableList<Month> monthsList = createAndGetMonthTemplateXML();

		try {
			JAXBContext context = JAXBContext.newInstance(DataFile.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Wrapping our person data.
			DataFile wrapper = new DataFile();
			wrapper.setHour(hoursList);
			wrapper.setMonth(monthsList);
			// Marshalling and saving XML to the file.
			m.marshal(wrapper, dataFile);
			TempSavedInformation.getInstance().setHourMonthFile(dataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateHoursTabToDataFile(File dataFile, String date, String hEntry, String hExit, String holHours, String parHours, String sickHours, String workedHours) {
		try {
			JAXBContext context = JAXBContext.newInstance(DataFile.class);
			Unmarshaller um = context.createUnmarshaller();
			DataFile wrapper = (DataFile) um.unmarshal(dataFile);
			List<Hour> hoursList = wrapper.getHour();
			String monthDate = date.substring(4, 6);
			if (monthDate.startsWith("0")) {
				monthDate = monthDate.replace("0", "");
			}
			for (Hour currentHour : hoursList) {
				if (currentHour.getId().equals(date)) {
					currentHour.setHEntry(hEntry);
					currentHour.setHExit(hExit);
					currentHour.setHolidaysHoursUsed(holHours);
					currentHour.setParHoursUsed(parHours);
					currentHour.setSicknessHoursUsed(sickHours);
					currentHour.setWorkedHours(workedHours);

					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					m.marshal(wrapper, dataFile);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean loadHoursTabFromDataFile(File dataFile, JFXDatePicker calendar, ComboBox<String> hh_entryCB, ComboBox<String> mm_entryCB, ComboBox<String> hh_exitCB, ComboBox<String> mm_exitCB,
			Label parLabel, Label freeLabel, Label sickLabel, Label workedHours) {
		boolean loadedSuccessfuly = false;
		try {
			JAXBContext context = JAXBContext.newInstance(DataFile.class);
			Unmarshaller um = context.createUnmarshaller();
			DataFile wrapper = (DataFile) um.unmarshal(dataFile);
			List<Hour> hoursList = wrapper.getHour();
			String date = calendar.getValue().toString().replace("-", "");
			for (Hour hour : hoursList) {
				if (hour.getId().equals(date)) {
					if (!hour.getHEntry().equals(defaultHour)) {
						String[] hourArray = hour.getHEntry().split(":");
						String hh = hourArray[0];
						String mm = hourArray[1];
						hh_entryCB.setValue(hh);
						mm_entryCB.setValue(mm);
						loadedSuccessfuly = true;
					}
					if (!hour.getHExit().equals(defaultHour)) {
						String[] hourArray = hour.getHExit().split(":");
						String hh = hourArray[0];
						String mm = hourArray[1];
						hh_exitCB.setValue(hh);
						mm_exitCB.setValue(mm);
						loadedSuccessfuly = true;
					}
					if (!hour.getWorkedHours().equals(defaultParFreeSick)) {
						workedHours.setText(hour.getWorkedHours());
						loadedSuccessfuly = true;
					}
					// Par hours used
					if (!hour.getParHoursUsed().equals(defaultParFreeSick)) {
						parLabel.setText(hour.getParHoursUsed());
						parLabel.setVisible(true);
						MainController.areParHoursSetted = true;
						loadedSuccessfuly = true;
					}
					// Holidays hours used
					if (!hour.getHolidaysHoursUsed().equals(defaultParFreeSick)) {
						freeLabel.setText(hour.getHolidaysHoursUsed());
						freeLabel.setVisible(true);
						MainController.areFreeHoursSetted = true;
						loadedSuccessfuly = true;
					}
					// Sickness hours used
					if (!hour.getSicknessHoursUsed().equals(defaultParFreeSick)) {
						sickLabel.setText(hour.getSicknessHoursUsed());
						sickLabel.setVisible(true);
						MainController.areSickHoursSetted = true;
						loadedSuccessfuly = true;
					}
					if (loadedSuccessfuly) {
						Marshaller m = context.createMarshaller();
						m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						m.marshal(wrapper, dataFile);
						return loadedSuccessfuly;
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("You have deleted hours and month file for the current year!!!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
			System.exit(1);
		}
		return loadedSuccessfuly;
	}

	public ObservableList<Hour> createAndGetHourTemplateXML(int year) {
		boolean isLeapYear = year % 4 == 0 ? true : false;
		String stringYear = year + "";
		Hour hour = null;
		String month = null;
		String day = null;
		ObservableList<Hour> hours = FXCollections.observableArrayList();
		for (int i = 1; i <= 12; i++) {
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if (i != 10 && i != 12) {
					month = "0" + i;
				} else {
					month = i + "";
				}
				for (int y = 1; y <= 31; y++) {
					if (y < 10) {
						day = "0" + y;
					} else {
						day = y + "";
					}
					String id = stringYear + month + day;
					hour = new Hour(id, defaultHour, defaultHour, defaultParFreeSick, defaultParFreeSick, defaultParFreeSick, defaultParFreeSick);
					hours.add(hour);
				}
				break;
			case 2:
				month = "0" + i;
				for (int y = 1; y <= 28; y++) {
					if (y < 10) {
						day = "0" + y;
					} else {
						day = y + "";
					}
					if (isLeapYear) {
						day = (Integer.parseInt(day) + 1) + "";
					}
					String id = stringYear + month + day;
					hour = new Hour(id, defaultHour, defaultHour, defaultParFreeSick, defaultParFreeSick, defaultParFreeSick, defaultParFreeSick);
					hours.add(hour);
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				if (i != 11) {
					month = "0" + i;
				} else {
					month = i + "";
				}
				for (int y = 1; y <= 30; y++) {
					if (y < 10) {
						day = "0" + y;
					} else {
						day = y + "";
					}
					String id = stringYear + month + day;
					hour = new Hour(id, defaultHour, defaultHour, defaultParFreeSick, defaultParFreeSick, defaultParFreeSick, defaultParFreeSick);
					hours.add(hour);
				}
				break;
			default:
				break;
			}
		}
		return hours;
	}

	public ObservableList<Month> createAndGetMonthTemplateXML() {
		final String ZERO = "0.0";
		ObservableList<Month> months = FXCollections.observableArrayList();
		String[] monthList = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		int i = 0;
		while (i < monthList.length) {
			String stringMonth = monthList[i];
			Month month = new Month(String.valueOf(i + 1), stringMonth, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO);
			months.add(month);
			i++;
		}
		return months;
	}

	public ObservableList<CustomButton> createAndGetCustomButtonTemplateXML() {
		final String default_value_button = "unsetted";
		final int max_num_custom_button = 3;
		ObservableList<CustomButton> customButtons = FXCollections.observableArrayList();
		for (int i = 1; i <= max_num_custom_button; i++) {
			CustomButton customButton = new CustomButton(String.valueOf(i), default_value_button);
			customButtons.add(customButton);
		}
		return customButtons;
	}

	public ObservableList<TitledPaneYear> createAndGetTitledPaneTemplateXML() {
		ObservableList<TitledPaneYear> titledPanes = FXCollections.observableArrayList();
		Calendar calendar = GregorianCalendar.getInstance();
		TitledPaneYear currentTitledPaneYear = new TitledPaneYear(calendar.get(Calendar.YEAR));
		titledPanes.add(currentTitledPaneYear);
		return titledPanes;
	}

	public void initPreferencesFile(File preferencesFile) {
		ObservableList<CustomButton> customButtonsList = createAndGetCustomButtonTemplateXML();
		ObservableList<TitledPaneYear> titledPaneList = createAndGetTitledPaneTemplateXML();
		try {
			JAXBContext context = JAXBContext.newInstance(PreferencesFile.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Wrapping our person data.
			PreferencesFile wrapper = new PreferencesFile();
			wrapper.setCustomButtons(customButtonsList);
			wrapper.setTitledPanesYears(titledPaneList);
			// Marshalling and saving XML to the file.
			m.marshal(wrapper, preferencesFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/***** CUSTOM BUTTON *****/
	public void saveCustomButtonPreferences(File dataFile, String customButtonValue) {
		PreferencesFile wrapper = null;
		try {
			JAXBContext context = JAXBContext.newInstance(PreferencesFile.class);
			Unmarshaller um = context.createUnmarshaller();
			wrapper = (PreferencesFile) um.unmarshal(dataFile);
			for (CustomButton customButton : wrapper.getCustomButtons()) {
				if (customButton.getValue().equals("unsetted")) {
					customButton.setValue(customButtonValue);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					m.marshal(wrapper, dataFile);
					break;
				}
			}
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteCustomButtonPreferences(File dataFile, String customButtonValue) {
		try {
			JAXBContext context = JAXBContext.newInstance(PreferencesFile.class);
			Unmarshaller um = context.createUnmarshaller();
			PreferencesFile wrapper = (PreferencesFile) um.unmarshal(dataFile);
			for (CustomButton customButton : wrapper.getCustomButtons()) {
				if (customButton.getValue().equals(customButtonValue)) {
					customButton.setValue("unsetted");
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					m.marshal(wrapper, dataFile);
					break;
				}
			}
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public List<CustomButton> loadCustomButtonPreferences(File dataFile) {
		List<CustomButton> customButtonList = new ArrayList<CustomButton>();
		try {
			JAXBContext context = JAXBContext.newInstance(PreferencesFile.class);
			Unmarshaller um = context.createUnmarshaller();
			PreferencesFile wrapper = (PreferencesFile) um.unmarshal(dataFile);
			for (CustomButton customButton : wrapper.getCustomButtons()) {
				if (!customButton.getValue().equals("unsetted")) {
					customButtonList.add(customButton);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return customButtonList;
	}

	/********** ********** **********/

	public void updateSalaryTabToDataFile(File dataFile, String currentMonth, String newSalary) {
		try {
			JAXBContext context = JAXBContext.newInstance(DataFile.class);
			Unmarshaller um = context.createUnmarshaller();
			DataFile wrapper = (DataFile) um.unmarshal(dataFile);
			List<Month> monthsList = wrapper.getMonth();
			for (Month month : monthsList) {
				if (month.getId().equals(currentMonth)) {
					month.setSalary(newSalary);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					m.marshal(wrapper, dataFile);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Month> getMonthsFromDataFile(File dataFile) {
		ArrayList<Month> monthList = new ArrayList<Month>();
		try {
			JAXBContext context = JAXBContext.newInstance(DataFile.class);
			Unmarshaller um = context.createUnmarshaller();
			DataFile wrapper = (DataFile) um.unmarshal(dataFile);
			for (Month month : wrapper.getMonth()) {
				monthList.add(month);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return monthList;
	}

	public ArrayList<Hour> getHoursFromDataFile(File dataFile) {
		ArrayList<Hour> hoursList = new ArrayList<Hour>();
		try {
			JAXBContext context = JAXBContext.newInstance(DataFile.class);
			Unmarshaller um = context.createUnmarshaller();
			DataFile wrapper = (DataFile) um.unmarshal(dataFile);
			for (Hour hour : wrapper.getHour()) {
				hoursList.add(hour);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hoursList;
	}

	/***** TITLED PANE YEARS *****/

	public ArrayList<TitledPaneYear> getTitledPanesTextYears(File dataFile) {
		// TODO Deve leggere dal file di PREFERENCES
		PreferencesFile wrapper = null;
		try {
			JAXBContext context = JAXBContext.newInstance(PreferencesFile.class);
			Unmarshaller um = context.createUnmarshaller();
			wrapper = (PreferencesFile) um.unmarshal(dataFile);
			return (ArrayList<TitledPaneYear>) wrapper.getTitledPanesYears();
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return new ArrayList<TitledPaneYear>();
	}

	public void saveTitledPanesTextYears(File dataFile, int titledPaneYear) {
		PreferencesFile wrapper = null;
		try {
			JAXBContext context = JAXBContext.newInstance(PreferencesFile.class);
			Unmarshaller um = context.createUnmarshaller();
			wrapper = (PreferencesFile) um.unmarshal(dataFile);
			ArrayList<TitledPaneYear> listTitledPane = (ArrayList<TitledPaneYear>) wrapper.getTitledPanesYears();
			TitledPaneYear newYear = new TitledPaneYear(titledPaneYear);
			listTitledPane.add(newYear);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(wrapper, dataFile);
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteTitledPanesTextYears(File dataFile, int titledPaneYear) {
		int indexYearFound = -1;
		ArrayList<TitledPaneYear> listTitledPane = null;
		try {
			JAXBContext context = JAXBContext.newInstance(PreferencesFile.class);
			Unmarshaller um = context.createUnmarshaller();
			PreferencesFile wrapper = (PreferencesFile) um.unmarshal(dataFile);
			listTitledPane = (ArrayList<TitledPaneYear>) wrapper.getTitledPanesYears();
			for (TitledPaneYear titledPane : listTitledPane) {
				if (titledPane.getId() == titledPaneYear) {
					indexYearFound = listTitledPane.indexOf(titledPane);
					break;
				}
			}
			if (indexYearFound != -1) {
				listTitledPane.remove(indexYearFound);
			}
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(wrapper, dataFile);
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	/********** ********** **********/
}
