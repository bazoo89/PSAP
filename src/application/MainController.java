package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
	private Button freeDaysBtn;
	@FXML
	private Button parOKBtn;
	@FXML
	private Button sickOKBtn;
	@FXML
	private Button freeOKBtn;
	@FXML
	private Button parBtn;
	@FXML
	private Button sicknessBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private Button previousBtn;
	@FXML
	private Button parAugmentBtn;
	@FXML
	private Button parReduceBtn;
	@FXML
	private Button freeAugmentBtn;
	@FXML
	private Button freeReduceBtn;
	@FXML
	private Button sickAugmentBtn;
	@FXML
	private Button sickReduceBtn;
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
	
	public static int sceneLength=650;
	public static int sceneWidth=650;
	public int dayTotalHour=0;
	
	ObservableList<String> hours=(ObservableList<String>) FXCollections.observableArrayList("08","09","10","11","12","13"
			,"14","15","16","17","18","19","20");
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
				int hh=Integer.parseInt(hh_exitCB.getValue())-Integer.parseInt(hh_entryCB.getValue())-1;
				int mm=Integer.parseInt(mm_exitCB.getValue())-Integer.parseInt(mm_entryCB.getValue());
				if(Integer.toString(hh).length()==1)
					result="0"+Integer.toString(hh);
				else
					result=Integer.toString(hh);
				if(Integer.toString(mm).length()==1 && mm>=0)
					result=result+":0"+Integer.toString(mm);
				else{
					if(mm<0){
						mm=mm+60;
						hh=hh-1;
						if(Integer.toString(mm).length()==1)
							result=Integer.toString(hh)+":0"+Integer.toString(mm);	
						else
							result=Integer.toString(hh)+":"+Integer.toString(mm);
					}
					else
						result=result+":"+Integer.toString(mm);
				}
				workedHoursLabel.setText(result);
				}
			else
				workedHoursLabel.setText("Warning!!!!!!");
		}
	}
	public void chooseParHours(){
		parDialog.show(parDialogLayout);
		parResultTF.setText("0");
		if(sickDialog.isVisible())
			sickDialog.close();
		if(freeDialog.isVisible())
			freeDialog.close();
	}
	public void chooseFreeHours(){
		freeDialog.show(freeDialogLayout);
		freeResultTF.setText("0");
		if(sickDialog.isVisible())
			sickDialog.close();
		if(parDialog.isVisible())
			parDialog.close();
	}
	public void chooseSickHours(){
		sickDialog.show(sickDialogLayout);
		sickResultTF.setText("0");
		if(parDialog.isVisible())
			parDialog.close();
		if(freeDialog.isVisible())
			freeDialog.close();
	}
	public void countSpecialHours(){
		int specialHours=0;
		if(parDialog.isVisible()){
			parDialog.setVisible(false);
			parCircle.setVisible(true);
			parHoursLabel.setVisible(true);
			if(parIsHalfHour.isSelected())
				parHoursLabel.setText(parResultTF.getText()+",5");
			else
				parHoursLabel.setText(parResultTF.getText());
			countTotalHours(parHoursLabel);			
		}
		else if(freeDialog.isVisible()){
			freeDialog.setVisible(false);
			freeCircle.setVisible(true);
			freeHoursLabel.setVisible(true);
			if(freeIsHalfHour.isSelected())
				freeHoursLabel.setText(freeResultTF.getText()+",5");
			else
				freeHoursLabel.setText(freeResultTF.getText());
			countTotalHours(freeHoursLabel);
		}
		else if(sickDialog.isVisible()){
			sickDialog.setVisible(false);
			sickCircle.setVisible(true);
			sickHoursLabel.setVisible(true);
			if(sickIsHalfHour.isSelected())
				sickHoursLabel.setText(sickResultTF.getText()+",5");
			else
				sickHoursLabel.setText(sickResultTF.getText());
			countTotalHours(sickHoursLabel);
		}
		else{}
	}
	public void countTotalHours(Label label){
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
	public void augment(){
		if(parDialog.isVisible()){
			manageAugment(parResultTF);
		}
		else if(freeDialog.isVisible()){
			manageAugment(freeResultTF);
		}
		else if(sickDialog.isVisible()){
			manageAugment(sickResultTF);
		}
		else{}
		
	}
	public void reduce(){
		if(parDialog.isVisible()){
			manageReduce(parResultTF);
		}
		else if(freeDialog.isVisible()){
			manageReduce(freeResultTF);
		}
		else if(sickDialog.isVisible()){
			manageReduce(sickResultTF);
		}
		else{}
	}
	public void manageAugment(TextField tf){
		int hours=Integer.parseInt(tf.getText());
		hours=hours+1;
		hours=Math.min(hours, 8);
		tf.setText(Integer.toString(hours));
	}
	public void manageReduce(TextField tf){
		int hours=Integer.parseInt(tf.getText());
		hours=hours-1;
		hours=Math.max(hours, 0);
		tf.setText(Integer.toString(hours));
	}
	public void reset(){
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
		if(parDialog.isVisible())
			parDialog.close();
		if(sickDialog.isVisible())
			sickDialog.close();
		if(freeDialog.isVisible())
			freeDialog.close();
		workedHoursLabel.setText("press save to calculate");
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
		reset();
		calendar.setValue(calendar.getValue().plusDays(1));
//			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
//			Scene initScene=new Scene(root,Main.sceneLength,Main.sceneWidth);
//			Main.primaryStage.setScene(initScene);
	}
	public void goPrevious(){
		reset();
		calendar.setValue(calendar.getValue().minusDays(1));
//			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
//			Scene initScene=new Scene(root,Main.sceneLength,Main.sceneWidth);
//			Main.primaryStage.setScene(initScene);

	}
	///////////////////////////////////////
}
