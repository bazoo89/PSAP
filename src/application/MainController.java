package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

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
	private TextField workedHoursTF;
	@FXML
	private Button ferieBtn;
	@FXML
	private Button parBtn;
	@FXML
	private Button malattiaBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private Button previousBtn;
	@FXML
	public JFXDatePicker calendar;
	
	public static int sceneLength=650;
	public static int sceneWidth=650;
	
	ObservableList<String> hours=(ObservableList<String>) FXCollections.observableArrayList("08","09","10","11","12","13"
			,"14","15","16","17","18","19","20","21");
	ObservableList<String> minutes=(ObservableList<String>) FXCollections.observableArrayList("00","05","10","15","20","25","30"
			,"35","40","45","50","55");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hh_entryCB.setItems(hours);
		mm_entryCB.setItems(minutes);
		hh_exitCB.setItems(hours);
		mm_exitCB.setItems(minutes);
		calendar.setValue(LocalDate.now());
		
	}
	
	//******* This method counts the worked hours and displays the result on the workedHours text field *******//
	public void countWorkedHours(){
		String result="";
		if(hh_exitCB.getValue()!=null && hh_entryCB.getValue()!=null  && 
				mm_exitCB.getValue()!=null && mm_entryCB.getValue()!=null ){
			if(Integer.parseInt(hh_entryCB.getValue())<Integer.parseInt(hh_exitCB.getValue())){
				int hh=Integer.parseInt(hh_exitCB.getValue())-Integer.parseInt(hh_entryCB.getValue());
				int mm=Integer.parseInt(mm_exitCB.getValue())-Integer.parseInt(mm_entryCB.getValue());
				if(Integer.toString(hh).length()==1)
					result="0"+Integer.toString(hh);
				else
					result=Integer.toString(hh);
				if(Integer.toString(mm).equals("0"))
					result=result+":0"+Integer.toString(mm);
				else{
					if(mm<0)
					result=result+":"+Integer.toString(mm);	
				}
				workedHoursTF.setText(result);
				}
			else
				workedHoursTF.setText("Warning!");
		}
	}
	public void chooseSpecialDayHours(){
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/application/SpecialDays.fxml"));
		Scene specialDaysScene = new Scene(root,300,140);
		Main.primaryStage.setScene(specialDaysScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//******* goToMethods *******//
	public void goToSalary(){
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Salary.fxml"));
		Scene salaryScene = new Scene(root,sceneLength,sceneWidth);
		Main.primaryStage.setScene(salaryScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void goNext(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene initScene=new Scene(root,Main.sceneLength,Main.sceneWidth);
			Main.primaryStage.setScene(initScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void goPrevious(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene initScene=new Scene(root,Main.sceneLength,Main.sceneWidth);
			Main.primaryStage.setScene(initScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	///////////////////////////////////////
}
