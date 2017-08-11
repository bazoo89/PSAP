package application;

import java.io.IOException;
import java.net.URL;
import application.SalaryTab;
import file.ToolsForManageFile;
import file.entity.Salary;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFormattedTextField;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.EditorNodeBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.javafx.scene.control.skin.LabeledText;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utils.TempSavedInformation;


public class SalaryController implements Initializable {
    @FXML
    public TitledPane titledPane2017;
    @FXML
    public TitledPane titledPane2018;
    @FXML
    public JFXTreeTableView<SalaryTab> treeTableView;
    @FXML
    public TextField addYearTF;
    @FXML 
    public VBox lateralVBox;
    
	ObservableList<SalaryTab> salaries=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image noteImg=new Image("file:resources/icons/note.png");
		JFXButton janButton=new JFXButton("",new ImageView(noteImg));
		JFXButton febButton=new JFXButton("",new ImageView(noteImg));
		JFXButton marButton=new JFXButton("",new ImageView(noteImg));
		JFXButton aprButton=new JFXButton("",new ImageView(noteImg));
		JFXButton mayButton=new JFXButton("",new ImageView(noteImg));
		JFXButton junButton=new JFXButton("",new ImageView(noteImg));
		JFXButton julButton=new JFXButton("",new ImageView(noteImg));
		JFXButton augButton=new JFXButton("",new ImageView(noteImg));
		JFXButton sepButton=new JFXButton("",new ImageView(noteImg));
		JFXButton octButton=new JFXButton("",new ImageView(noteImg));
		JFXButton novButton=new JFXButton("",new ImageView(noteImg));
		JFXButton decButton=new JFXButton("",new ImageView(noteImg));
		HBox janButtonBox=new HBox();
		janButtonBox.getChildren().add(janButton);
		HBox febButtonBox=new HBox();
		febButtonBox.getChildren().add(febButton);
		HBox marButtonBox=new HBox();
		marButtonBox.getChildren().add(marButton);
		HBox aprButtonBox=new HBox();
		aprButtonBox.getChildren().add(aprButton);
		HBox mayButtonBox=new HBox();
		mayButtonBox.getChildren().add(mayButton);
		HBox junButtonBox=new HBox();
		junButtonBox.getChildren().add(junButton);
		HBox julButtonBox=new HBox();
		julButtonBox.getChildren().add(julButton);
		HBox augButtonBox=new HBox();
		augButtonBox.getChildren().add(augButton);
		HBox sepButtonBox=new HBox();
		sepButtonBox.getChildren().add(sepButton);
		HBox octButtonBox=new HBox();
		octButtonBox.getChildren().add(octButton);
		HBox novButtonBox=new HBox();
		novButtonBox.getChildren().add(novButton);
		HBox decButtonBox=new HBox();
		decButtonBox.getChildren().add(decButton);
		
