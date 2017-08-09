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

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		String name = "";
		String lastname = "";
		isLogged = new File("XMLFile/userLogged.info");
		if (isLogged.exists() && !isLogged.isDirectory()) {
			String loggedUser = ToolsForManageFile.getInstance().readAndGetUserLoggedInformation();
			if (loggedUser.contains(":")) {
				String[] loggedNameLastname = loggedUser.split(":");
				name = loggedNameLastname[0];
				lastname = loggedNameLastname[1];
			}
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
				Scene scene = new Scene(root, sceneLength, sceneWidth);
				Main.primaryStage.setScene(scene);
				Main.primaryStage.setResizable(false);
				Main.primaryStage.show();
				Main.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icona.png")));
				Main.primaryStage.setTitle(TITLE + " ::: " + name + " " + lastname);
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

	public static void main(String[] args) {
		launch(args);
	}
}
