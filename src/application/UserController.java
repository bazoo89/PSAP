package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UserController implements Initializable {

	@FXML
	private TextField name_textField;
	@FXML
	private TextField surname_textField;
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;

	public static int sceneLength = 930;
	public static int sceneWidth = 700;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	//******* This method counts the worked hours and displays the result on the workedHours text field *******//

	public void goToSAP() {
		try {
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root, sceneLength, sceneWidth);
			//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.primaryStage.setScene(scene);
			Main.primaryStage.setResizable(false);
			Main.primaryStage.show();
			Main.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icona.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		//			}
	}
	///////////////////////////////////////
}
