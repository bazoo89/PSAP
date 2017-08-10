package application;

import java.io.IOException;
import java.net.URL;
import application.Salary;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;


public class ButtonAreaController implements Initializable {
	@FXML
	public JFXButton allDayBtn;
	@FXML
	public GridPane gridPane;
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
	
	private static boolean customBtn1State;
	private static boolean customBtn2State;
	private static boolean customBtn3State;
	private static int createdButtons;
	public FXMLLoader loader=null;
	public MainController mainController=null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		customBtn1State = false;
		customBtn1State = false;
		customBtn1State = false;
		createdButtons = 0;
		loader= new FXMLLoader(getClass().getResource("/application/Main.fxml"));
		mainController = loader.getController();
	}
	public void removeBtn1() {
		manageCustomBtn(customBtn1, recycleBtn1, false, true);
		customBtn1State = false;
		createdButtons--;
		customHHEntryTF.requestFocus();
	}

	public void removeBtn2() {
		manageCustomBtn(customBtn2, recycleBtn2, false, true);
		customBtn2State = false;
		createdButtons--;
		customHHEntryTF.requestFocus();
	}

	public void removeBtn3() {
		manageCustomBtn(customBtn3, recycleBtn3, false, true);
		customBtn3State = false;
		createdButtons--;
		customHHEntryTF.requestFocus();
	}

	public void createCustom() {
		if(customMMExitTF.getText().length()==1){
			customMMExitTF.setText(customMMExitTF.getText()+"0");
		}
		//gestire salvataggio su XML
		if (!customHHEntryTF.getText().equals("") && !customMMEntryTF.getText().equals("") && 
				!customHHExitTF.getText().equals("") &&!customMMExitTF.getText().equals("") ) {
			if (createdButtons == 0) {
					boolean r=manageCustomBtn(customBtn1, recycleBtn1, true, false);
					if(r){
						customBtn1State = true;
						createdButtons++;
					}
			} else if (createdButtons == 1) {
				if (customBtn1State) {
					boolean r=manageCustomBtn(customBtn2, recycleBtn2, true, false);
					if(r){
						customBtn2State = true;
						createdButtons++;
					}
				} else if (customBtn2State) {
					boolean r=manageCustomBtn(customBtn1, recycleBtn1, true, false);
					if(r){
						customBtn1State = true;
						createdButtons++;
					}				
				} else {
					boolean r=manageCustomBtn(customBtn1, recycleBtn1, true, false);
					if(r){
					customBtn1State = true;
					createdButtons++;
					}
				}
			} else if (createdButtons == 2) {
				if (customBtn1State && customBtn2State) {
					boolean r=manageCustomBtn(customBtn3, recycleBtn3, true, false);
					if(r){
						customBtn3State = true;
						createdButtons++;
					}
				} else if (customBtn1State && customBtn3State) {
					boolean r=manageCustomBtn(customBtn2, recycleBtn2, true, false);
					if(r){
						customBtn2State = true;
						createdButtons++;
					}
				} else if (customBtn2State && customBtn3State) {
					boolean r=manageCustomBtn(customBtn1, recycleBtn1, true, false);
					if(r){
						customBtn1State = true;
						createdButtons++;
					}	
				}
			}
		}
	}
	public boolean manageCustomBtn(JFXButton btn, Button recBtn, boolean visible, boolean disabled) {
		boolean result=false;
		if(Integer.parseInt(customHHExitTF.getText())>Integer.parseInt(customHHEntryTF.getText())){
			btn.setText(customHHEntryTF.getText()+":"+customMMEntryTF.getText()+"-"+customHHExitTF.getText()+":"+customMMExitTF.getText());
			btn.setDisable(disabled);
			btn.setVisible(visible);
			recBtn.setDisable(disabled);
			recBtn.setVisible(visible);
			result=true;
		}
		else
			customHHExitTF.setText(null);
		return result;
	}
	public void hideWindow(){
		 Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainController.dialog.close();
			mainController.penAlreadyClicked=false;
			mainController.penImageView.setEffect(null);
			
	}

	public void checkHHEntryText(){
		try {
			int entryhour=Integer.parseInt(customHHEntryTF.getText());
			if((entryhour<10 && entryhour!=1)|| entryhour>=10 ){
				if(customHHEntryTF.getText().length()==1 && entryhour!=1){
					customHHEntryTF.setText("0"+customHHEntryTF.getText());
				}
				customMMEntryTF.requestFocus();
			}
			else
				customHHEntryTF.requestFocus();
		} catch (Exception e) {
			customHHEntryTF.setText(null);
		}
		
	}
	public void checkHHExitText(){
		try {
		int exithour=Integer.parseInt(customHHExitTF.getText());
		if((exithour<10 && exithour!=1)|| exithour>=10 ){
			customMMExitTF.requestFocus();
			if(customHHExitTF.getText().length()==1 && exithour!=1){
				customHHExitTF.setText("0"+customHHExitTF.getText());
			}
		}
		else
			customHHExitTF.requestFocus();
		} catch (Exception e) {
			customHHExitTF.setText(null);
		}
	}
	public void checkMMEntryText(){
		try {
		int entrytminute=Integer.parseInt(customMMEntryTF.getText());
			if(customMMEntryTF.getText().length()>1){
				if(entrytminute<0 || entrytminute>=59){
					customMMEntryTF.setText(null);
					customMMEntryTF.requestFocus();
				}
				else
					customHHExitTF.requestFocus();
				}
		} catch (Exception e) {
			customMMEntryTF.setText(null);
		}
		
	}
	public void checkMMExitText(){
		try {
			int exitminute=Integer.parseInt(customMMExitTF.getText());
			if(customMMExitTF.getText().length()>1){
				if(exitminute<0 ||exitminute>=59){
					customMMExitTF.setText(null);
					customMMExitTF.requestFocus();
				}
				else
					createCustomBtn.requestFocus();
			}	
		} catch (Exception e) {
			customMMExitTF.setText(null);
		}

	}
}
