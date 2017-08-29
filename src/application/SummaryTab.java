package application;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SummaryTab extends RecursiveTreeObject<SummaryTab> {

	public SimpleStringProperty key;
	public SimpleStringProperty value;

	public SummaryTab(String key, String value) {
		this.key = new SimpleStringProperty(key);
		this.value = new SimpleStringProperty(value);
	}

	public StringProperty keyProperty() {
		return key;
	}

	public void setKey(String keyProperty) {
		this.key.set(keyProperty);
	}

	public String getKey() {
		return key.get();
	}

	public StringProperty valueProperty() {
		return value;
	}

	public void setValue(String valueProperty) {
		this.value.set(valueProperty);
	}

	public String getValue() {
		return value.get();
	}
}
