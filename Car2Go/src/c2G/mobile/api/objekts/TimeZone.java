package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class TimeZone {

	String DSTSavings;
	String ID;
	boolean dirty;
	String displayName;
	TimeZone lastRuleInstance;
	String rawOffset;
	
	public TimeZone(String dSTSavings, String iD, boolean dirty,
			String displayName, TimeZone lastRuleInstance, String rawOffset) {
		super();
		DSTSavings = dSTSavings;
		ID = iD;
		this.dirty = dirty;
		this.displayName = displayName;
		this.lastRuleInstance = lastRuleInstance;
		this.rawOffset = rawOffset;
	}
	
	public TimeZone(String dSTSavings, String iD, String dirty,
			String displayName, TimeZone lastRuleInstance, String rawOffset) {
		super();
		DSTSavings = dSTSavings;
		ID = iD;
		this.dirty = Boolean.parseBoolean(dirty);
		this.displayName = displayName;
		this.lastRuleInstance = lastRuleInstance;
		this.rawOffset = rawOffset;
	}

	public String getDSTSavings() {
		return DSTSavings;
	}

	public void setDSTSavings(String dSTSavings) {
		DSTSavings = dSTSavings;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public TimeZone getLastRuleInstance() {
		return lastRuleInstance;
	}

	public void setLastRuleInstance(TimeZone lastRuleInstance) {
		this.lastRuleInstance = lastRuleInstance;
	}

	public String getRawOffset() {
		return rawOffset;
	}

	public void setRawOffset(String rawOffset) {
		this.rawOffset = rawOffset;
	}
	
	
}
