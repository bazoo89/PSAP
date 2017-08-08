package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	public static Stage primaryStage;
	public static int sceneLength=930;
	public static int sceneWidth=700;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root,sceneLength,sceneWidth);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.primaryStage=primaryStage;
			this.primaryStage.setScene(scene);
			this.primaryStage.setTitle("SAP&GO");
			this.primaryStage.setResizable(false);
			this.primaryStage.show();
			this.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icona.png")));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
