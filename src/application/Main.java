package application;

import java.io.File;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage primaryStage;
	public static int sceneLength = 930;
	public static int sceneWidth = 700;
	private final String TITLE = "SAP&GO";

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root, sceneLength, sceneWidth);
			//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.primaryStage = primaryStage;
			this.primaryStage.setScene(scene);
			this.primaryStage.setResizable(false);
			this.primaryStage.show();
			this.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icona.png")));
			File personsFile = new File("file:XMLFile/Persons.xml");
			setPersonFilePath(personsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			this.primaryStage.setTitle(TITLE + " - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			this.primaryStage.setTitle(TITLE);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
