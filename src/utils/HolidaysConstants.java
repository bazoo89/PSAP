package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

import javafx.scene.control.DateCell;
import javafx.scene.paint.Color;

public class HolidaysConstants {

	public final static String gennaio_1 = "0101";
	public final static String gennaio_6 = "0106";
	public final static String aprile_25 = "0325";
	public final static String maggio_1 = "0501";
	public final static String giugno_2 = "0602";
	public final static String agosto_15 = "0815";
	public final static String novembre_1 = "1101";
	public final static String dicembre_8 = "1208";
	public final static String dicembre_25 = "1225";
	public final static String dicembre_26 = "1226";

	public final static String easter2017 = "0416";
	public final static String easter_monday_2017 = "0417";
	public final static String easter2018 = "0401";
	public final static String easter_monday_2018 = "0402";
	public final static String year2017 = "2017";
	public final static String year2018 = "2018";
	public final static String year2019 = "2019";

	public String getEasterDate(String year) {
		if (year.equals("2018")) {
			return "0401";
		}
		return "";
	}

	public static void setCalendarHolidays(DateCell calendar, LocalDate localDate) {
		DayOfWeek day = DayOfWeek.from(localDate);
		if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
			calendar.setTextFill(Color.RED);
			calendar.setDisable(true);
		}
		Year year = Year.from(localDate);
		MonthDay monthDay = MonthDay.from(localDate);
		String monthDayString = monthDay.toString().replace("-", "");
		switch (year.toString()) {
		case year2017:
			switch (monthDayString) {
			case gennaio_1:
			case gennaio_6:
			case aprile_25:
			case maggio_1:
			case giugno_2:
			case agosto_15:
			case novembre_1:
			case dicembre_8:
			case dicembre_25:
			case dicembre_26:
			case easter2017:
			case easter_monday_2017:
				calendar.setTextFill(Color.RED);
				calendar.setDisable(true);
				break;
			default:
				break;
			}
		default:
			break;
		}
		switch (year.toString()) {
		case year2018:
			switch (monthDayString) {
			case gennaio_1:
			case gennaio_6:
			case aprile_25:
			case maggio_1:
			case giugno_2:
			case agosto_15:
			case novembre_1:
			case dicembre_8:
			case dicembre_25:
			case dicembre_26:
			case easter2018:
			case easter_monday_2018:
				calendar.setTextFill(Color.RED);
				calendar.setDisable(true);
				break;
			default:
				break;
			}
		default:
			break;
		}
	}
}
