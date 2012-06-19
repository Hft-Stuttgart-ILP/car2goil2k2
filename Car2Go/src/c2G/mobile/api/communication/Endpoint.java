package c2G.mobile.api.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import c2G.mobile.api.objekts.Account;
import c2G.mobile.api.objekts.Booking;
import c2G.mobile.api.objekts.CanceledBooking;
import c2G.mobile.api.objekts.Coordinate;
import c2G.mobile.api.objekts.GasStation;
import c2G.mobile.api.objekts.Location;
import c2G.mobile.api.objekts.ParkingSpot;
import c2G.mobile.api.objekts.Position;
import c2G.mobile.api.objekts.Vehicle;

/**
 * @author brought to you by 1st
 *
 */
public class Endpoint implements EndpointCommunication {
	
	public List<Vehicle> getAllFreeVehicles(String loc, String oauth_consumer_key) {
		List<Vehicle> result = new ArrayList<Vehicle>();
		String url = String.format(GETALLFREEVEHICLES, loc, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Position position = new Position(getCoordinatesFromJsonArray(jsonObject.getJSONArray((COORDINATES))), jsonObject.getString(ADRESS));
				Vehicle vehicle = new Vehicle(
						jsonObject.getString(VIN),
						jsonObject.getString(NAME),
						position,
						jsonObject.getString(EXTERIOR),
						jsonObject.getString(INTERIOR),
						jsonObject.getString(FUEL),
						jsonObject.getString(ENGINE_TYPE));
				result.add(vehicle);
				//debug
//				Log.i(Endpoint.class.getCanonicalName(), vehicle.getPlate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ParkingSpot> getAllParkingSpots(String loc, String oauth_consumer_key) {
		List<ParkingSpot> result = new ArrayList<ParkingSpot>();
		String url = String.format(GETALLPARKINGSPOTS, loc, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Coordinate coordinate = new Coordinate(getCoordinatesFromJsonArray(jsonObject.getJSONArray(COORDINATES)));
				ParkingSpot parkingSpot = new ParkingSpot(
						coordinate,
						jsonObject.getString(NAME),
						jsonObject.getString(TOTAL_CAPACITY),
						jsonObject.getString(USED_CAPACITY), 
						jsonObject.getString(CHARGING_POLE));
				result.add(parkingSpot);
				//debug
//				Log.i(Endpoint.class.getCanonicalName(), parkingSpot.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Location> getAllLocations(String oauth_consumer_key) {
		List<Location> result = new ArrayList<Location>();
		String url = String.format(GETALLLOCATIONS, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(LOCATION));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				JSONObject mapSection = jsonObject.getJSONObject(MAPS_SECTION);
				Location location = new Location(
						jsonObject.getString(COUNTRY_CODE),
						jsonObject.getString(DEFAULT_LANGUAGE),
						jsonObject.getString(LOCATION_ID), 
						jsonObject.getString(LOCATION_NAME), 
						new Coordinate(getCoordinatesFromJsonObjekt(mapSection.getJSONObject(CENTER))), 
						new Coordinate(getCoordinatesFromJsonObjekt(mapSection.getJSONObject(LOWER_RIGHT))), 
						new Coordinate(getCoordinatesFromJsonObjekt(mapSection.getJSONObject(UPPER_LEFT))), 
						jsonObject.get(TIMEZONE).toString());
				//debug
//				Log.i(Endpoint.class.getCanonicalName(), location.getLocationName());
				result.add(location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<GasStation> getAllPGasStations(String loc, String oauth_consumer_key) {
		List<GasStation> result = new ArrayList<GasStation>();
		String url = String.format(GETALLGASSTATIONS, loc, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Coordinate coordinate = new Coordinate(getCoordinatesFromJsonArray((JSONArray) jsonObject.get(COORDINATES)));
				GasStation gasStation = new GasStation(
						coordinate,
						jsonObject.getString(NAME));
				result.add(gasStation);
				//debug
//				Log.i(Endpoint.class.getCanonicalName(), gasStation.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Account> getAllAccounts(String loc) {
		List<Account> result = new ArrayList<Account>();
//		String url = String.format(GETALLACCOUNTS, loc);
//		String data = getDataByURL(url);
//		try {
//			JSONObject jsonObj = new JSONObject(data);
//			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
//			for (int i = 0; i < jsonArray.length(); i++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				Coordinate coordinate = new Coordinate(getCoordinatesFromJsonArray((JSONArray) jsonObject.get(COORDINATES)));
//				GasStation gasStation = new GasStation(
//						coordinate,
//						jsonObject.getString(NAME));
//				result.add(gasStation);
//				//debug
////				Log.i(Endpoint.class.getCanonicalName(), gasStation.getName());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return result;
	}

	public List<Booking> getBookings(String loc) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Booking> getBooking(String loc) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Booking> createBooking(String loc, String vin, String accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CanceledBooking> cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getDataByURL(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		Log.i(Endpoint.class.getName(), "connect to: " + url);
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(Endpoint.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	private Coordinate getCoordinatesFromJsonArray(JSONArray jsonArray) throws JSONException{
		Coordinate coordiante = new Coordinate(jsonArray.getDouble(0),jsonArray.getDouble((0)));
		return coordiante;
	}
	
	private Coordinate getCoordinatesFromJsonObjekt(JSONObject jsonObjekt) throws JSONException{
		Coordinate coordiante = new Coordinate(jsonObjekt.getDouble(LATITUDE), jsonObjekt.getDouble((LONGITUDE)));
		return coordiante;
	}
	
}
