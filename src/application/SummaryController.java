package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SummaryController implements Initializable {

	@FXML
	public JFXButton closeButton;
	@FXML
	public Label holidaysUsed;
	@FXML
	public Label parUsed;
	@FXML
	public Label sicknessUsed;
	@FXML
	public Label workedDays;
	@FXML
	public Label workedHours;
	@FXML
	public Label extraTime;
	//	@FXML
	//	public JFXTreeTableView<SummaryTab> summaryTableView;
	//	@FXML
	//	public AnchorPane anchorPane;

	public FXMLLoader loader = null;
	public SalaryController salaryController = null;
	//	public JFXTreeTableColumn<SummaryTab, String> keyColumn;
	//	public JFXTreeTableColumn<SummaryTab, String> valueColumn;
	//	TreeItem<SummaryTab> rootSummary = null;
	//	ObservableList<SummaryTab> summaries = FXCollections.observableArrayList();

	//	@Override
	//	public void initialize(URL location, ResourceBundle resources) {
	//		//		loader = new FXMLLoader(getClass().getResource("/application/Salary.fxml"));
	//		//		salaryController = loader.getController();
	//		//		summaryTableView.setStyle("-fx-background-color: rgba(255.0, 0.0, 0.0, 0.3);");
	//		ArrayList<Month> salaryList = ToolsForManageFile.getInstance().loadSalaryTabFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
	//		populateTableView(salaryList);
	//
	//		keyColumn = new JFXTreeTableColumn<>("KEY");
	//		keyColumn.setPrefWidth(200);
	//		keyColumn.setResizable(false);
	//		keyColumn.setSortable(false);
	//		keyColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SummaryTab, String>, ObservableValue<String>>() {
	//			@Override
	//			public ObservableValue<String> call(CellDataFeatures<SummaryTab, String> param) {
	//				return param.getValue().getValue().key;
	//			}
	//		});
	//		valueColumn = new JFXTreeTableColumn<>("VALUE");
	//		valueColumn.setPrefWidth(200);
	//		valueColumn.setResizable(false);
	//		valueColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().value);
	//
	//		rootSummary = new RecursiveTreeItem<SummaryTab>(summaries, RecursiveTreeObject::getChildren);
	//		summaryTableView.getColumns().setAll(keyColumn, valueColumn);
	//		summaryTableView.setRoot(rootSummary);
	//		summaryTableView.setShowRoot(false);
	//		summaryTableView.setFixedCellSize(32);
	//	}

	//	private void populateTableView(ArrayList<Month> salaryList) {
	//		for (Month salary : salaryList) {
	//			summaries.add(new SummaryTab(salary.getId(), salary.getId()));
	//		}
	//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
