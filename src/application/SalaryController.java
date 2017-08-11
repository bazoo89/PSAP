package application;

import java.io.IOException;
import java.net.URL;
import application.Salary;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
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


public class SalaryController implements Initializable {
    @FXML
    public TitledPane titledPane2017;
    @FXML
    public TitledPane titledPane2018;
    @FXML
    public JFXTreeTableView<Salary> treeTableView;
    @FXML
    public JFXTreeTableColumn<Salary,String> month;
    @FXML
    public JFXTreeTableColumn<Salary,String> amount;
    @FXML
    public JFXTreeTableColumn<Salary,String> resHol;
    @FXML
    public JFXTreeTableColumn<Salary,String> resPAR;
    @FXML
    public TextField addYearTF;
    @FXML 
    public VBox lateralVBox;
    @FXML 
    public VBox buttonVBox;
    
	ObservableList<Salary> salaries=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXTreeTableColumn<Salary, String> month= new JFXTreeTableColumn<>("MONTH");
		month.setPrefWidth(100);
		month.setResizable(false);
		month.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().monthProperty;
			}
		});
		JFXTreeTableColumn<Salary, String> amount= new JFXTreeTableColumn<>("SALARY");
		amount.setPrefWidth(177);
		amount.setEditable(true);
		amount.setResizable(false);
		amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().amountProperty;
			}
		});
		amount.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
		amount.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Salary,String>>() {
			@Override
			public void handle(javafx.scene.control.TreeTableColumn.CellEditEvent<Salary, String> event) {
				TreeItem<Salary> currentEditingSalary=treeTableView.getTreeItem(event.getTreeTablePosition().getRow());
				currentEditingSalary.getValue().setAmountProperty(event.getNewValue());
			}
		});
		
		JFXTreeTableColumn<Salary, String> resHol= new JFXTreeTableColumn<>("RESIDUAL HOLIDAYS");
		resHol.setPrefWidth(185);
		resHol.setResizable(false);
		resHol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().resHolProperty;
			}
		});
		JFXTreeTableColumn<Salary, String> resPAR= new JFXTreeTableColumn<>("RESIDUAL PAR");
		resPAR.setPrefWidth(177);
		resPAR.setResizable(false);
		resPAR.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Salary,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Salary, String> param) {
				return param.getValue().getValue().resPARProperty;
			}
		});
		
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
		
		final TreeItem<Salary> root=new RecursiveTreeItem<Salary>(salaries,RecursiveTreeObject::getChildren);
		treeTableView.getColumns().setAll(month,amount,resHol,resPAR);
		treeTableView.setRoot(root); 
		treeTableView.setShowRoot(false);
		
		addYearTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				 if (event.getCode().equals(KeyCode.ENTER)){
		            if(addYearTF.getText().startsWith("2") && addYearTF.getText().matches("^\\d{4}$")){
//		            	if(){ condizione per limitare l'add dei titled pane
			            	ObservableList<Node> nodes=lateralVBox.getChildren();
			            	TitledPane newTitledPane=new TitledPane();
			            	newTitledPane.setText(addYearTF.getText());
			            	newTitledPane.setExpanded(false);
			            	int size=lateralVBox.getChildren().size();
			            	lateralVBox.getChildren().add(size-1,newTitledPane);
			            	addYearTF.setText(null);
//			            }
		            }
		        }
			}
		});
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
		janButtonBox.setMargin(janButton, new Insets(60,0, 0, 0));
		HBox febbuttonBox=new HBox();
		febbuttonBox.getChildren().add(febButton);
		febbuttonBox.setMargin(febButton, new Insets(20, 0, 0, 0));
		HBox marButtonBox=new HBox();
		marButtonBox.getChildren().add(marButton);
		marButtonBox.setMargin(marButton, new Insets(20, 0, 0, 0));
		HBox aprButtonBox=new HBox();
		aprButtonBox.getChildren().add(aprButton);
		aprButtonBox.setMargin(aprButton, new Insets(20,0, 0, 0));
		HBox mayButtonBox=new HBox();
		mayButtonBox.getChildren().add(mayButton);
		mayButtonBox.setMargin(mayButton, new Insets(20, 0, 0, 0));
		HBox junButtonBox=new HBox();
		junButtonBox.getChildren().add(junButton);
		junButtonBox.setMargin(junButton, new Insets(20, 0, 0, 0));
		HBox julButtonBox=new HBox();
		julButtonBox.getChildren().add(julButton);
		julButtonBox.setMargin(julButton, new Insets(20,0, 0, 0));
		HBox augButtonBox=new HBox();
		augButtonBox.getChildren().add(augButton);
		augButtonBox.setMargin(augButton, new Insets(20, 0, 0, 0));
		HBox sepButtonBox=new HBox();
		sepButtonBox.getChildren().add(sepButton);
		sepButtonBox.setMargin(sepButton, new Insets(20, 0, 0, 0));
		HBox octButtonBox=new HBox();
		octButtonBox.getChildren().add(octButton);
		octButtonBox.setMargin(octButton, new Insets(20, 0, 0, 0));
		HBox novButtonBox=new HBox();
		novButtonBox.getChildren().add(novButton);
		novButtonBox.setMargin(novButton, new Insets(20,0, 0, 0));
		HBox decButtonBox=new HBox();
		decButtonBox.getChildren().add(decButton);
		decButtonBox.setMargin(decButton, new Insets(20, 0, 0, 0));
		
		buttonVBox.getChildren().addAll(janButtonBox,febbuttonBox,marButtonBox,
				aprButtonBox,mayButtonBox,junButtonBox,julButtonBox,augButtonBox,sepButtonBox,
				octButtonBox,novButtonBox,decButtonBox);
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
				treeTableView.setVisible(true);
			}
			else
				treeTableView.setVisible(false);
		}
		else{
			if(titledPane2018.isExpanded()){
				titledPane2017.setExpanded(false);
				treeTableView.setVisible(true);
			}
			else
				treeTableView.setVisible(false);
		}

	}

	
}
