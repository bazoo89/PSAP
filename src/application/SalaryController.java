package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import file.ToolsForManageFile;
import file.entity.Month;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import utils.TempSavedInformation;

public class SalaryController implements Initializable {
	@FXML
	public TitledPane titledPane2017;
	@FXML
	public TitledPane titledPane2018;
	@FXML
	public StackPane stackPane;
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
	public JFXTreeTableColumn<SalaryTab, JFXButton> notes;
	JFXDialog dialog = null;
	public Month clickedMonth;

	private Image summary = new Image("file:resources/icons/summary.png");
	TreeItem<SalaryTab> root = null;

	ObservableList<SalaryTab> salaries = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Month> salaryList = ToolsForManageFile.getInstance().getMonthsFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
		//		Image noteImg = new Image("file:resources/icons/note.png");
		//		JFXButton janButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton febButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton marButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton aprButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton mayButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton junButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton julButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton augButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton sepButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton octButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton novButton = new JFXButton("", new ImageView(noteImg));
		//		JFXButton decButton = new JFXButton("", new ImageView(noteImg));
		//		HBox janButtonBox = new HBox();
		//		janButtonBox.getChildren().add(janButton);
		//		HBox febButtonBox = new HBox();
		//		febButtonBox.getChildren().add(febButton);
		//		HBox marButtonBox = new HBox();
		//		marButtonBox.getChildren().add(marButton);
		//		HBox aprButtonBox = new HBox();
		//		aprButtonBox.getChildren().add(aprButton);
		//		HBox mayButtonBox = new HBox();
		//		mayButtonBox.getChildren().add(mayButton);
		//		HBox junButtonBox = new HBox();
		//		junButtonBox.getChildren().add(junButton);
		//		HBox julButtonBox = new HBox();
		//		julButtonBox.getChildren().add(julButton);
		//		HBox augButtonBox = new HBox();
		//		augButtonBox.getChildren().add(augButton);
		//		HBox sepButtonBox = new HBox();
		//		sepButtonBox.getChildren().add(sepButton);
		//		HBox octButtonBox = new HBox();
		//		octButtonBox.getChildren().add(octButton);
		//		HBox novButtonBox = new HBox();
		//		novButtonBox.getChildren().add(novButton);
		//		HBox decButtonBox = new HBox();
		//		decButtonBox.getChildren().add(decButton);

