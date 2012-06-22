package c2G.mobile.api.Utils;

import java.util.List;

import c2G.mobile.api.objekts.Coordinate;
import c2G.mobile.api.objekts.Location;

public class Utilities {
	
	public static Location getLocationByCoordinates(List<Location> locations, Coordinate coordinate) {
		for (int i = 0; i < locations.size(); i++) {
			if (locations.get(i).getUpperLeft().getCoordinate().getLatitude() > coordinate.getLatitude() 
					&& locations.get(i).getUpperLeft().getCoordinate().getLongitude() < coordinate.getLongitude()
					&& locations.get(i).getLowerRight().getCoordinate().getLatitude() < coordinate.getLatitude()
					&& locations.get(i).getLowerRight().getCoordinate().getLongitude() > coordinate.getLongitude()) {
				return locations.get(i);
			}
		}
		return null;
	}

}
