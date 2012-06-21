package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class GasStation {

	Coordinate coordinate;
	String name;
	
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
	
}