		month = new JFXTreeTableColumn<>("MONTH");
		month.setPrefWidth(100);
		month.setMaxWidth(100);
		month.setMinWidth(100);
		month.setResizable(false);
		month.setSortable(false);
		month.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().monthProperty;
			}
		});
		amount = new JFXTreeTableColumn<>("SALARY");
		amount.setPrefWidth(177);
		amount.setMaxWidth(177);
		amount.setMinWidth(177);
		amount.setEditable(true);
		amount.setResizable(false);
		amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().amountProperty;
			}
		});
		amount.setCellFactory((TreeTableColumn<SalaryTab, String> param) -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
		amount.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<SalaryTab, String>>() {
			@Override
			public void handle(javafx.scene.control.TreeTableColumn.CellEditEvent<SalaryTab, String> event) {
				TreeItem<SalaryTab> currentEditingSalary = treeTableView.getTreeItem(event.getTreeTablePosition().getRow());
				SalaryTab selectedRow = currentEditingSalary.getValue();
				selectedRow.setAmountProperty(event.getNewValue());
				ToolsForManageFile.getInstance().updateSalaryTabToDataFile(TempSavedInformation.getInstance().getHourMonthFile(), selectedRow.monthProperty.get(), selectedRow.amountProperty.get());
			}
		});

		resHol = new JFXTreeTableColumn<>("RESIDUAL HOLIDAYS");
		resHol.setPrefWidth(185);
		resHol.setMaxWidth(185);
		resHol.setMinWidth(185);
		resHol.setResizable(false);
		resHol.setSortable(false);
		resHol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().resHolProperty;
			}
		});
		resPAR = new JFXTreeTableColumn<>("RESIDUAL PAR");
		resPAR.setPrefWidth(177);
		resPAR.setMaxWidth(177);
		resPAR.setMinWidth(177);
		resPAR.setResizable(false);
		resPAR.setSortable(false);
		resPAR.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SalaryTab, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<SalaryTab, String> param) {
				return param.getValue().getValue().resPARProperty;
			}
		});
		notes = new JFXTreeTableColumn<>("");
		notes.setPrefWidth(50);
		notes.setMinWidth(50);
		notes.setMaxWidth(50);
		notes.setResizable(false);
		notes.setSortable(false);
		notes.setCellValueFactory(new Callback<CellDataFeatures<SalaryTab, JFXButton>, ObservableValue<JFXButton>>() {
			@Override
			public ObservableValue<JFXButton> call(CellDataFeatures<SalaryTab, JFXButton> param) {
				return param.getValue().getValue().notesProperty;
			}

		});
		notes.setCellFactory(new Callback<TreeTableColumn<SalaryTab, JFXButton>, TreeTableCell<SalaryTab, JFXButton>>() {
			@Override
			public TreeTableCell<SalaryTab, JFXButton> call(TreeTableColumn<SalaryTab, JFXButton> param) {
				TreeTableCell<SalaryTab, JFXButton> cell = new TreeTableCell<SalaryTab, JFXButton>() {
					@Override
					public void updateItem(JFXButton item, boolean empty) {
						if (item != null) {
							DropShadow ds = new DropShadow(20, Color.RED);
							ImageView imageview = new ImageView();
							imageview.setFitHeight(15);
							imageview.setFitWidth(15);
							imageview.setImage(summary);
							item.setGraphic(imageview);
							setGraphic(item);
							item.setOnMouseClicked(event -> {
								item.setEffect(ds);
								showSummary(item, getIndex());
							});
						}
					}

					private void showSummary(JFXButton summary, int monthId) {
						clickedMonth = salaryList.get(monthId);
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Summary.fxml"));
						Parent root = null;
						try {
							root = loader.load();
						} catch (IOException e) {
							e.printStackTrace();
						}
						SummaryController sumController = loader.getController();
						sumController.populateView(clickedMonth);
						JFXDialogLayout content = new JFXDialogLayout();

						HBox hboxTitle = new HBox();
						Label text = new Label("Summary :: " + clickedMonth.getMonthName().toUpperCase());
						text.setStyle("-fx-font-style: italic; -fx-fill: red; -fx-font-size: 30px;");
						hboxTitle.getChildren().add(text);
						content.setHeading(hboxTitle);
						content.setBody(root);
						stackPane.setOnMouseClicked(mouseClicked -> {
							summary.setEffect(null);
						});
						dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.RIGHT);
						//						content.setStyle("-fx-background-color: rgba(0.0, 0.0, 0.0, 0.6);");
						dialog.setStyle("-fx-background-color: rgba(0.0, 0.0, 0.0, 0.2);");
						sumController.closeButton.setOnMouseClicked(mouseClick -> {
							dialog.close();
							summary.setEffect(null);
						});
						dialog.show();
					}
				};
				return cell;
			}
		});

		root = new RecursiveTreeItem<SalaryTab>(salaries, RecursiveTreeObject::getChildren);
		treeTableView.getColumns().setAll(month, amount, resHol, resPAR, notes);
		treeTableView.setRoot(root);
		treeTableView.setShowRoot(false);
		treeTableView.setFixedCellSize(53);

		addYearTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					if (addYearTF.getText() != null && addYearTF.getText().startsWith("20") && addYearTF.getText().matches("^\\d{4}$")) {
						ObservableList<Node> nodes = lateralVBox.getChildren();
						TitledPane newTitledPane = new TitledPane();
						newTitledPane.setText(addYearTF.getText());
						newTitledPane.setExpanded(false);
						newTitledPane.setAnimated(false);
						int size = lateralVBox.getChildren().size();
						lateralVBox.getChildren().add(size - 1, newTitledPane);
						newTitledPane.setOnMouseClicked(click -> {
							ArrayList<Month> salaryList = ToolsForManageFile.getInstance().getMonthsFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
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
			salaries.add(new SalaryTab(salary.getMonthName(), salary.getSalary(), salary.getHolidaysRes(), salary.getParRes(), new JFXButton()));
		}
	}

	public void goBack() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene initScene = new Scene(root, Main.sceneWidth, Main.sceneHeight);
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
