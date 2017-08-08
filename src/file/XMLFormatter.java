package file;

public class XMLFormatter {

	private XMLFormatter instance;

	public XMLFormatter getInstance() {
		if (instance == null) {
			instance = new XMLFormatter();
		}
		return instance;
	}

	//	public File getDateFile() {
	//		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	//		String filePath = prefs.get("filePath", null);
	//		if (filePath != null) {
	//			return new File(filePath);
	//		} else {
	//			return null;
	//		}
	//	}
	//
	//	public File getMonthsSalaryFile() {
	//		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	//		String filePath = prefs.get("filePath", null);
	//		if (filePath != null) {
	//			return new File(filePath);
	//		} else {
	//			return null;
	//		}
	//	}
}
