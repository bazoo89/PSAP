package application;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.SelectUser;

public class Main extends Application {
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		File personsFile = new File("XMLFile/Persons.xml");
		SelectUser selectUser = new SelectUser(personsFile);
		selectUser.showLoginInterface();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
