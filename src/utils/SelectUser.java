package utils;

import java.io.File;
import java.util.prefs.Preferences;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SelectUser {

	private final String TITLE = "SAP&GO";
	public static boolean isLogged = false;

	public SelectUser(File usersFile) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Login.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("LOGIN");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			// Update the stage title.
			Main.primaryStage.setTitle(TITLE + " - " + file.getName());
		} else {
			prefs.remove("filePath");
			// Update the stage title.
			Main.primaryStage.setTitle(TITLE);
		}
		return false;
	}

}
