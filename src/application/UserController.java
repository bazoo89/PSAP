package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import file.ToolsForManageFile;
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
import javafx.stage.StageStyle;
import users.Person;
import users.Persons;
import utils.TempSavedInformation;

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
	private String fileName = null;
	ObservableList<Person> personObservableList = null;
	private String loggedUserPathFile = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		personObservableList = FXCollections.observableArrayList();
	}

	public void loginOrRegister() {
		File folder = null;
		name = name_textField.getText().toUpperCase();
		lastname = lastname_textField.getText().toUpperCase();
		String firstLetterName = name.substring(0, 1);
		String userFolder = (firstLetterName.toLowerCase() + lastname.toLowerCase());
		fileName = (firstLetterName.toLowerCase() + lastname.toLowerCase() + default_file_name).toLowerCase();
		searchUser(name, lastname);
		// REGISTER
		if (loggedUser.equals(new_user) || loggedUser.equals(first_user)) {
			try {
				folder = new File("resources/Files/" + userFolder);
				folder.mkdir();
				TempSavedInformation.getInstance().setUserFolder(folder);
				userFile = new File("resources/Files/" + userFolder + "/" + fileName + ".xml");
				userFile.createNewFile();
				TempSavedInformation.getInstance().setHourMonthFile(userFile);
				ToolsForManageFile.getInstance().initDataFile(userFile, Calendar.getInstance().get(Calendar.YEAR));
				Person person = new Person(name, lastname, fileName);

				// First user
				if (loggedUser.equals(first_user)) {
					ToolsForManageFile.getInstance().writePersonToFile(person, personObservableList, personFile);
				}
				// Append User
				else {
					ToolsForManageFile.getInstance().tempSaveActualPersons(person, personObservableList, personFile);
					ToolsForManageFile.getInstance().writePersonToFile(person, personObservableList, personFile);
				}
				loggedUser = name + ":" + lastname + ":" + userFile.getPath().replace("\\", "/");
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
			folder = new File("resources/Files/" + userFolder);
			TempSavedInformation.getInstance().setUserFolder(folder);
			userFile = new File("resources/Files/" + userFolder + "/" + fileName + ".xml");
			TempSavedInformation.getInstance().setHourMonthFile(userFile);
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
				String nameFile = person.getDataFile() + ".xml";
				String userFolder = (person.getFirstName().substring(0, 1) + person.getLastName()).toLowerCase();
				loggedUserPathFile = "resources/Files/" + userFolder + "/" + nameFile;
				loggedUser = name + ":" + surname + ":" + loggedUserPathFile;
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, sceneLength, sceneWidth);
			Main.primaryStage.setScene(scene);
			Main.primaryStage.setResizable(false);
			Image image = new Image("file:resources/icons/mainIcon.png");
			Main.primaryStage.getIcons().add(image);
			Main.primaryStage.setTitle(TITLE + " ::: " + name + " " + lastname);
			TempSavedInformation.getInstance().getIsLogged().createNewFile();
			TempSavedInformation.getInstance().setName(name);
			TempSavedInformation.getInstance().setLastname(lastname);
			TempSavedInformation.getInstance().setName(name);
			ToolsForManageFile.getInstance().writeUserLoggedInformation(loggedUser);
			if (TempSavedInformation.getInstance().getPreferencesFile() == null || !TempSavedInformation.getInstance().getPreferencesFile().exists()) {
				String nameFile = name.substring(0, 1) + lastname + "_preferences.xml";
				String userFolderPath = TempSavedInformation.getInstance().getUserFolder().getPath();
				File preferencesFile = new File(userFolderPath + "/" + nameFile.toLowerCase());
				TempSavedInformation.getInstance().setPreferencesFile(preferencesFile);
				TempSavedInformation.getInstance().getPreferencesFile().createNewFile();
				ToolsForManageFile.getInstance().initCustomButtonPreferencesFile(preferencesFile);
			}
			MainController mainController = loader.getController();
			boolean loadedSuccessfully = ToolsForManageFile.getInstance().loadHoursTabFromDataFile(TempSavedInformation.getInstance().getHourMonthFile(), mainController.calendar,
					mainController.hh_entryCB, mainController.mm_entryCB, mainController.hh_exitCB, mainController.mm_exitCB);
			if (loadedSuccessfully) {
				mainController.countWorkedHours();
			}
			Main.primaryStage.initStyle(StageStyle.DECORATED);
			Main.primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFile(File file) {
		this.personFile = file;
	}

}
