package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSlider;

import file.ToolsForManageFile;
import file.entity.CustomButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import utils.Constants;
import utils.TempSavedInformation;

public class MainController implements Initializable {
	@FXML
	public StackPane mainStackPane;
	@FXML
	public StackPane buttonAreaStackPane;
	@FXML
	public ComboBox<String> hh_entryCB;
	@FXML
	public ComboBox<String> mm_entryCB;
	@FXML
	public ComboBox<String> hh_exitCB;
	@FXML
	public ComboBox<String> mm_exitCB;
	@FXML
	private Label workedHoursLabel;
	@FXML
	private Button saveBtn;
	@FXML
	public Button freeDaysBtn;
	@FXML
	public Button parBtn;
	@FXML
	public Button sicknessBtn;
	@FXML
	private JFXButton nextBtn;
	@FXML
	private JFXButton previousBtn;
	@FXML
	private AnchorPane parBorderPane;
	@FXML
	private AnchorPane freeBorderPane;
	@FXML
	private AnchorPane sickBorderPane;
	@FXML
	public JFXDatePicker calendar;
	@FXML
	public JFXDialogLayout customButtonDialogLayout;
	@FXML
	public JFXDialog customButtonDialog;
	@FXML
	public ImageView penImageView;
	@FXML
	public JFXButton allDayBtn;
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
	public JFXButton okBtn;
	@FXML
	public TextField customTF;
	@FXML
	public Button homeButton;
	@FXML
	public Button settingsButton;
	@FXML
	public Label parLabel;
	@FXML
	public Label freeDayLabel;
	@FXML
	public Label sickLabel;

	public static int sceneLength = 810;
	public static int sceneWidth = 755;
	public int dayTotalHour = 0;
	boolean penAlreadyClicked = false;
	JFXDialog dialog = null;

	ObservableList<String> hours = FXCollections.observableArrayList("08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");
	ObservableList<String> minutes = FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");
	ArrayList<CustomButton> savedCustomButtonsList = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hh_entryCB.setItems(hours);
		mm_entryCB.setItems(minutes);
		hh_exitCB.setItems(hours);
		mm_exitCB.setItems(minutes);
		calendar.setValue(LocalDate.now());
		Locale.setDefault(Locale.ITALY);
		calendar.setOnAction(action -> {
			reset();
			ToolsForManageFile.getInstance().loadHoursTabFromDataFile(TempSavedInformation.getInstance().getHourMonthFile(), calendar, hh_entryCB, mm_entryCB, hh_exitCB, mm_exitCB);
		});
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						Constants.setCalendarHolidays(this, item);
					}
				};
			}
		};
		calendar.setDayCellFactory(dayCellFactory);
		
		
		mainStackPane.setOnMouseClicked(event -> {
			if (penAlreadyClicked)
				penImageView.setEffect(null);
		});
	}

	//******* This method counts the worked hours and displays the result on the workedHours text field *******//
	public void countWorkedHours() {
		String result = "";
		int hh, mm;
		if (hh_exitCB.getValue() != null && hh_entryCB.getValue() != null && mm_exitCB.getValue() != null && mm_entryCB.getValue() != null) {
			if (Integer.parseInt(hh_entryCB.getValue()) < Integer.parseInt(hh_exitCB.getValue())) {
				if ((Integer.parseInt(hh_entryCB.getValue()) < 13 && Integer.parseInt(hh_exitCB.getValue()) > 13)
						|| (Integer.parseInt(hh_entryCB.getValue()) < 13 && Integer.parseInt(hh_exitCB.getValue()) >= 13 && Integer.parseInt(mm_exitCB.getValue()) > 0))
					hh = Integer.parseInt(hh_exitCB.getValue()) - Integer.parseInt(hh_entryCB.getValue()) - 1;
				else
					hh = Integer.parseInt(hh_exitCB.getValue()) - Integer.parseInt(hh_entryCB.getValue());
				mm = Integer.parseInt(mm_exitCB.getValue()) - Integer.parseInt(mm_entryCB.getValue());
				if (Integer.toString(hh).length() == 1)
					result = "0" + Integer.toString(hh);
				else
					result = Integer.toString(hh);
				if (Integer.toString(mm).length() == 1 && mm >= 0)
					result = result + ":0" + Integer.toString(mm);
				else {
					if (mm < 0) {
						mm = mm + 60;
						hh = hh - 1;
						if (Integer.toString(mm).length() == 1)
							result = Integer.toString(hh) + ":0" + Integer.toString(mm);
						else
							result = Integer.toString(hh) + ":" + Integer.toString(mm);
					} else
						result = result + ":" + Integer.toString(mm);
				}
				workedHoursLabel.setText(result);
				save();
			} else
				workedHoursLabel.setText("Warning!!!!!!");
		}
	}

