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
	
	public Coordinate getCoordinate() {
		return this;
	}
	
	@Override
	public String toString() {
		String result = "Latitude: " + this.latitude + " | Longitude: " + this.longitude;
		return result;
	}
	
	public double getDistKilometer(Coordinate target) {
		
		double latSelf = this.getLatitude() / 180 * Math.PI;
		double lonSelf = this.getLongitude() / 180 * Math.PI;
		double latTarget = target.getLatitude() / 180 * Math.PI;
		double lonTarget = target.getLongitude() / 180 * Math.PI;
		double d = 0; double r = 6378.137;
		double distKilometer;
		// Entfernungsberechnung mittels Einheitskugel
		d = Math.acos((Math.sin(latSelf)*Math.sin(latTarget)) 
				+ (Math.cos(latSelf)*Math.cos(latTarget)*Math.cos(lonTarget - lonSelf)));
		distKilometer = Math.round(d * r * 100) / 100.0;
		return distKilometer;
	}

	public double getDistMeter(Coordinate target) {
		double distMeter;
		distMeter = getDistKilometer(target) * 1000; 
		return distMeter;
	}
	
	public int getDurationOf(double Dist) {
		int durationMinute;
		final int maxMeterPerMinute = 65;
		durationMinute = (int) (Dist/maxMeterPerMinute);
		return durationMinute;
	}	
	
}
