package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class CanceledBooking {
	
	String cancelFee;
	String cancelFeeCurrency;
	boolean cancelFeeExists;
	
	public CanceledBooking(String cancelFee, String cancelFeeCurrency,
			boolean cancelFeeExists) {
		this.cancelFee = cancelFee;
		this.cancelFeeCurrency = cancelFeeCurrency;
		this.cancelFeeExists = cancelFeeExists;
	}
	
	public CanceledBooking(String cancelFee, String cancelFeeCurrency,
			String cancelFeeExists) {
		this.cancelFee = cancelFee;
		this.cancelFeeCurrency = cancelFeeCurrency;
		this.cancelFeeExists = Boolean.parseBoolean(cancelFeeExists);
	}

	public String getCancelFee() {
		return cancelFee;
	}

	public void setCancelFee(String cancelFee) {
		this.cancelFee = cancelFee;
	}

	public String getCancelFeeCurrency() {
		return cancelFeeCurrency;
	}

	public void setCancelFeeCurrency(String cancelFeeCurrency) {
		this.cancelFeeCurrency = cancelFeeCurrency;
	}

	public boolean isCancelFeeExists() {
		return cancelFeeExists;
	}

	public void setCancelFeeExists(boolean cancelFeeExists) {
		this.cancelFeeExists = cancelFeeExists;
	}
	
}
