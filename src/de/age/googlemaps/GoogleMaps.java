package de.age.googlemaps;

import java.util.List;

import c2G.mobile.api.communication.Endpoint;
import c2G.mobile.api.objekts.Coordinate;
import c2G.mobile.api.objekts.GasStation;
import c2G.mobile.api.objekts.ParkingSpot;
import c2G.mobile.api.objekts.Vehicle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;

import de.gui.c2g.R;



import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

public class GoogleMaps extends MapActivity {
	
	
	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private MyOverlays itemizedoverlaydot, itemizedOverlayCar, itemizedOverlayParkingSlot, itemizedOverlayGasStation;
	private MyLocationOverlay myLocationOverlay;
	private GeoPoint userlocation;
	private Endpoint endpoint;
	

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemapmain);
        
        
		// Configure the Map
		mapView = (MapView) findViewById(R.id.mapview);		
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapController = mapView.getController();
		mapController.setZoom(14); // Zoon 1 is world view
		mapController.setCenter(new GeoPoint((int) (48.400833 * 1E6), (int) (9.987222* 1E6)));
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,
				0, new GeoUpdateHandler());

		myLocationOverlay = new MyLocationOverlay(this, mapView);
		mapView.getOverlays().add(myLocationOverlay);

//		myLocationOverlay.runOnFirstFix(new Runnable() {
//			public void run() {
//				mapView.getController().animateTo(myLocationOverlay.getMyLocation());
//			}
//		});

		Drawable drawabledot = this.getResources().getDrawable(R.drawable.reddot);
		Drawable drawableCar = this.getResources().getDrawable(R.drawable.car2go_icon_org_klein);
//		Drawable drawableParkingSlot = null;
//		Drawable drawableGasStation = null;
		
		itemizedoverlaydot = new MyOverlays(drawabledot);
		itemizedOverlayCar = new MyOverlays(drawableCar);
		
		endpoint = new Endpoint();
		
		
//		itemizedOverlayGasStation = new MyOverlays(drawableGasStation);
//		itemizedOverlayParkingSlot = new MyOverlays(drawableParkingSlot);
		
		getAllFreeVehicle("ulm", "AdrianMarsch", 1, new Coordinate(48.400833, 9.987222));
		
//		getAllGasStations("ulm", "AdrianMarsch");
		
//		getAllParkingSpots("ulm", "AdrianMarsch");
		
		
    }

	@Override
	protected boolean isRouteDisplayed() {
		
		// TODO Auto-generated method stub
		return false;
	}
	
	public class GeoUpdateHandler implements LocationListener {

		public void onLocationChanged(Location location) {
//			int lat = (int) (location.getLatitude() * 1E6);			
//			int lng = (int) (location.getLongitude() * 1E6);
			
			int lat = (int) (48.400833 * 1E6);			
			int lng = (int) (9.987222 * 1E6);
			
			GeoPoint point = new GeoPoint(lat, lng);
//			createMarker();
//			mapController.animateTo(point); // mapController.setCenter(point);
			userlocation = point;
			OverlayItem overlayitem = new OverlayItem(point, "", "");
			itemizedoverlaydot.adOverlay(overlayitem);
			mapView.getOverlays().add(itemizedoverlaydot);
			
			
			

		}


		public void onProviderDisabled(String provider) {
		}


		
		public void onProviderEnabled(String provider) {
		}


		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
	
	private void createMarkerCars(double latitude, double longitude) {
		
		int lat = (int) (latitude * 1E6);
		int lng = (int) (longitude * 1E6);
		GeoPoint p = new GeoPoint(lat, lng);
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedOverlayCar.adOverlay(overlayitem);
		if (itemizedOverlayCar.size() > 0) {
			mapView.getOverlays().add(itemizedOverlayCar);
		}
	}
//	private void createMarkerGasStations(Coordinate coordinate) {
//		
//		int lat = (int) (coordinate.getLatitude() * 1E6);
//		int lng = (int) (coordinate.getLongitude() * 1E6);
//		GeoPoint p = new GeoPoint(lat, lng);
//		OverlayItem overlayitem = new OverlayItem(p, "", "");
//		itemizedOverlayCar.adOverlay(overlayitem);
//		if (itemizedOverlayCar.size() > 0) {
//			mapView.getOverlays().add(itemizedOverlayCar);
//		}
//	}
	
//	private void createMarkerParkingSlots(Coordinate coordinate) {
//		
//		int lat = (int) (coordinate.getLatitude() * 1E6);
//		int lng = (int) (coordinate.getLongitude() * 1E6);
//		GeoPoint p = new GeoPoint(lat, lng);
//		OverlayItem overlayitem = new OverlayItem(p, "", "");
//		itemizedOverlayCar.adOverlay(overlayitem);
//		if (itemizedOverlayCar.size() > 0) {
//			mapView.getOverlays().add(itemizedOverlayCar);
//		}
//	}
	
//	private void createMarker() {
//		
//		int lat = (int) (48.7687 * 1E6);
//		int lng = (int) (9.18293 * 1E6);
//		GeoPoint p = new GeoPoint(lat, lng);
//		OverlayItem overlayitem = new OverlayItem(p, "", "");
//		itemizedOverlayCar.adOverlay(overlayitem);
//		if (itemizedOverlayCar.size() > 0) {
//			mapView.getOverlays().add(itemizedOverlayCar);
//		}
//	}
	
	
	public void locateUser(View view) {
		mapController.animateTo(userlocation);
	}
	
	
	private List<Vehicle> getAllFreeVehicle(String location, String authorization,int range, Coordinate coordinate) {
		
		List<Vehicle> freeVehicle = endpoint.getAllFreeVehiclesInRange(location, authorization, range, coordinate);
		
		for (int i = 0; i < freeVehicle.size(); i++) {
			System.out.println("lat. " + freeVehicle.get(i).getPosition().getLatitude() 
					+ "lon: " + freeVehicle.get(i).getPosition().getLongitude());
			createMarkerCars(freeVehicle.get(i).getPosition().getLatitude(), freeVehicle.get(i).getPosition().getLongitude());
		}

		return freeVehicle;
		
	}
	
//	private List<ParkingSpot> getAllParkingSpots(String location, String authorization) {
//		List<ParkingSpot> allParkingSpots = endpoint.getAllParkingSpots(location, authorization);
//		
//		for (int i = 0; i < allParkingSpots.size(); i++) {
//			createMarkerParkingSlots(allParkingSpots.get(i).getCoordinate());
//		}
//		
//		return allParkingSpots;
//	}
	
//	private List<GasStation> getAllGasStations(String location, String authorization) {
//		List<GasStation> allGasStations = endpoint.getAllPGasStations(location, authorization);
//		
//		for (int i = 0; i < allGasStations.size(); i++) {
//			createMarkerGasStations(allGasStations.get(i).getCoordinate());
//		}
//		
//		return allGasStations;
//	}
}