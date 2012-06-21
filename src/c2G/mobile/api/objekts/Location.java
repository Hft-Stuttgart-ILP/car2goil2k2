package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class Location {
	
    String countryCode;
    String defaultLanguage;
    String locationId;
    String locationName;
    Coordinate centerCoordinates;
    Coordinate lowerRight;
    Coordinate upperLeft;
    String timezone;
    
	public Location(String countryCode, String defaultLanguage,
			String locationId, String locationName,
			Coordinate centerCoordinates, Coordinate lowerRight,
			Coordinate upperLeft, String timezone) {
		this.countryCode = countryCode;
		this.defaultLanguage = defaultLanguage;
		this.locationId = locationId;
		this.locationName = locationName;
		this.centerCoordinates = centerCoordinates;
		this.lowerRight = lowerRight;
		this.upperLeft = upperLeft;
		this.timezone = timezone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Coordinate getCenterCoordinates() {
		return centerCoordinates;
	}

	public void setCenterCoordinates(Coordinate centerCoordinates) {
		this.centerCoordinates = centerCoordinates;
	}

	public Coordinate getLowerRight() {
		return lowerRight;
	}

	public void setLowerRight(Coordinate lowerRight) {
		this.lowerRight = lowerRight;
	}

	public Coordinate getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Coordinate upperLeft) {
		this.upperLeft = upperLeft;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}
