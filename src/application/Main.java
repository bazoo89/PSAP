package application;

import java.io.File;
import java.io.IOException;

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
	private File hourMonthFile = null;
	private File preferencesFile = null;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		TempSavedInformation.getInstance().setIsLogged(new File("resources/Files/userLogged.info"));
		File isLogged = TempSavedInformation.getInstance().getIsLogged();
		if (isLogged.exists() && !isLogged.isDirectory()) {
			String loggedUser = ToolsForManageFile.getInstance().readAndGetUserLoggedInformation();
			if (loggedUser.contains(":")) {
				String[] loggedNameLastname = loggedUser.split(":");
				name = loggedNameLastname[0];
				lastname = loggedNameLastname[1];
				hourMonthFile = new File(loggedNameLastname[2]);
				String nameFile = (name.substring(0, 1) + lastname).toLowerCase() + "_preferences.xml";
				preferencesFile = new File(loggedNameLastname[2].substring(0, loggedNameLastname[2].lastIndexOf("/")) + "/" + nameFile);
				TempSavedInformation.getInstance().setName(name);
				TempSavedInformation.getInstance().setLastname(lastname);
				TempSavedInformation.getInstance().setHourMonthFile(hourMonthFile);
				TempSavedInformation.getInstance().setPreferencesFile(preferencesFile);
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root, sceneWidth, sceneHeight);
				Main.primaryStage.setScene(scene);
				Main.primaryStage.setResizable(false);
				Image image = new Image("file:resources/icons/mainIcon.png");
				Main.primaryStage.getIcons().add(image);
				Main.primaryStage.setTitle(name + " " + lastname);
				Main.primaryStage.initStyle(StageStyle.DECORATED);
				Main.primaryStage.show();
				MainController mainController = loader.getController();
				boolean loadedSuccessfully = ToolsForManageFile.getInstance().loadHoursTabFromDataFile(hourMonthFile, mainController.calendar, mainController.hh_entryCB, mainController.mm_entryCB,
						mainController.hh_exitCB, mainController.mm_exitCB, mainController.parHoursLabel, mainController.freeHoursLabel, mainController.sickHoursLabel);
				if (loadedSuccessfully) {
					mainController.countWorkedHours();
				}
				if (TempSavedInformation.getInstance().getPreferencesFile() == null || !TempSavedInformation.getInstance().getPreferencesFile().exists()) {
					String nameFile = name.substring(0, 1) + lastname + "_preferences.xml";
					String userFolderPath = TempSavedInformation.getInstance().getUserFolder().getPath();
					TempSavedInformation.getInstance().setPreferencesFile(new File(userFolderPath + "/" + nameFile.toLowerCase()));
					TempSavedInformation.getInstance().getPreferencesFile().createNewFile();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			File personsFile = new File("resources/Files/Persons.xml");
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
