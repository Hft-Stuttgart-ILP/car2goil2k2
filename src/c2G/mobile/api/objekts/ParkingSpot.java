package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class ParkingSpot {

	Coordinate coordinate;
	String name;
	int totalCapacity;
	int usedCapacity;
	boolean chargingPole;
	private double distanceKm;
	private int timeToGo;
	
	public ParkingSpot(Coordinate coordinate, String name, int totalCapacity,
			int usedCapacity, boolean chargingPole) {
		super();
		this.coordinate = coordinate;
		this.name = name;
		this.totalCapacity = totalCapacity;
		this.usedCapacity = usedCapacity;
		this.chargingPole = chargingPole;
	}

	public ParkingSpot(Coordinate coordinate, String name,
			String totalCapacity, String usedCapacity, String chargingPole) {
		this.coordinate = coordinate;
		this.name = name;
		this.totalCapacity = Integer.parseInt(totalCapacity);
		this.usedCapacity = Integer.parseInt(usedCapacity);
		this.chargingPole = Boolean.parseBoolean(chargingPole);
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

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public int getUsedCapacity() {
		return usedCapacity;
	}

	public void setUsedCapacity(int usedCapacity) {
		this.usedCapacity = usedCapacity;
	}

	public boolean isChargingPole() {
		return chargingPole;
	}

	public void setChargingPole(boolean chargingPole) {
		this.chargingPole = chargingPole;
	}

	public int getTimeToGo() {
		return timeToGo;
	}

	public void setTimeToGo(int timeToGo) {
		this.timeToGo = timeToGo;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}
	
}
