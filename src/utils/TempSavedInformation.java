package utils;

import java.io.File;

public class TempSavedInformation {

	private static TempSavedInformation instance;
	private String name = null;
	private String lastname = null;
	private File hourMonthFile = null;
	private File preferencesFile = null;
	private File isLogged = null;

	public static TempSavedInformation getInstance() {
		if (instance == null) {
			instance = new TempSavedInformation();
		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public File getHourMonthFile() {
		return hourMonthFile;
	}

	public void setHourMonthFile(File hourMonthFile) {
		this.hourMonthFile = hourMonthFile;
	}

	public File getPreferencesFile() {
		return preferencesFile;
	}

	public void setPreferencesFile(File preferencesFile) {
		this.preferencesFile = preferencesFile;
	}

	public File getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(File isLogged) {
		this.isLogged = isLogged;
	}
}
