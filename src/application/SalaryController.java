package application;

import java.io.IOException;
import java.net.URL;
import application.SalaryTab;
import file.ToolsForManageFile;
import file.entity.Month;

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
    public JFXTreeTableColumn<SalaryTab, String> month;
    public JFXTreeTableColumn<SalaryTab, String> amount;
    public JFXTreeTableColumn<SalaryTab, String> resHol;
    public JFXTreeTableColumn<SalaryTab, String> resPAR;
    public JFXTreeTableColumn<SalaryTab, String> notes;
    TreeItem<SalaryTab> root = null;
    
	ObservableList<SalaryTab> salaries=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Month> salaryList=ToolsForManageFile.getInstance().loadSalaryTabFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
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
		
		month= new JFXTreeTableColumn<>("MONTH");
		month.setPrefWidth(100);
		month.setResizable(false);
		month.setSortable(false);
		month.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().monthProperty;
			}
		});
		amount= new JFXTreeTableColumn<>("SALARY");
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
		
		resHol= new JFXTreeTableColumn<>("RESIDUAL HOLIDAYS");
		resHol.setPrefWidth(185);
		resHol.setResizable(false);
		resHol.setSortable(false);
		resHol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().resHolProperty;
			}
		});
		resPAR= new JFXTreeTableColumn<>("RESIDUAL PAR");
		resPAR.setPrefWidth(177);
		resPAR.setResizable(false);
		resPAR.setSortable(false);
		resPAR.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().resPARProperty;
			}
		});
		notes= new JFXTreeTableColumn<>("");
		notes.setPrefWidth(50);
		notes.setResizable(false);
		notes.setSortable(false);
		root=new RecursiveTreeItem<SalaryTab>(salaries,RecursiveTreeObject::getChildren);
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
			            		ArrayList<Month> salaryList=ToolsForManageFile.getInstance().loadSalaryTabFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
			            		populateTableView(salaryList);
			            	});
			            	addYearTF.setText(null);
		            }
		        }
			}
		});
		populateTableView(salaryList);
	}
	private void populateTableView(ArrayList<Month> salaryList) {
		for (Month salary : salaryList) {
			salaries.add(new SalaryTab(salary.getId(), salary.getSalary(), salary.getHolidaysRes(), salary.getParRes(), null));
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
