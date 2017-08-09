package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSlider;

import file.ToolsForManageFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {

	@FXML
	private ComboBox<String> hh_entryCB;
	@FXML
	private ComboBox<String> mm_entryCB;
	@FXML
	private ComboBox<String> hh_exitCB;
	@FXML
	private ComboBox<String> mm_exitCB;
	@FXML
	private Label workedHoursLabel;
	@FXML
	private Button saveBtn;
	@FXML
	private Button freeDaysBtn;
	@FXML
	private Button parOKBtn;
	@FXML
	private Button sickOKBtn;
	@FXML
	private Button freeOKBtn;
	@FXML
	private Button parClearBtn;
	@FXML
	private Button freeClearBtn;
	@FXML
	private Button sickClearBtn;
	@FXML
	private Button parBtn;
	@FXML
	private Button sicknessBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private Button previousBtn;
	@FXML
	private TextField parResultTF;
	@FXML
	private TextField freeResultTF;
	@FXML
	private TextField sickResultTF;
	@FXML
	private CheckBox parIsHalfHour;
	@FXML
	private CheckBox freeIsHalfHour;
	@FXML
	private CheckBox sickIsHalfHour;
	@FXML
	private AnchorPane parBorderPane;
	@FXML
	private AnchorPane freeBorderPane;
	@FXML
	private AnchorPane sickBorderPane;
	@FXML
	private Label freeHoursLabel;
	@FXML
	private Label parHoursLabel;
	@FXML
	private Label sickHoursLabel;
	@FXML
	public JFXDatePicker calendar;
	@FXML
	public Circle freeCircle;
	@FXML
	public Circle sickCircle;
	@FXML
	public Circle parCircle;
	@FXML
	public JFXDialogLayout customButtonDialogLayout;
	@FXML
	public JFXDialog customButtonDialog;
	@FXML
	public JFXDialog parDialog;
	@FXML
	public JFXDialog freeDialog;
	@FXML
	public JFXDialog sickDialog;
	@FXML
	public JFXDialogLayout parDialogLayout;
	@FXML
	public JFXDialogLayout freeDialogLayout;
	@FXML
	public JFXDialogLayout sickDialogLayout;
	@FXML
	public JFXSlider parSlider;
	@FXML
	public JFXSlider freeSlider;
	@FXML
	public JFXSlider sickSlider;
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

	public static int sceneLength = 650;
	public static int sceneWidth = 430;
	public int dayTotalHour = 0;
	public static boolean customBtn1State;
	public static boolean customBtn2State;
	public static boolean customBtn3State;
	public static int createdButtons;

	ObservableList<String> hours = FXCollections.observableArrayList("08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");
	ObservableList<String> minutes = FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hh_entryCB.setItems(hours);
		mm_entryCB.setItems(minutes);
		hh_exitCB.setItems(hours);
		mm_exitCB.setItems(minutes);
		calendar.setValue(LocalDate.now());
		parSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				if (newValue == null) {
					parResultTF.setText("");
					return;
				}
				float decimal = newValue.floatValue() - (int) newValue.floatValue();
				if ((decimal > 0 && decimal < 0.5) || (decimal > 0.5 && decimal < 1)) {
					parResultTF.setText((int) newValue.floatValue() + "");
				} else {
					if (decimal == 0)
						parResultTF.setText((int) newValue.floatValue() + "");
					else if (decimal == 0.5)
						parResultTF.setText((int) newValue.floatValue() + ".5");
					else
						parResultTF.setText(newValue.floatValue() + "");
				}
			}
		});
		freeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				if (newValue == null) {
					freeResultTF.setText("");
					return;
				}
				float decimal = newValue.floatValue() - (int) newValue.floatValue();
				if ((decimal > 0 && decimal < 0.5) || (decimal > 0.5 && decimal < 1)) {
					freeResultTF.setText((int) newValue.floatValue() + "");
				} else {
					if (decimal == 0)
						freeResultTF.setText((int) newValue.floatValue() + "");
					else if (decimal == 0.5)
						freeResultTF.setText((int) newValue.floatValue() + ".5");
					else
						freeResultTF.setText(newValue.floatValue() + "");
				}
			}
		});
		sickSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				if (newValue == null) {
					sickResultTF.setText("");
					return;
				}
				float decimal = newValue.floatValue() - (int) newValue.floatValue();
				if ((decimal > 0 && decimal < 0.5) || (decimal > 0.5 && decimal < 1)) {
					sickResultTF.setText((int) newValue.floatValue() + "");
				} else {
					if (decimal == 0)
						sickResultTF.setText((int) newValue.floatValue() + "");
					else if (decimal == 0.5)
						sickResultTF.setText((int) newValue.floatValue() + ".5");
					else
						sickResultTF.setText(newValue.floatValue() + "");
				}
			}
		});
		customBtn1State = false;
		customBtn1State = false;
		customBtn1State = false;
		createdButtons = 0;
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

	public void chooseParHours() {
		if (!parDialog.isVisible()) {
			parDialog.show(parDialogLayout);
			parSlider.setValue(0);
			parResultTF.setText(Double.toString(parSlider.getValue()));
			if (sickDialog.isVisible())
				sickDialog.close();
			if (freeDialog.isVisible())
				freeDialog.close();
		} else {
			parDialog.setVisible(false);
		}
	}

	public void chooseFreeHours() {
		if (!freeDialog.isVisible()) {
			freeDialog.show(freeDialogLayout);
			freeSlider.setValue(0);
			freeResultTF.setText(Double.toString(freeSlider.getValue()));
			if (sickDialog.isVisible())
				sickDialog.close();
			if (parDialog.isVisible())
				parDialog.close();
		}
	}

	public void chooseSickHours() {
		if (!sickDialog.isVisible()) {
			sickDialog.show(sickDialogLayout);
			sickSlider.setValue(0);
			sickResultTF.setText(Double.toString(sickSlider.getValue()));
			if (parDialog.isVisible())
				parDialog.close();
			if (freeDialog.isVisible())
				freeDialog.close();
		}
	}

	public void countSpecialHours() {
		int specialHours = 0;
		if (parDialog.isVisible()) {
			parDialog.setVisible(false);
			parCircle.setVisible(true);
			parHoursLabel.setVisible(true);
			parHoursLabel.setText(parResultTF.getText());
			countTotalHours(parHoursLabel);
		} else if (freeDialog.isVisible()) {
			freeDialog.setVisible(false);
			freeCircle.setVisible(true);
			freeHoursLabel.setVisible(true);
			freeHoursLabel.setText(freeResultTF.getText());
			countTotalHours(freeHoursLabel);
		} else if (sickDialog.isVisible()) {
			sickDialog.setVisible(false);
			sickCircle.setVisible(true);
			sickHoursLabel.setVisible(true);
			sickHoursLabel.setText(sickResultTF.getText());
			countTotalHours(sickHoursLabel);
		} else {
		}
	}

	public void clear() {
		if (parDialog.isVisible()) {
			parSlider.setValue(0);
			if (parCircle.isVisible())
				parCircle.setVisible(false);
			if (parHoursLabel.isVisible()) {
				parHoursLabel.setText(null);
				parHoursLabel.setVisible(false);
			}
		} else if (freeDialog.isVisible()) {
			freeSlider.setValue(0);
			if (freeCircle.isVisible())
				freeCircle.setVisible(false);
			if (freeHoursLabel.isVisible()) {
				freeHoursLabel.setText(null);
				freeHoursLabel.setVisible(false);
			}
		} else if (sickDialog.isVisible()) {
			sickSlider.setValue(0);
			if (sickCircle.isVisible())
				sickCircle.setVisible(false);
			if (sickHoursLabel.isVisible()) {
				sickHoursLabel.setText(null);
				sickHoursLabel.setVisible(false);
			}
		} else {
		}
	}

	public void countTotalHours(Label label) {
		//		int specialHour=Integer.parseInt(label.getText());
		//		int standardHour=Integer.parseInt(workedHoursTF.getText());
		//		try {
		//			dayTotalHour=specialHour+standardHour;
		//			if(dayTotalHour>=8){
		//				hh_entryCB.setEditable(false);
		//				hh_exitCB.setEditable(false);
		//				mm_entryCB.setEditable(false);
		//				mm_exitCB.setEditable(false);
		//			}
		//		} catch (Exception e) {
		//			System.out.println(e.getMessage());
		//		}
	}

	public void reset() {
		hh_entryCB.setValue(null);
		hh_exitCB.setValue(null);
		mm_entryCB.setValue(null);
		mm_exitCB.setValue(null);
		parCircle.setVisible(false);
		freeCircle.setVisible(false);
		sickCircle.setVisible(false);
		parHoursLabel.setText(null);
		parHoursLabel.setVisible(false);
		freeHoursLabel.setText(null);
		freeHoursLabel.setVisible(false);
		sickHoursLabel.setText(null);
		sickHoursLabel.setVisible(false);
		if (parDialog.isVisible())
			parDialog.close();
		if (sickDialog.isVisible())
			sickDialog.close();
		if (freeDialog.isVisible())
			freeDialog.close();
		workedHoursLabel.setText("press save to calculate");
	}

	//******* goToMethods *******//

	public void goToSalary() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Salary.fxml"));
			Scene salaryScene = new Scene(root, sceneLength, sceneWidth);
			Main.primaryStage.setScene(salaryScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goNext() {
		reset();
		calendar.setValue(calendar.getValue().plusDays(1));
	}

	public void goPrevious() {
		reset();
		calendar.setValue(calendar.getValue().minusDays(1));
	}

	public void goToButtonArea() {
		//		if (!customButtonDialog.isVisible()) {
		//			customButtonDialog.show(customButtonDialogLayout);
		//		} else {
		//			customButtonDialog.setVisible(false);
		//		}
	}
	///////////////////////////////////////

	//******* Utils Methods *******//

	public void save() {
		File userFile = new File(Main.pathFile);
		String hEntry = hh_entryCB.getValue() + ":" + mm_entryCB.getValue();
		String hExit = hh_exitCB.getValue() + ":" + mm_exitCB.getValue();
		String date = calendar.getValue().toString().replace("-", "");
		ToolsForManageFile.getInstance().updateHoursTabToDataFile(userFile, date, hEntry, hExit);

	}

	public void removeBtn1() {
		manageCustomBtn(customBtn1, recycleBtn1, false, true);
		customBtn1State = false;
		createdButtons--;
	}

	public void removeBtn2() {
		manageCustomBtn(customBtn2, recycleBtn2, false, true);
		customBtn2State = false;
		createdButtons--;
	}

	public void removeBtn3() {
		manageCustomBtn(customBtn3, recycleBtn3, false, true);
		customBtn3State = false;
		createdButtons--;
	}

	public void createCustom() {
		if (!customTF.getText().equals("")) {
			if (createdButtons == 0) {
				manageCustomBtn(customBtn1, recycleBtn1, true, false);
				customBtn1State = true;
				createdButtons++;
			} else if (createdButtons == 1) {
				if (customBtn1State) {
					manageCustomBtn(customBtn2, recycleBtn2, true, false);
					customBtn2State = true;
					createdButtons++;
				} else if (customBtn2State) {
					manageCustomBtn(customBtn1, recycleBtn1, true, false);
					customBtn1State = true;
					createdButtons++;
				} else {
					manageCustomBtn(customBtn1, recycleBtn1, true, false);
					customBtn1State = true;
					createdButtons++;
				}
			} else if (createdButtons == 2) {
				if (customBtn1State && customBtn2State) {
					manageCustomBtn(customBtn3, recycleBtn3, true, false);
					customBtn3State = true;
					createdButtons++;
				} else if (customBtn1State && customBtn3State) {
					manageCustomBtn(customBtn2, recycleBtn2, true, false);
					customBtn2State = true;
					createdButtons++;
				} else if (customBtn2State && customBtn3State) {
					manageCustomBtn(customBtn1, recycleBtn1, true, false);
					customBtn1State = true;
					createdButtons++;
				}
			}
		}
	}

	public void manageCustomBtn(JFXButton btn, Button recBtn, boolean visible, boolean disabled) {
		btn.setText(customTF.getText());
		btn.setDisable(disabled);
		btn.setVisible(visible);
		recBtn.setDisable(disabled);
		recBtn.setVisible(visible);
	}

	public void manageCustomChoice() {
		System.out.println("ciao");
	}

	//*****************************//
}
