package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class Position extends Coordinate{
	
	String adress;

	public Position(double latitude, double longitude, String adress) {
		super(latitude, longitude);
		this.adress = adress;
	}
	
	public Position(Coordinate coordinate, String adress) {
		super(coordinate.latitude, coordinate.longitude);
		this.adress = adress;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
