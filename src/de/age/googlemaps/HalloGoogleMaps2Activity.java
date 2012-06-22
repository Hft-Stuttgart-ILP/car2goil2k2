package de.age.googlemaps;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import de.gui.c2g.R;

public class HalloGoogleMaps2Activity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapmain);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        MapController mapController ;
    }

	@Override
	protected boolean isRouteDisplayed() {
		
		// TODO Auto-generated method stub
		return false;
	}
}