//	public void chooseParHours() {
//		if (!parDialog.isVisible()) {
//			parDialog.show(parDialogLayout);
//			parSlider.setValue(0);
//			parResultTF.setText(Double.toString(parSlider.getValue()));
//			if (sickDialog.isVisible())
//				sickDialog.close();
//			if (freeDialog.isVisible())
//				freeDialog.close();
//		} else {
//			parDialog.setVisible(false);
//		}
//	}



	public void reset() {
		hh_entryCB.setValue(null);
		hh_exitCB.setValue(null);
		mm_entryCB.setValue(null);
		mm_exitCB.setValue(null);
		

		workedHoursLabel.setText("press save to calculate");
	}

	//******* goToMethods *******//

	public void goToSalary() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Salary.fxml"));
			Scene salaryScene = new Scene(root, sceneLength, sceneWidth);
			Main.primaryStage.setScene(salaryScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goNext() {
		reset();
		LocalDate nextDay = calendar.getValue().plusDays(1);
		calendar.setValue(nextDay);
		File userFile = TempSavedInformation.getInstance().getHourMonthFile();
		boolean loadedSuccessfully = ToolsForManageFile.getInstance().loadHoursTabFromDataFile(userFile, calendar, hh_entryCB, mm_entryCB, hh_exitCB, mm_exitCB);
		if (loadedSuccessfully) {
			countWorkedHours();
		}
	}

	public void goPrevious() {
		reset();
		LocalDate previousDay = calendar.getValue().minusDays(1);
		calendar.setValue(previousDay);
		File userFile = TempSavedInformation.getInstance().getHourMonthFile();
		boolean loadedSuccessfully = ToolsForManageFile.getInstance().loadHoursTabFromDataFile(userFile, calendar, hh_entryCB, mm_entryCB, hh_exitCB, mm_exitCB);
		if (loadedSuccessfully) {
			countWorkedHours();
		}
	}

	public void goToButtonArea() {
		DropShadow ds = new DropShadow(20, Color.RED);
		penAlreadyClicked = true;
		penImageView.setEffect(ds);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ShortcutArea.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ButtonAreaController baController = loader.getController();
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Shortcut"));
		content.setBody(root);
		dialog = new JFXDialog(mainStackPane, content, JFXDialog.DialogTransition.CENTER);
		savedCustomButtonsList = (ArrayList<CustomButton>) ToolsForManageFile.getInstance().loadCustomButtonPreferences(TempSavedInformation.getInstance().getPreferencesFile());
		if (savedCustomButtonsList.size() != 0) {
			for (CustomButton customButton : savedCustomButtonsList) {
				String[] text = customButton.getValue().split("-");
				String hourEntry = text[0];
				String hourExit = text[1];
				String hh_entry = hourEntry.split(":")[0];
				String mm_entry = hourEntry.split(":")[1];
				String hh_exit = hourExit.split(":")[0];
				String mm_exit = hourExit.split(":")[1];
				createHBox(baController, hh_entry, mm_entry, hh_exit, mm_exit);
			}
		}
		baController.createCustomBtn.setOnMouseClicked(mouseClick -> {
			String customHHEntry = baController.customHHEntryTF.getText();
			String customMMEntry = baController.customMMEntryTF.getText();
			String customHHExit = baController.customHHExitTF.getText();
			String customMMExit = baController.customMMExitTF.getText();
			if (!customHHEntry.equals("") && !customMMEntry.equals("") && !customHHExit.equals("") && !customMMExit.equals("")) {
				createHBox(baController, customHHEntry, customMMEntry, customHHExit, customMMExit);
			}
		});
		baController.cancelBtn.setOnMouseClicked(mouseClick -> {
			dialog.close();
			penAlreadyClicked = false;
			penImageView.setEffect(null);
		});
		dialog.show();
		baController.allDayBtn.setOnMouseClicked(mouseClick -> {
			dialog.close();
			hh_entryCB.setValue("09");
			mm_entryCB.setValue("00");
			hh_exitCB.setValue("18");
			mm_exitCB.setValue("00");
			penAlreadyClicked = false;
			penImageView.setEffect(null);
		});
		baController.onlyAfternoonBtn.setOnMouseClicked(mouseClick -> {
			dialog.close();
			hh_entryCB.setValue("14");
			mm_entryCB.setValue("00");
			hh_exitCB.setValue("18");
			mm_exitCB.setValue("00");
			penAlreadyClicked = false;
			penImageView.setEffect(null);
		});
		baController.onlyMorningBtn.setOnMouseClicked(mouseClick -> {
			dialog.close();
			hh_entryCB.setValue("09");
			mm_entryCB.setValue("00");
			hh_exitCB.setValue("13");
			mm_exitCB.setValue("00");
			penAlreadyClicked = false;
			penImageView.setEffect(null);
		});
		//		penImageView.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
		//			if (newValue) {
		//				penImageView.setEffect(ds);
		//			} else {
		//				penImageView.setEffect(null);
		//			}
		//		});
	}

	public void openParDialog() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SpecialHours.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Special Hours"));
		content.setBody(root);
		SpecialHoursController shController = loader.getController();
		dialog = new JFXDialog(mainStackPane, content, JFXDialog.DialogTransition.CENTER);
		dialog.show();
		shController.specialOkBtn.setOnMouseClicked(mouseClick -> {
		parLabel.setText(shController.resultTF.getText());
		dialog.close();
		shController.countTotalHours(parLabel);
//				ToolsForManageFile.getInstance().updateHolidayIntoDatafile(TempSavedInformation.getInstance().getHourMonthFile(),calendar.getValue().toString().replace("-", ""), Constants.Holidays, parLabel.getText());
			dialog.close();
			
		});
		shController.clearBtn.setOnMouseClicked(mouseClick -> {
			parLabel.setText(null);
			dialog.close();
		});
	}
	public void openFreeDayDialog() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SpecialHours.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Special Hours"));
		content.setBody(root);
		SpecialHoursController shController = loader.getController();
		dialog = new JFXDialog(mainStackPane, content, JFXDialog.DialogTransition.CENTER);
		dialog.show();
		shController.specialOkBtn.setOnMouseClicked(mouseClick -> {
		freeDayLabel.setText(shController.resultTF.getText());
		dialog.close();
		shController.countTotalHours(freeDayLabel);
//				ToolsForManageFile.getInstance().updateHolidayIntoDatafile(TempSavedInformation.getInstance().getHourMonthFile(),calendar.getValue().toString().replace("-", ""), Constants.Holidays, parLabel.getText());
			dialog.close();
			
		});
		shController.clearBtn.setOnMouseClicked(mouseClick -> {
			freeDayLabel.setText(null);
			dialog.close();
		});
	}
	public void openSicknessDialog() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SpecialHours.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Special Hours"));
		content.setBody(root);
		SpecialHoursController shController = loader.getController();
		dialog = new JFXDialog(mainStackPane, content, JFXDialog.DialogTransition.CENTER);
		dialog.show();
		shController.specialOkBtn.setOnMouseClicked(mouseClick -> {
		sickLabel.setText(shController.resultTF.getText());
		dialog.close();
		shController.countTotalHours(sickLabel);
//				ToolsForManageFile.getInstance().updateHolidayIntoDatafile(TempSavedInformation.getInstance().getHourMonthFile(),calendar.getValue().toString().replace("-", ""), Constants.Holidays, parLabel.getText());
			dialog.close();
			
		});
		shController.clearBtn.setOnMouseClicked(mouseClick -> {
			sickLabel.setText(null);
			dialog.close();
		});
	}
	private boolean createHBox(ButtonAreaController baController, String customHHEntry, String customMMEntry, String customHHExit, String customMMExit) {
		boolean successfullyCreation = false;
		HBox hbox = null;
		JFXButton customButton = new JFXButton(customHHEntry + ":" + customMMEntry + "-" + customHHExit + ":" + customMMExit);
		customButton.setOnMouseClicked(click -> {
			hh_entryCB.setValue(customHHEntry);
			mm_entryCB.setValue(customMMEntry);
			hh_exitCB.setValue(customHHExit);
			mm_exitCB.setValue(customMMExit);
			penAlreadyClicked = false;
			penImageView.setEffect(null);
			dialog.close();
		});
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("file:resources/icons/recycleBin.png"));
		JFXButton imageBtn = new JFXButton(null, imageView);
		imageBtn.setPrefSize(33, 33);
		imageBtn.setOnMouseClicked(click -> {
			baController.removeCustomBtn(customButton.getText());
		});
		if (baController.buttonAreaVBox.getChildren().size() == 2) {
			hbox = new HBox();
			hbox.setPrefHeight(50);
			baController.buttonAreaVBox.getChildren().add(1, hbox);
			baController.buttonAreaVBox.setSpacing(15);
		}
		hbox = (HBox) baController.buttonAreaVBox.getChildren().get(1);
		if (hbox.getChildren().size() < 3) {
			HBox customButtonHBox = new HBox();
			HBox.setMargin(customButtonHBox, new Insets(0, 0, 0, 5.5));
			customButtonHBox.getChildren().addAll(imageBtn, customButton);
			hbox.getChildren().add(customButtonHBox);
			if (hbox.getChildren().size() > savedCustomButtonsList.size()) {
				ToolsForManageFile.getInstance().saveCustomButtonPreferences(TempSavedInformation.getInstance().getPreferencesFile(), customButton.getText());
			}
		}
		return successfullyCreation;
	}

	///////////////////////////////////////

	//******* Utils Methods *******//

	public void save() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SpecialHours.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpecialHoursController shController = loader.getController();
		File userFile = TempSavedInformation.getInstance().getHourMonthFile();
		String hEntry = hh_entryCB.getValue() + ":" + mm_entryCB.getValue();
		String hExit = hh_exitCB.getValue() + ":" + mm_exitCB.getValue();
		String date = calendar.getValue().toString().replace("-", "");
		String holUsed = "0.0";
		String parUsed = "0.0";
		String sickUsed = "0.0";
		if (shController.resultTF != null && !shController.resultTF.getText().equals("") && !freeDayLabel.getText().equals("")) {
			holUsed = shController.resultTF.getText();
		}
		if (shController.resultTF  != null && !shController.resultTF.getText().equals("") && !parLabel.getText().equals("")) {
			parUsed = shController.resultTF.getText();
		}
		if (shController.resultTF  != null && !shController.resultTF.getText().equals("") && !sickLabel.getText().equals("")) {
			sickUsed = shController.resultTF.getText();
		}
		ToolsForManageFile.getInstance().updateHoursTabToDataFile(userFile, date, hEntry, hExit, holUsed, parUsed, sickUsed);

	}
	//*****************************//
}
