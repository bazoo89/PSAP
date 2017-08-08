package application;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.SelectUser;

public class Main extends Application {
	public static Stage primaryStage;
	public static int sceneLength = 930;
	public static int sceneWidth = 700;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		File personsFile = new File("file:XMLFile/Persons.xml");
		SelectUser selectUser = new SelectUser(personsFile);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
