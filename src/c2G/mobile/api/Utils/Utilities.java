package c2G.mobile.api.Utils;

import java.util.ArrayList;
import java.util.List;

import c2G.mobile.api.objekts.Coordinate;
import c2G.mobile.api.objekts.GasStation;
import c2G.mobile.api.objekts.Location;
import c2G.mobile.api.objekts.ParkingSpot;
import c2G.mobile.api.objekts.Vehicle;

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
	
	public static List<Vehicle> filterVehicleList(List<Vehicle> list, Coordinate coordinate, double range) {
		List<Vehicle> result = new ArrayList<Vehicle>();
        for (int i = 0; i < list.size(); i++) {
        	if (list.get(i).getPosition().getCoordinate().getDistKilometer(coordinate) <= range) {
                double distanceKm = list.get(i).getPosition().getCoordinate().getDistKilometer(new Coordinate(48.400833,9.987222));
                int timeToGo = list.get(i).getPosition().getCoordinate().getDurationOf(distanceKm);
        		list.get(i).setDistanceKm(distanceKm);
        		list.get(i).setTimeToGo(timeToGo);
        		result.add(list.get(i));
			}
		}
        return result;
	}
	
	public static List<ParkingSpot> filterParkingSpotsList(List<ParkingSpot> list, Coordinate coordinate, double range) {
		List<ParkingSpot> result = new ArrayList<ParkingSpot>();
        for (int i = 0; i < list.size(); i++) {
        	if (list.get(i).getCoordinate().getDistKilometer(coordinate) <= range) {
                double distanceKm = list.get(i).getCoordinate().getDistKilometer(new Coordinate(48.400833,9.987222));
                int timeToGo = list.get(i).getCoordinate().getDurationOf(distanceKm);
        		list.get(i).setDistanceKm(distanceKm);
        		list.get(i).setTimeToGo(timeToGo);
        		result.add(list.get(i));
			}
		}
        return result;
	}
	
	public static List<GasStation> filterGasStationList(List<GasStation> list, Coordinate coordinate, double range) {
		List<GasStation> result = new ArrayList<GasStation>();
        for (int i = 0; i < list.size(); i++) {
        	if (list.get(i).getCoordinate().getDistKilometer(coordinate) <= range) {
                double distanceKm = list.get(i).getCoordinate().getDistKilometer(new Coordinate(48.400833,9.987222));
                int timeToGo = list.get(i).getCoordinate().getDurationOf(distanceKm);
        		list.get(i).setDistanceKm(distanceKm);
        		list.get(i).setTimeToGo(timeToGo);
        		result.add(list.get(i));
			}
		}
        return result;
	}

}
