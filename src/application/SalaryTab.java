package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalaryTab extends RecursiveTreeObject<SalaryTab> {
	public SimpleStringProperty monthProperty;
	public SimpleStringProperty amountProperty;
	public SimpleStringProperty resHolProperty;
	public SimpleStringProperty resPARProperty;
	public ObjectProperty<JFXButton> notesProperty;

	public SalaryTab(String month, String amount, String resHol, String resPAR, JFXButton notesProperty) {
		this.monthProperty = new SimpleStringProperty(month);
		this.amountProperty = new SimpleStringProperty(amount);
		this.resHolProperty = new SimpleStringProperty(resHol);
		this.resPARProperty = new SimpleStringProperty(resPAR);
		this.notesProperty = new SimpleObjectProperty<JFXButton>(notesProperty);
	}

	public StringProperty monthProperty() {
		return monthProperty;
	}

	public StringProperty amountProperty() {
		return amountProperty;
	}

	public StringProperty resHolProperty() {
		return resHolProperty;
	}

	public StringProperty resPARProperty() {
		return resPARProperty;
	}

	public ObjectProperty<JFXButton> notesProperty() {
		return notesProperty;
	}

	public void setAmountProperty(String amount) {
		this.amountProperty = new SimpleStringProperty(amount);
	}

}
