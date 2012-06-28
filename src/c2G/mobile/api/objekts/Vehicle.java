package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class Vehicle {
	String vin;
	String plate;
	Position position;
	String exterior;
	String interior;
	String fuel;
	String engineType;
	private double distanceKm;
	private int timeToGo;

	public Vehicle(String vin, String plate, Position position,
			String exterior, String interior, String fuel, String engineType) {
		this.vin = vin;
		this.plate = plate;
		this.position = position;
		this.exterior = exterior;
		this.interior = interior;
		this.fuel = fuel;
		this.engineType = engineType;
	}
	
	public Vehicle(String vin, String plate, String adress, Coordinate coordinate,
			String exterior, String interior, String fuel, String engineType) {
		this.vin = vin;
		this.plate = plate;
		this.position = new Position(coordinate.latitude, coordinate.longitude, adress);
		this.exterior = exterior;
		this.interior = interior;
		this.fuel = fuel;
		this.engineType = engineType;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public String getInterior() {
		return interior;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
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
	
	@Override
	public String toString() {
		String result = "Entfernung: " + this.distanceKm + " km / Entfernung: " + this.timeToGo + " min";
		return result;
	}
	
}
