package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import file.ToolsForManagedFile;
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
	private TextField lastname_textField;
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;

	public static int sceneLength = 930;
	public static int sceneWidth = 780;
	private final String TITLE = "SAP&GO";
	private File personFile = null;
	private File userFile = null;
	private String loggedUser = null;
	private final String new_user = "New User";
	private final String first_user = "First User";
	private final String default_file_name = "_day_and_month";
	private String name = null;
	private String lastname = null;
	ObservableList<Person> personObservableList = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		personObservableList = FXCollections.observableArrayList();
	}

	public void loginOrRegister() {
		name = name_textField.getText().toUpperCase();
		lastname = lastname_textField.getText().toUpperCase();
		searchUser(name, lastname);
		// REGISTER
		if (loggedUser.equals(new_user) || loggedUser.equals(first_user)) {
			try {
				loggedUser = name + ":" + lastname;
				String firstLetterName = name.substring(0, 1);
				String fileName = (firstLetterName + lastname.toLowerCase() + default_file_name).toLowerCase();
				userFile = new File("XMLFile/" + fileName + ".xml");
				userFile.createNewFile();
				Person person = new Person(name, lastname, fileName);

				// First user
				if (loggedUser.equals(first_user)) {
					ToolsForManagedFile.getInstance().writePersonToFile(person, personObservableList, personFile);
				}
				// Append User
				else {
					ToolsForManagedFile.getInstance().tempSaveActualPersons(person, personObservableList, personFile);
					ToolsForManagedFile.getInstance().writePersonToFile(person, personObservableList, personFile);
				}
				// Save the file path to the registry.
				//				setPersonFilePath(personFile);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Could not save data");
				alert.setContentText("Could not save data to file:\n" + personFile.getPath());
				alert.showAndWait();
			}
		}
		// LOGIN
		else {
			// TODO
		}

	}

	public void searchUser(String name, String surname) {
		List<Person> personsList = new ArrayList<Person>();
		try {
			JAXBContext context = JAXBContext.newInstance(Persons.class);
			Unmarshaller um = context.createUnmarshaller();
			Persons wrapper = (Persons) um.unmarshal(personFile);
			personsList.addAll(wrapper.getPersons());
		} catch (JAXBException e) {
			loggedUser = first_user;
			return;
		}
		for (Person person : personsList) {
			if (person.getFirstName().equals(name) && person.getLastName().equals(surname)) {
				loggedUser = name + ":" + surname;
				return;
			}
		}
		loggedUser = new_user;
		return;
	}

	public void goToSAP() {
		loginOrRegister();
		try {
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root, sceneLength, sceneWidth);
			Main.primaryStage.setScene(scene);
			Main.primaryStage.setResizable(false);
			Main.primaryStage.show();
			Main.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icona.png")));
			Main.primaryStage.setTitle(TITLE + " ::: " + name + " " + lastname);
			Main.isLogged.createNewFile();
			ToolsForManagedFile.getInstance().writeUserLoggedInformation(loggedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFile(File file) {
		this.personFile = file;
	}

}
