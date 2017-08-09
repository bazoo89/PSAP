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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.Main;
import hours.DataFile;
import hours.Hour;
import hours.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.Person;
import users.Persons;

public class ToolsForManageFile {

	private static ToolsForManageFile instance;

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
		File userLogged = Main.isLogged;
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
		File userLogged = Main.isLogged;
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
		ObservableList<Hour> hours = createAndGetHourTemplateXML(year);
		ObservableList<Month> months = createAndGetMonthTemplateXML();
		try {
			JAXBContext context = JAXBContext.newInstance(DataFile.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Wrapping our person data.
			DataFile wrapper = new DataFile();
			wrapper.setHours(hours);
			wrapper.setMonths(months);
			// Marshalling and saving XML to the file.
			m.marshal(wrapper, dataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void updateHoursTabToDataFile() {
		//TODO
	}

	public void updateSalaryTabToDataFile() {
		//TODO
	}

	public ObservableList<Hour> createAndGetHourTemplateXML(int year) {
		final String defaultHEntry = "00:00";
		final String defaultHExit = "00:00";
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
					hour = new Hour(id, defaultHEntry, defaultHExit);
					hours.add(hour);
				}
				break;
			case 2:
				month = i + "";
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
					hour = new Hour(id, defaultHEntry, defaultHExit);
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
					hour = new Hour(id, defaultHEntry, defaultHExit);
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
		final double ZERO = 0.0;
		ObservableList<Month> months = FXCollections.observableArrayList();
		int i = 0;
		while (i < 12) {
			Month month = new Month(i + 1, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO);
			months.add(month);
			i++;
		}
		return months;
	}
}
