package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class ReservationTime {

	String firstDayOfWeek;
	String gregorianChange;
	boolean lenient;
	String minimalDaysInFirstWeek;
	String time;
	String timeInMillis;
	TimeZone timeZone;
	
	public ReservationTime(String firstDayOfWeek, String gregorianChange,
			boolean lenient, String minimalDaysInFirstWeek, String time,
			String timeInMillis, TimeZone timeZone) {
		this.firstDayOfWeek = firstDayOfWeek;
		this.gregorianChange = gregorianChange;
		this.lenient = lenient;
		this.minimalDaysInFirstWeek = minimalDaysInFirstWeek;
		this.time = time;
		this.timeInMillis = timeInMillis;
		this.timeZone = timeZone;
	}
	
	public ReservationTime(String firstDayOfWeek, String gregorianChange,
			String lenient, String minimalDaysInFirstWeek, String time,
			String timeInMillis, TimeZone timeZone) {
		this.firstDayOfWeek = firstDayOfWeek;
		this.gregorianChange = gregorianChange;
		this.lenient = Boolean.parseBoolean(lenient);
		this.minimalDaysInFirstWeek = minimalDaysInFirstWeek;
		this.time = time;
		this.timeInMillis = timeInMillis;
		this.timeZone = timeZone;
	}
	
	public String getFirstDayOfWeek() {
		return firstDayOfWeek;
	}
	public void setFirstDayOfWeek(String firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}
	public String getGregorianChange() {
		return gregorianChange;
	}
	public void setGregorianChange(String gregorianChange) {
		this.gregorianChange = gregorianChange;
	}
	public boolean isLenient() {
		return lenient;
	}
	public void setLenient(boolean lenient) {
		this.lenient = lenient;
	}
	public String getMinimalDaysInFirstWeek() {
		return minimalDaysInFirstWeek;
	}
	public void setMinimalDaysInFirstWeek(String minimalDaysInFirstWeek) {
		this.minimalDaysInFirstWeek = minimalDaysInFirstWeek;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimeInMillis() {
		return timeInMillis;
	}
	public void setTimeInMillis(String timeInMillis) {
		this.timeInMillis = timeInMillis;
	}
	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	
}
