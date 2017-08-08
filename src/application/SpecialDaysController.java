package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;


public class SpecialDaysController implements Initializable {
	
	
	@FXML
	private Button augmentBtn;
	@FXML
	private Button reduceBtn;
	@FXML
	private TextField resultTF;
	@FXML
	private CheckBox isHalfHour;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resultTF.setText("0");
	}
	public void augment(){
		int hours=Integer.parseInt(resultTF.getText());
		System.out.println(hours);
		hours=hours+1;
		hours=Math.min(hours, 8);
		resultTF.setText(Integer.toString(hours));
		System.out.println(Integer.toString(hours));
	}
	public void reduce(){
		int hours=Integer.parseInt(resultTF.getText());
		hours=hours-1;
		hours=Math.max(hours,0);
		resultTF.setText(Integer.toString(hours));
	}
}
