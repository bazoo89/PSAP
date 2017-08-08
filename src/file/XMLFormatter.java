package file;

public class XMLFormatter {

	private XMLFormatter instance;

	public XMLFormatter getInstance() {
		if (instance == null) {
			instance = new XMLFormatter();
		}
		return instance;
	}

}