		JFXTreeTableColumn<SalaryTab, String> month= new JFXTreeTableColumn<>("MONTH");
		month.setPrefWidth(100);
		month.setResizable(false);
		month.setSortable(false);
		month.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().monthProperty;
			}
		});
		JFXTreeTableColumn<SalaryTab, String> amount= new JFXTreeTableColumn<>("SALARY");
		amount.setPrefWidth(177);
		amount.setEditable(true);
		amount.setResizable(false);
		amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				ToolsForManageFile.getInstance().updateSalaryTabToDataFile();
				return param.getValue().getValue().amountProperty;
			}
		});
		amount.setCellFactory((TreeTableColumn<SalaryTab, String> param) -> new GenericEditableTreeTableCell<>(
	            new TextFieldEditorBuilder()));
		amount.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<SalaryTab,String>>() {
			@Override
			public void handle(javafx.scene.control.TreeTableColumn.CellEditEvent<SalaryTab, String> event) {
				TreeItem<SalaryTab> currentEditingSalary=treeTableView.getTreeItem(event.getTreeTablePosition().getRow());
				currentEditingSalary.getValue().setAmountProperty(event.getNewValue());
			}
		});
		
		JFXTreeTableColumn<SalaryTab, String> resHol= new JFXTreeTableColumn<>("RESIDUAL HOLIDAYS");
		resHol.setPrefWidth(185);
		resHol.setResizable(false);
		resHol.setSortable(false);
		resHol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().resHolProperty;
			}
		});
		JFXTreeTableColumn<SalaryTab, String> resPAR= new JFXTreeTableColumn<>("RESIDUAL PAR");
		resPAR.setPrefWidth(177);
		resPAR.setResizable(false);
		resPAR.setSortable(false);
		resPAR.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().resPARProperty;
			}
		});
		JFXTreeTableColumn<SalaryTab, String> notes= new JFXTreeTableColumn<>("");
		notes.setPrefWidth(50);
		notes.setResizable(false);
		notes.setSortable(false);
	

		salaries.add(new SalaryTab("Jan",null,null,null,janButtonBox));
		salaries.add(new SalaryTab("Feb",null,null,null,febButtonBox));
		salaries.add(new SalaryTab("Mar",null,null,null,marButtonBox));
		salaries.add(new SalaryTab("Apr",null,null,null,aprButtonBox));
		salaries.add(new SalaryTab("May",null,null,null,mayButtonBox));
		salaries.add(new SalaryTab("Jun",null,null,null,junButtonBox));
		salaries.add(new SalaryTab("Jul",null,null,null,julButtonBox));
		salaries.add(new SalaryTab("Aug",null,null,null,augButtonBox));
		salaries.add(new SalaryTab("Sep",null,null,null,sepButtonBox));
		salaries.add(new SalaryTab("Oct",null,null,null,octButtonBox));
		salaries.add(new SalaryTab("Nov",null,null,null,novButtonBox));
		salaries.add(new SalaryTab("Dec",null,null,null,decButtonBox));
		
		final TreeItem<SalaryTab> root=new RecursiveTreeItem<SalaryTab>(salaries,RecursiveTreeObject::getChildren);
		treeTableView.getColumns().setAll(month,amount,resHol,resPAR,notes);
		treeTableView.setRoot(root); 
		treeTableView.setShowRoot(false);
		
		addYearTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				 if (event.getCode().equals(KeyCode.ENTER)){
		            if(addYearTF.getText()!=null && addYearTF.getText().startsWith("20") && addYearTF.getText().matches("^\\d{4}$")){
			            	ObservableList<Node> nodes=lateralVBox.getChildren();
			            	TitledPane newTitledPane=new TitledPane();
			            	newTitledPane.setText(addYearTF.getText());
			            	newTitledPane.setExpanded(false);
			            	newTitledPane.setAnimated(false);
			            	int size=lateralVBox.getChildren().size();
			            	lateralVBox.getChildren().add(size-1,newTitledPane);
			            	newTitledPane.setOnMouseClicked(click ->{
			            		ArrayList<Salary> salaryList=ToolsForManageFile.getInstance().loadSalaryTabFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
			            		populateTableView(salaryList);
			            	});
			            	addYearTF.setText(null);
		            }
		        }
			}

		
		});
	}
	private void populateTableView(ArrayList<Salary> salaryList) {
		for (Salary salary : salaryList) {
			
		}
		
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
//	public void showSalaryDetails(){
//		if(titledPane2017.isFocused()){
//			if(titledPane2017.isExpanded()){
//				titledPane2018.setExpanded(false);
//				treeTableView.setVisible(true);
//			}
//			else
//				treeTableView.setVisible(false);
//		}
//		else{
//			if(titledPane2018.isExpanded()){
//				titledPane2017.setExpanded(false);
//				treeTableView.setVisible(true);
//			}
//			else
//				treeTableView.setVisible(false);
//		}
//
//	}

	
}
