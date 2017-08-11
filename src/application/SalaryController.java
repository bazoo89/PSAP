package application;

import java.io.IOException;
import java.net.URL;
import application.Salary;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;


public class SalaryController implements Initializable {
    @FXML
    public TitledPane titledPane2017;
    @FXML
    public TitledPane titledPane2018;
    @FXML
    public JFXTreeTableView<Salary> tableView;
    @FXML
    public TableColumn<Salary,String> month;
    @FXML
    public TableColumn<Salary,String> amount;
    @FXML
    public TableColumn<Salary,String> resHol;
    @FXML
    public TableColumn<Salary,String> resPAR;
	
	
	ObservableList<Salary> salaries=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		month.setCellValueFactory(new PropertyValueFactory<Salary,String>("month"));
		month.setCellFactory(TextFieldTableCell.forTableColumn());
		month.setOnEditCommit(
		    new EventHandler<CellEditEvent<Salary, String>>() {
		        @Override
		        public void handle(CellEditEvent<Salary, String> t) {
		            ((Salary) t.getTableView().getItems().get(
		                t.getTablePosition().getRow())
		                ).setMonth(t.getNewValue());
		        }
		    }
		);
		amount.setCellValueFactory(new PropertyValueFactory<Salary,String>("amount"));
		amount.setCellFactory(TextFieldTableCell.forTableColumn());
		amount.setOnEditCommit(
			    new EventHandler<CellEditEvent<Salary, String>>() {
			        @Override
			        public void handle(CellEditEvent<Salary, String> t) {
			            ((Salary) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setAmount(t.getNewValue());
			        }
			    }
			);
		resHol.setCellValueFactory(new PropertyValueFactory<Salary,String>("resHol"));
		resHol.setCellFactory(TextFieldTableCell.forTableColumn());
		resHol.setOnEditCommit(
			    new EventHandler<CellEditEvent<Salary, String>>() {
			        @Override
			        public void handle(CellEditEvent<Salary, String> t) {
			            ((Salary) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setResHol(t.getNewValue());
			        }
			    }
			);
		resPAR.setCellValueFactory(new PropertyValueFactory<Salary,String>("resPAR"));
		resPAR.setCellFactory(TextFieldTableCell.forTableColumn());
		resPAR.setOnEditCommit(
			    new EventHandler<CellEditEvent<Salary, String>>() {
			        @Override
			        public void handle(CellEditEvent<Salary, String> t) {
			            ((Salary) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setResPAR(t.getNewValue());
			        }
			    }
			);

//		tableView.setItems(salaries);
		salaries.add(new Salary("Jan",null,null,null));
		salaries.add(new Salary("Feb",null,null,null));
		salaries.add(new Salary("Mar",null,null,null));
		salaries.add(new Salary("Apr",null,null,null));
		salaries.add(new Salary("May",null,null,null));
		salaries.add(new Salary("Jun",null,null,null));
		salaries.add(new Salary("Jul",null,null,null));
		salaries.add(new Salary("Aug",null,null,null));
		salaries.add(new Salary("Sep",null,null,null));
		salaries.add(new Salary("Oct",null,null,null));
		salaries.add(new Salary("Nov",null,null,null));
		salaries.add(new Salary("Dec",null,null,null));
	}
	public void goBack(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene initScene=new Scene(root,UserController.sceneLength,UserController.sceneWidth);
			Main.primaryStage.setScene(initScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showSalaryDetails(){
		if(titledPane2017.isFocused()){
			if(titledPane2017.isExpanded()){
				titledPane2018.setExpanded(false);
				tableView.setVisible(true);
			}
			else
				tableView.setVisible(false);
		}
		else{
			if(titledPane2018.isExpanded()){
				titledPane2017.setExpanded(false);
				tableView.setVisible(true);
			}
			else
				tableView.setVisible(false);
		}

	}
	
}
