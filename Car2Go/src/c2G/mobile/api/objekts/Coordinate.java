package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class Coordinate {

	double latitude;
	double longitude;
	
	public Coordinate(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Coordinate(String latitude, String longitude){
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
	}
	
	public Coordinate(Coordinate coordinate){
		this.latitude = coordinate.latitude;
		this.longitude = coordinate.longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		String result = "Latitude: " + this.latitude + " | Longitude: " + this.longitude;
		return result;
	}
	
}
