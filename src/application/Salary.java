package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;


public class Salary extends RecursiveTreeObject<Salary>{
	public SimpleStringProperty monthProperty;
	public SimpleStringProperty amountProperty;
    public SimpleStringProperty resHolProperty;
    public SimpleStringProperty resPARProperty;
    public HBox hbox;

    public Salary(String month, String amount,String resHol, String resPAR,HBox hbox) {
        this.monthProperty = new SimpleStringProperty(month);
        this.amountProperty = new SimpleStringProperty(amount);
        this.resHolProperty = new SimpleStringProperty(resHol);
        this.resPARProperty = new SimpleStringProperty(resPAR);
        this.hbox=hbox;
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
	public void setAmountProperty(String amount){
		this.amountProperty= new SimpleStringProperty(amount);
	}
	
}
