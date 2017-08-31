package application;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import file.ToolsForManageFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.SelectUser;
import utils.TempSavedInformation;

public class Main extends Application {
	public static Stage primaryStage;
	public static int sceneWidth = 930;
	public static int sceneHeight = 400;
	//	private final String TITLE = "SAP&GO";
	private String name = null;
	private String lastname = null;
	private String hourMonthFileName = null;
	private File hourMonthFile = null;
	private File preferencesFile = null;
	private String defaultPartialFileNameDayMonth = "_day_and_month_";
	private File personsFile = new File("resources/Files/Persons.xml");

	@Override
	public void start(Stage primaryStage) throws IOException {
		Calendar calendar = GregorianCalendar.getInstance();
		Main.primaryStage = primaryStage;
		TempSavedInformation.getInstance().setIsLogged(new File("resources/Files/userLogged.info"));
		File isLogged = TempSavedInformation.getInstance().getIsLogged();

		// AUTOMATIC LOGIN
		if (isLogged.exists() && !isLogged.isDirectory()) {
			String loggedUser = ToolsForManageFile.getInstance().readAndGetUserLoggedInformation();
			if (loggedUser.contains(":")) {
				String[] loggedNameLastname = loggedUser.split(":");
				name = loggedNameLastname[0];
				lastname = loggedNameLastname[1];
				hourMonthFileName = name.substring(0, 1).toLowerCase() + lastname.toLowerCase() + defaultPartialFileNameDayMonth + calendar.get(Calendar.YEAR);
				hourMonthFile = new File("resources/Files/" + name.substring(0, 1).toLowerCase() + lastname.toLowerCase() + "/" + hourMonthFileName + ".xml");
				if (!hourMonthFile.exists()) {
					hourMonthFile.createNewFile();
					ToolsForManageFile.getInstance().initDataFile(hourMonthFile, Calendar.getInstance().get(Calendar.YEAR));
					ToolsForManageFile.getInstance().updatePersonToFile(name, lastname, hourMonthFileName, personsFile);
				}
				String nameFile = (name.substring(0, 1) + lastname).toLowerCase() + "_preferences.xml";
				preferencesFile = new File("resources/Files/" + name.substring(0, 1) + lastname + "/" + nameFile);
				TempSavedInformation.getInstance().setName(name);
				TempSavedInformation.getInstance().setLastname(lastname);
				TempSavedInformation.getInstance().setHourMonthFile(hourMonthFile);
				TempSavedInformation.getInstance().setPreferencesFile(preferencesFile);
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root, sceneWidth, sceneHeight);
				scene.getStylesheets().add("file:resources/application.css");
				Main.primaryStage.setScene(scene);
				Main.primaryStage.setResizable(false);
				Image image = new Image("file:resources/icons/mainIcon.png");
				Main.primaryStage.getIcons().add(image);
				Main.primaryStage.setTitle(name + " " + lastname);
				Main.primaryStage.initStyle(StageStyle.DECORATED);
				Main.primaryStage.show();
				MainController mainController = loader.getController();
				ToolsForManageFile.getInstance().loadHoursTabFromDataFile(hourMonthFile, mainController.calendar, mainController.hh_entryCB, mainController.mm_entryCB, mainController.hh_exitCB,
						mainController.mm_exitCB, mainController.parLabel, mainController.freeDayLabel, mainController.sickLabel, mainController.workedHoursLabel);
				if (TempSavedInformation.getInstance().getPreferencesFile() == null || !TempSavedInformation.getInstance().getPreferencesFile().exists()) {
					String nameFile = name.substring(0, 1) + lastname + "_preferences.xml";
					String userFolderPath = TempSavedInformation.getInstance().getUserFolder().getPath();
					TempSavedInformation.getInstance().setPreferencesFile(new File(userFolderPath + "/" + nameFile.toLowerCase()));
					TempSavedInformation.getInstance().getPreferencesFile().createNewFile();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// FIRST REGISTRATION
		else {
			if (!personsFile.exists()) {
				personsFile.createNewFile();
			}
			SelectUser selectUser = new SelectUser(personsFile);
			selectUser.showLoginInterface();
		}
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastname;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
