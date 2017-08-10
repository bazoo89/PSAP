package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import file.ToolsForManageFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.TempSavedInformation;

public class ButtonAreaController implements Initializable {
	@FXML
	public JFXButton allDayBtn;
	@FXML
	public VBox buttonAreaVBox;
	@FXML
	public JFXButton onlyMorningBtn;
	@FXML
	public JFXButton onlyAfternoonBtn;
	@FXML
	public JFXButton createCustomBtn;
	@FXML
	public JFXButton customBtn1;
	@FXML
	public JFXButton customBtn2;
	@FXML
	public JFXButton customBtn3;
	@FXML
	public Button recycleBtn1;
	@FXML
	public Button recycleBtn2;
	@FXML
	public Button recycleBtn3;
	@FXML
	public JFXButton cancelBtn;
	@FXML
	public TextField customHHEntryTF;
	@FXML
	public TextField customHHExitTF;
	@FXML
	public TextField customMMEntryTF;
	@FXML
	public TextField customMMExitTF;

	public FXMLLoader loader = null;
	public MainController mainController = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
		mainController = loader.getController();
	}

	public void removeCustomBtn(String customButtonText) {
		boolean found = false;
		JFXButton cButton = null;
		HBox hbox = null;
		HBox customButtonsHBox = (HBox) buttonAreaVBox.getChildren().get(1);
		for (Node node : customButtonsHBox.getChildren()) {
			hbox = (HBox) node;
			cButton = (JFXButton) hbox.getChildren().get(1);
			if (cButton.getText().equals(customButtonText)) {
				found = true;
				break;
			}
		}
		if (found) {
			ToolsForManageFile.getInstance().deleteCustomButtonPreferences(TempSavedInformation.getInstance().getPreferencesFile(), cButton.getText());
			if (customButtonsHBox.getChildren().size() == 1) {
				buttonAreaVBox.getChildren().remove(1);
				buttonAreaVBox.setSpacing(0);
			} else {
				customButtonsHBox.getChildren().remove(hbox);
			}
			customHHEntryTF.requestFocus();
		}
	}

	public void hideWindow() {
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainController.dialog.close();
		mainController.penAlreadyClicked = false;
		mainController.penImageView.setEffect(null);
	}

	public void checkHHEntryText() {
		try {
			int entryhour = Integer.parseInt(customHHEntryTF.getText());
			if ((entryhour < 10 && entryhour != 1) || entryhour >= 10) {
				if (customHHEntryTF.getText().length() == 1 && entryhour != 1) {
					customHHEntryTF.setText("0" + customHHEntryTF.getText());
				}
				customMMEntryTF.requestFocus();
			} else
				customHHEntryTF.requestFocus();
		} catch (Exception e) {
			customHHEntryTF.setText(null);
		}

	}

	public void checkHHExitText() {
		try {
			int exithour = Integer.parseInt(customHHExitTF.getText());
			if ((exithour < 10 && exithour != 1) || exithour >= 10) {
				customMMExitTF.requestFocus();
				if (customHHExitTF.getText().length() == 1 && exithour != 1) {
					customHHExitTF.setText("0" + customHHExitTF.getText());
				}
			} else
				customHHExitTF.requestFocus();
		} catch (Exception e) {
			customHHExitTF.setText(null);
		}
	}

	public void checkMMEntryText() {
		try {
			int entrytminute = Integer.parseInt(customMMEntryTF.getText());
			if (customMMEntryTF.getText().length() > 1) {
				if (entrytminute < 0 || entrytminute >= 59) {
					customMMEntryTF.setText(null);
					customMMEntryTF.requestFocus();
				} else
					customHHExitTF.requestFocus();
			}
		} catch (Exception e) {
			customMMEntryTF.setText(null);
		}

	}

	public void checkMMExitText() {
		try {
			int exitminute = Integer.parseInt(customMMExitTF.getText());
			if (customMMExitTF.getText().length() > 1) {
				if (exitminute < 0 || exitminute >= 59) {
					customMMExitTF.setText(null);
					customMMExitTF.requestFocus();
				} else
					createCustomBtn.requestFocus();
			}
		} catch (Exception e) {
			customMMExitTF.setText(null);
		}

	}
}
