package c2G.mobile.api.communication;

import android.app.Activity;
import android.os.Bundle;
import de.gui.c2g.R;

public class Car2GoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapmain);
        Endpoint ep = new Endpoint();
//        ep.getAllFreeVehicles("ulm", "AdrianMarsch");
//        ep.getAllParkingSpots("ulm", "AdrianMarsch");
//        ep.getAllLocations("AdrianMarsch");
        ep.getAllPGasStations("ulm", "AdrianMarsch");
    }
}