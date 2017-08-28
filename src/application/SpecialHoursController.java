package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;

import file.ToolsForManageFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.TempSavedInformation;

public class SpecialHoursController implements Initializable {
	@FXML
	public JFXButton specialOkBtn;
	@FXML
	public JFXButton clearBtn;
	@FXML
	public JFXSlider slider;
	@FXML 
	public TextField resultTF;



	public FXMLLoader loader = null;
	public MainController mainController = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
		mainController = loader.getController();
		slider.setValue(0);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				slider.setValue(newValue.doubleValue());
				if (newValue == null) {
					resultTF.setText("");
					return;
				}
				float decimal = newValue.floatValue() - (int) newValue.floatValue();
				if ((decimal > 0 && decimal < 0.5) || (decimal > 0.5 && decimal < 1)) {
					resultTF.setText((int) newValue.floatValue() + "");
				} else {
					if (decimal == 0) {
						resultTF.setText((int) newValue.floatValue() + "");
						slider.setValue((int) newValue.floatValue());
					} else if (decimal == 0.5) {
						resultTF.setText((int) newValue.floatValue() + ".5");
						slider.setValue((int) newValue.floatValue() + 0.5);
					} else {
						resultTF.setText(newValue.floatValue() + "");
						slider.setValue(newValue.floatValue());
					}
				}
			}
		});
		
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
}
