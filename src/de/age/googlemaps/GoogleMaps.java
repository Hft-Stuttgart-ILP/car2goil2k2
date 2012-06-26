package de.age.googlemaps;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import de.age.hellogooglemaps2.R;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GoogleMaps extends MapActivity {
	
	MapView mapView;
	LocationManager locationManager;
	MapController mapController;
	List<Overlay> mapOverlays;
	Drawable drawable;
	MyOverlays myOverlay;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(de.age.hellogooglemaps2.R.layout.main);
        
        mapView = (MapView) findViewById(de.age.hellogooglemaps2.R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        mapController.setZoom(15);
        
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GeoUpdateHandler());
        
        // Testen, ob GPS verfügbar
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
        	warnungUndBeenden();
        }
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		
		// TODO Auto-generated method stub
		return false;
	}
	
	
   	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
//			createMarker();
			mapController.animateTo(point); // mapController.setCenter(point);

		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
	
   	
   	/** Benutzer auffordern GPS zu aktivieren und Activity beenden 
     * 
     */
    private void warnungUndBeenden() {
    	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	 Resources res = getResources();
    	 String text   = res.getString(R.string.keinGPS);
    	 builder.setMessage(text); 
    	 builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 dialog.dismiss();
                 finish(); // Activity beenden
             }
         });
    	 AlertDialog dialog = builder.create();
    	 dialog.show();
    }
   	
}