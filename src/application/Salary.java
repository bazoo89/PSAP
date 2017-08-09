package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Salary {
	private final SimpleStringProperty month;
    private final SimpleStringProperty amount;
    private final SimpleStringProperty resHol;
    private final SimpleStringProperty resPAR;

    public Salary(String month, String amount,String resHol, String resPAR) {
        this.month = new SimpleStringProperty(month);
        this.amount = new SimpleStringProperty(amount);
        this.resHol = new SimpleStringProperty(resHol);
        this.resPAR = new SimpleStringProperty(resPAR);
    }

	public String getMonth() {
		return month.get();
	}
	public void setMonth(String m) {
		month.set(m);
	}
	public String getAmount() {
		return amount.get();
	}
	public void setAmount(String a) {
		amount.set(a);
	}
	public String getResHol() {
		return resHol.get();
	}
	public void setResHol(String rh) {
		resHol.set(rh);
	}
	public String getResPAR() {
		return resPAR.get();
	}
	public void setResPAR(String rp) {
		resPAR.set(rp);
	}
//	public StringProperty monthProperty() {
//		return month;
//	}
//	public StringProperty amountProperty() {
//		return amount;
//	}
//	public StringProperty resHolProperty() {
//		return resHol;
//	}
//	public StringProperty resPARProperty() {
//		return resPAR;
//	}
	
}
