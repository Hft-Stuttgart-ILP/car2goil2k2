package c2G.mobile.api.communication;

import java.util.List;

import c2G.mobile.api.Utils.Utilities;
import c2G.mobile.api.objekts.Coordinate;
import c2G.mobile.api.objekts.Location;
import c2G.mobile.api.objekts.Vehicle;
import android.os.Bundle;

/**
 * @author brought to you by 1st
 * show me some Samples how to Use communication via Endpoint Framework, Filtering Lists and Utils
 */
public class Samples {
    public void dosometesting(Bundle savedInstanceState) {
        /*
         * samples
         */
        
        // needed Parameters
        String loc = "ulm";
        String oauth_consumer_key = "AdrianMarsch";
        
        // create Endpoint Object
        Endpoint ep = new Endpoint();
        
        //Also possible, but some Methods may not be available
//        EndpointCommunication ep = new Endpoint();
        
        //call Methods -> for details see Method Comment. While debug mode output in log
        ep.getAllFreeVehicles(loc, oauth_consumer_key);
        ep.getAllParkingSpots(loc, oauth_consumer_key);
        ep.getAllLocations(oauth_consumer_key);
        ep.getAllPGasStations(loc, oauth_consumer_key);
        //... for more Methods see Interface or Endpoint implementation
        
        /*
         * two ways to filter Vehicles, ParkingSpots and GasStations
         */
        
        //Request with filter
        ep.getAllFreeVehiclesInRange(loc, oauth_consumer_key, 3, new Coordinate(48.400833,9.987222));
        ep.getAllParkingSpotsInRange(loc, oauth_consumer_key, 3, new Coordinate(48.400833,9.987222));
        ep.getAllPGasStationsInRange(loc, oauth_consumer_key, 3, new Coordinate(48.400833,9.987222));
        
        //filter complete vehicle list
        List<Vehicle> allcars = ep.getAllFreeVehicles(loc, oauth_consumer_key);
        for (int i = 0; i < allcars.size(); i++) {
        	if (allcars.get(i).getPosition().getCoordinate().getDistKilometer(new Coordinate(48.400833,9.987222)) <= 2) {
                double distkm = allcars.get(i).getPosition().getCoordinate().getDistKilometer(new Coordinate(48.400833,9.987222));
                double distMeter = allcars.get(i).getPosition().getCoordinate().getDistMeter(new Coordinate(48.400833,9.987222));
                int timeMeter = allcars.get(i).getPosition().getCoordinate().getDurationOf(distMeter);
                
                System.out.println("Distance km: " + distkm);
                System.out.println("Distance m: " + distMeter);
                System.out.println("Time m: " + timeMeter);
			}

		}
        
        //Auto detect Location by current Coordinates
        List<Location> locationList = ep.getAllLocations(oauth_consumer_key);
        Coordinate ulm = new Coordinate(48.400833,9.987222);
        Coordinate vancouver = new Coordinate(49.28098, -123.1224410);
        Location detectUlm = Utilities.getLocationByCoordinates(locationList, ulm);
        Location detectVancouver = Utilities.getLocationByCoordinates(locationList, vancouver);
        System.out.println(ulm.getLatitude() + "  /  " + ulm.getLongitude());
        System.out.println("detected location is: ---->" + detectVancouver.getLocationName());
        System.out.println("detected location is: ---->" + detectUlm.getLocationName());


    }
}
