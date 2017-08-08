package utils;

import java.io.File;

import application.UserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SelectUser {

	public static boolean isLogged = false;
	private Stage stage;
	private Parent root;

	public SelectUser(File usersFile) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Login.fxml"));
			root = (Parent) fxmlLoader.load();
			UserController userController = fxmlLoader.<UserController> getController();
			userController.setFile(usersFile);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.DECORATED);
			stage.setTitle("LOGIN");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void showLoginInterface() {
		stage.setScene(new Scene(root));
		stage.show();
	}
}
