package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import file.ToolsForManageFile;
import file.entity.Hour;
import file.entity.Month;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.TempSavedInformation;

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

	public FXMLLoader loader = null;
	public SalaryController salaryController = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loader = new FXMLLoader(getClass().getResource("/application/Salary.fxml"));
		salaryController = loader.getController();
		//		summaryTableView.setStyle("-fx-background-color: rgba(255.0, 0.0, 0.0, 0.3);");
		ArrayList<Month> monthList = ToolsForManageFile.getInstance().getMonthsFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
		ArrayList<Hour> hoursList = ToolsForManageFile.getInstance().getHoursFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());

		for (Hour hour : hoursList) {

		}
	}
}
