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
import utils.SelectUser;
import utils.TempSavedInformation;

public class Main extends Application {
	public static Stage primaryStage;
	public static int sceneLength = 930;
	public static int sceneWidth = 780;
	private final String TITLE = "SAP&GO";
	private String name = null;
	private String lastname = null;
	private File hourMonthFile = null;
	private File preferencesFile = null;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		TempSavedInformation.getInstance().setIsLogged(new File("XMLFile/userLogged.info"));
		File isLogged = TempSavedInformation.getInstance().getIsLogged();
		if (isLogged.exists() && !isLogged.isDirectory()) {
			String loggedUser = ToolsForManageFile.getInstance().readAndGetUserLoggedInformation();
			if (loggedUser.contains(":")) {
				String[] loggedNameLastname = loggedUser.split(":");
				name = loggedNameLastname[0];
				lastname = loggedNameLastname[1];
				hourMonthFile = new File(loggedNameLastname[2]);
				TempSavedInformation.getInstance().setName(name);
				TempSavedInformation.getInstance().setLastname(lastname);
				TempSavedInformation.getInstance().setHourMonthFile(hourMonthFile);
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root, sceneLength, sceneWidth);
				Main.primaryStage.setScene(scene);
				Main.primaryStage.setResizable(false);
				Main.primaryStage.show();
				Image image=new Image("file:resources/icons/mainIcon.png");
				Main.primaryStage.getIcons().add(image);
				Main.primaryStage.setTitle(TITLE + " ::: " + name + " " + lastname);
				MainController mainController = loader.getController();
				String date = mainController.calendar.getValue().toString().replace("-", "");
				boolean loadedSuccessfully = ToolsForManageFile.getInstance().loadHoursTabFromDataFile(hourMonthFile, date, mainController.hh_entryCB, mainController.mm_entryCB,
						mainController.hh_exitCB, mainController.mm_exitCB);
				if (loadedSuccessfully) {
					mainController.countWorkedHours();
				}
				if (TempSavedInformation.getInstance().getPreferencesFile() == null || !TempSavedInformation.getInstance().getPreferencesFile().exists()) {
					String nameFile = name.substring(0, 1) + lastname + "_preferences.xml";
					TempSavedInformation.getInstance().setPreferencesFile(new File("XMLFile/" + nameFile.toLowerCase()));
					TempSavedInformation.getInstance().getPreferencesFile().createNewFile();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			File personsFile = new File("XMLFile/Persons.xml");
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
