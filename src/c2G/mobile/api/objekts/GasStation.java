package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class GasStation {

	Coordinate coordinate;
	String name;
	private double distanceKm;
	private int timeToGo;
	
	public GasStation(Coordinate coordinate, String name) {
		super();
		this.coordinate = coordinate;
		this.name = name;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}

	public int getTimeToGo() {
		return timeToGo;
	}

	public void setTimeToGo(int timeToGo) {
		this.timeToGo = timeToGo;
	}
	
}
