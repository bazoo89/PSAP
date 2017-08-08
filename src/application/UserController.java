package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.Person;
import utils.Persons;

public class UserController implements Initializable {

	@FXML
	private TextField name_textField;
	@FXML
	private TextField surname_textField;
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;

	public static int sceneLength = 930;
	public static int sceneWidth = 700;
	private final String TITLE = "SAP&GO";
	private File personFile = null;
	private File userFile = null;
	private String loggedUser = null;
	private final String new_user = "New User";
	private final String default_file_name = "_day_and_month";
	private String name = null;
	private String surname = null;
	ObservableList<Person> personObservableList = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		personObservableList = FXCollections.observableArrayList();
	}

	public void login() {
		name = name_textField.getText();
		surname = surname_textField.getText();
		searchAndGetUser(name, surname);
		if (loggedUser == new_user) {
			try {
				String firstLetterName = name.substring(0, 1);
				String firstLetterSurname = surname.substring(0, 1);
				String fileName = firstLetterName + firstLetterSurname + default_file_name;
				userFile = new File("XMLFile/" + fileName + ".xml");
				Person person = new Person(name, surname, fileName);
				JAXBContext context = JAXBContext.newInstance(Persons.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				// Wrapping our person data.
				Persons wrapper = new Persons();
				if (wrapper.getPersons() != null) {
					List<Person> tempList = wrapper.getPersons();
					personObservableList.addAll(tempList);
					personObservableList.add(person);
				} else {
					personObservableList.add(person);
					wrapper.setPersons(personObservableList);
				}

				// Marshalling and saving XML to the file.
				m.marshal(wrapper, personFile);

				// Save the file path to the registry.
				//				setPersonFilePath(personFile);
			} catch (Exception e) { // catches ANY exception
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Could not save data");
				alert.setContentText("Could not save data to file:\n" + personFile.getPath());
				alert.showAndWait();
			}

		} else

		{
			//			userFile = new File("file:XMLFile/" + fileName + ".xml");
		}

	}

	public String searchAndGetUser(String name, String surname) {
		List<Person> personsList = new ArrayList<Person>();
		try {
			JAXBContext context = JAXBContext.newInstance(Persons.class);
			Unmarshaller um = context.createUnmarshaller();
			Persons wrapper = (Persons) um.unmarshal(personFile);
			personsList.addAll(wrapper.getPersons());
		} catch (JAXBException e) {
			loggedUser = new_user;
		}
		for (Person person : personsList) {
			// TODO
		}
		return "";
	}

	public void goToSAP() {
		login();
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (userFile != null) {
			prefs.put("filePath", personFile.getPath());
			// Update the stage title.
			Main.primaryStage.setTitle(TITLE + " - " + name);
		} else {
			prefs.remove("filePath");
			// Update the stage title.
			Main.primaryStage.setTitle(TITLE);
		}
		try {
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root, sceneLength, sceneWidth);
			Main.primaryStage.setScene(scene);
			Main.primaryStage.setResizable(false);
			Main.primaryStage.show();
			Main.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icona.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			// Update the stage title.
			Main.primaryStage.setTitle(TITLE + " - " + name);
		} else {
			prefs.remove("filePath");
			// Update the stage title.
			Main.primaryStage.setTitle(TITLE);
		}
		return false;
	}

	public void setFile(File file) {
		this.personFile = file;
	}
}
