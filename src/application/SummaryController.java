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
	public int month = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//		loader = new FXMLLoader(getClass().getResource("/application/Salary.fxml"));
		//		salaryController = loader.getController();
		//		month = Integer.parseInt(salaryController.month.getId());
		//		summaryTableView.setStyle("-fx-background-color: rgba(255.0, 0.0, 0.0, 0.3);");
	}

	private boolean compareMonth(String stringDate, int insertedMonth) {
		String stringMonth = stringDate.substring(4, 6);
		if (stringMonth.startsWith("0")) {
			stringMonth = stringMonth.replace("0", "");
		}
		int monthId = Integer.parseInt(stringMonth);
		return insertedMonth == monthId;
	}

	public void populateView(Month clickedMonth) {
		month = Integer.parseInt(clickedMonth.getId());
		ArrayList<Month> monthList = ToolsForManageFile.getInstance().getMonthsFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());
		ArrayList<Hour> hoursList = ToolsForManageFile.getInstance().getHoursFromDataFile(TempSavedInformation.getInstance().getHourMonthFile());

		double usedHolidays = 0.0;
		double usedPARs = 0.0;
		double usedSickness = 0.0;
		int workedDays = 0;
		double workedHours = 0.0;
		double extraTime = 0.0;

		for (Hour hour : hoursList) {
			if (compareMonth(hour.getId(), month)) {
				usedHolidays += Double.parseDouble(hour.getHolidaysHoursUsed());
				usedPARs += Double.parseDouble(hour.getParHoursUsed());
				usedSickness += Double.parseDouble(hour.getSicknessHoursUsed());
			}
		}

		holidaysUsed.setText(String.valueOf(usedHolidays));
		parUsed.setText(String.valueOf(usedPARs));
		sicknessUsed.setText(String.valueOf(usedSickness));

	}
}
