package c2G.mobile.api.communication;

import c2G.mobile.api.objekts.Coordinate;
import Car2Go.mobile.api.communication.R;
import android.app.Activity;
import android.os.Bundle;

public class Car2GoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Endpoint ep = new Endpoint();
//        ep.getAllFreeVehicles("ulm", "AdrianMarsch");
//        ep.getAllParkingSpots("ulm", "AdrianMarsch");
//        ep.getAllLocations("AdrianMarsch");
        ep.getAllPGasStations("ulm", "AdrianMarsch");
        ep.getAllFreeVehiclesInRange("ulm", "AdrianMarsch", 3, new Coordinate(48.400833,9.987222));
        
    }
}