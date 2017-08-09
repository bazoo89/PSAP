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

public class Main extends Application {
	public static Stage primaryStage;

	public static int sceneLength = 930;
	public static int sceneWidth = 780;
	public static File isLogged = null;
	private final String TITLE = "SAP&GO";
	public static String name = null;
	public static String lastname = null;
	public static String pathFile = null;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		name = "";
		lastname = "";
		pathFile = "";
		isLogged = new File("XMLFile/userLogged.info");
		if (isLogged.exists() && !isLogged.isDirectory()) {
			String loggedUser = ToolsForManageFile.getInstance().readAndGetUserLoggedInformation();
			if (loggedUser.contains(":")) {
				String[] loggedNameLastname = loggedUser.split(":");
				name = loggedNameLastname[0];
				lastname = loggedNameLastname[1];
				pathFile = loggedNameLastname[2];
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root, sceneLength, sceneWidth);
				Main.primaryStage.setScene(scene);
				Main.primaryStage.setResizable(false);
				Main.primaryStage.show();
				Main.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icona.png")));
				Main.primaryStage.setTitle(TITLE + " ::: " + name + " " + lastname);
				MainController mainController = loader.getController();
				String date = mainController.calendar.getValue().toString().replace("-", "");
				boolean loadedSuccessfully = ToolsForManageFile.getInstance().loadHoursTabFromDataFile(new File(pathFile), date, mainController.hh_entryCB, mainController.mm_entryCB,
						mainController.hh_exitCB, mainController.mm_exitCB);
				if (loadedSuccessfully) {
					mainController.countWorkedHours();
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

	public String getFilename() {
		return pathFile;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
