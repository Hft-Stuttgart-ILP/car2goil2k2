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
import c2G.mobile.api.objekts.ReservationTime;
import c2G.mobile.api.objekts.TimeZone;
import c2G.mobile.api.objekts.Vehicle;

/**
 * @author brought to you by 1st
 * Provides Methods to communicate with Car2Go Endpoint.
 */
public class Endpoint implements EndpointCommunication {
	
	public List<Vehicle> getAllFreeVehicles(String loc, String oauth_consumer_key) {
		List<Vehicle> result = new ArrayList<Vehicle>();
		String url = String.format(URL_GETALLFREEVEHICLES, loc, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Position position = new Position(
						getCoordinatesFromJsonArray(jsonObject.getJSONArray((COORDINATES))),
						jsonObject.getString(ADRESS));
				Vehicle vehicle = new Vehicle(
						jsonObject.getString(VIN),
						jsonObject.getString(NAME),
						position,
						jsonObject.getString(EXTERIOR),
						jsonObject.getString(INTERIOR),
						jsonObject.getString(FUEL),
						jsonObject.getString(ENGINE_TYPE));
				result.add(vehicle);
				Log.d(Endpoint.class.getCanonicalName(), vehicle.getPlate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**Provides a list of all free car2go vehicles for a given location like Ulm or Austin within a given Range in Kilometers of the submitted Position.
	 * A OAuth Consumer Key is needed.
	 * http Request Type: public.
	 * Can be provided as KML. (NOT YET IMPLEMENTED)
	 * @param loc - Location e.g. ulm
	 * @param oauth_consumer_key - valid OAuth Consumer Key
	 * @param range in km
	 * @param myPosition your current position
	 * @return List<Vehicle> inner type c2G.mobile.api.objekts.Vehicle.java
	 * @see More information about KML can be found at: https://developers.google.com/kml/documentation/kmlreference?hl=de
	 */
	public List<Vehicle> getAllFreeVehiclesInRange(String loc, String oauth_consumer_key, double range, Coordinate myPosition) {
		List<Vehicle> result = new ArrayList<Vehicle>();
		String url = String.format(URL_GETALLFREEVEHICLES, loc, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Position position = new Position(
						getCoordinatesFromJsonArray(jsonObject.getJSONArray((COORDINATES))),
						jsonObject.getString(ADRESS));
				if (position.getDistKilometer(myPosition) <= range) {
					Vehicle vehicle = new Vehicle(
							jsonObject.getString(VIN),
							jsonObject.getString(NAME),
							position,
							jsonObject.getString(EXTERIOR),
							jsonObject.getString(INTERIOR),
							jsonObject.getString(FUEL),
							jsonObject.getString(ENGINE_TYPE));
					result.add(vehicle);
					Log.d(Endpoint.class.getCanonicalName(), vehicle.getPlate());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ParkingSpot> getAllParkingSpots(String loc, String oauth_consumer_key) {
		List<ParkingSpot> result = new ArrayList<ParkingSpot>();
		String url = String.format(URL_GETALLPARKINGSPOTS, loc, oauth_consumer_key);
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
				Log.d(Endpoint.class.getCanonicalName(), parkingSpot.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**Provides a list of car2go parking spots for a specific location like Ulm or Austin  within a given Range in Kilometers of the submitted Position.
	 * A OAuth Consumer Key is required.
	 * http Request Type: public.
	 * Can be provided as KML. (NOT YET IMPLEMENTED)
	 * @param loc - Location e.g. ulm
	 * @param oauth_consumer_key - valid OAuth Consumer Key
	 * @param range in km
	 * @param myPosition your current position
	 * @return List<ParkingSpot> inner type c2G.mobile.api.objekts.ParkingSpot.java
	 * @see More information about KML can be found at: https://developers.google.com/kml/documentation/kmlreference?hl=de
	 */
	public List<ParkingSpot> getAllParkingSpotsInRange(String loc, String oauth_consumer_key, double range, Coordinate myPosition) {
		List<ParkingSpot> result = new ArrayList<ParkingSpot>();
		String url = String.format(URL_GETALLPARKINGSPOTS, loc, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Coordinate coordinate = new Coordinate(getCoordinatesFromJsonArray(jsonObject.getJSONArray(COORDINATES)));
				if (coordinate.getDistKilometer(myPosition) <= range) {
					ParkingSpot parkingSpot = new ParkingSpot(
							coordinate,
							jsonObject.getString(NAME),
							jsonObject.getString(TOTAL_CAPACITY),
							jsonObject.getString(USED_CAPACITY), 
							jsonObject.getString(CHARGING_POLE));
					result.add(parkingSpot);
					Log.d(Endpoint.class.getCanonicalName(), parkingSpot.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Location> getAllLocations(String oauth_consumer_key) {
		List<Location> result = new ArrayList<Location>();
		String url = String.format(URL_GETALLLOCATIONS, oauth_consumer_key);
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
				Log.d(Endpoint.class.getCanonicalName(), location.getLocationName());
				result.add(location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<GasStation> getAllPGasStations(String loc, String oauth_consumer_key) {
		List<GasStation> result = new ArrayList<GasStation>();
		String url = String.format(URL_GETALLGASSTATIONS, loc, oauth_consumer_key);
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
				Log.d(Endpoint.class.getCanonicalName(), gasStation.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**Provides a list of car2go gas stations in context of specific location like Ulm or Austin within a given Range in Kilometers of the submitted Position.
	 * A OAuth Consumer Key is needed.
	 * http Request Type: public.
	 * Can be provided as KML. (NOT YET IMPLEMENTED)
	 * @param loc - Location e.g. ulm
	 * @param oauth_consumer_key - valid OAuth Consumer Key
	 * @param range in km
	 * @param myPosition your current position
	 * @return List<GasStation> inner type c2G.mobile.api.objekts.GasStation.java
	 * @see More information about KML can be found at: https://developers.google.com/kml/documentation/kmlreference?hl=de
	 */
	public List<GasStation> getAllPGasStationsInRange(String loc, String oauth_consumer_key, double range, Coordinate myPosition) {
		List<GasStation> result = new ArrayList<GasStation>();
		String url = String.format(URL_GETALLGASSTATIONS, loc, oauth_consumer_key);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(PLACEMARKS));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Coordinate coordinate = new Coordinate(getCoordinatesFromJsonArray((JSONArray) jsonObject.get(COORDINATES)));
				if (coordinate.getDistKilometer(myPosition) <= range) {
					GasStation gasStation = new GasStation(
							coordinate,
							jsonObject.getString(NAME));
					result.add(gasStation);
					Log.d(Endpoint.class.getCanonicalName(), gasStation.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Account> getAllAccounts(String loc) {
		List<Account> result = new ArrayList<Account>();
		String url = String.format(URL_GETALLACCOUNTS, loc);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(ACCOUNT));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Account account = new Account(
						jsonObject.getString(ACCOUNT_ID), 
						jsonObject.getString(DESCRIPTION));
				result.add(account);
				Log.d(Endpoint.class.getCanonicalName(), account.getAccountId());
				Log.d(Endpoint.class.getCanonicalName(), account.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Booking> getBookings(String loc) {
		List<Booking> result = new ArrayList<Booking>();
		String url = String.format(URL_GETBOOKINGS, loc);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(BOOKING));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				JSONObject jAccount = jsonObject.getJSONObject(ACCOUNT);
				Account account = new Account(
						jAccount.getString(ACCOUNT_ID),
						jAccount.getString(DESCRIPTION));
				JSONObject jVehicle = jsonObject.getJSONObject(VEHICLE);
				Position position = getPositionFromJsonObjekt(jVehicle.getJSONObject(POSITION));
				Vehicle vehicle = new Vehicle(
						jVehicle.getString(VIN),
						jVehicle.getString(NUMBER_PLATE),
						position,
						jVehicle.getString(EXTERIOR),
						jVehicle.getString(INTERIOR),
						jVehicle.getString(FUEL),
						jVehicle.getString(ENGINE_TYPE));
				JSONObject jReservationTime = jsonObject.getJSONObject(RESERVATION_TIME);
				JSONObject jTimeZone = jReservationTime.getJSONObject(TIMEZONE);
				JSONObject innerJTimeZone = jTimeZone.getJSONObject(TIMEZONE);
				TimeZone innerTimeZone = new TimeZone(
						innerJTimeZone.getString(DST_SAVINGS),
						innerJTimeZone.getString(ID),
						innerJTimeZone.getString(DISPLAY_NAME),
						innerJTimeZone.getString(RAW_OFFSET));
				TimeZone timeZone = new TimeZone(
						jTimeZone.getString(DST_SAVINGS),
						jTimeZone.getString(ID),
						jTimeZone.getBoolean(DIRTY),
						jTimeZone.getString(DISPLAY_NAME),
						innerTimeZone,
						jTimeZone.getString(RAW_OFFSET));
				ReservationTime reservationTime = new ReservationTime(
						jReservationTime.getString(FIRST_DAY_OF_WEEK), 
						jReservationTime.getString(GREGORIAN_CHANGE), 
						jReservationTime.getBoolean(LENIENT), 
						jReservationTime.getString(MINIMAL_DAYS_IN_FIRST_WEEK), 
						jReservationTime.getString(TIME), 
						jReservationTime.getString(TIME_IN_MILLIS), 
						timeZone);
				Position bookingPosition = getPositionFromJsonObjekt(jsonObject.getJSONObject(BOOKING_POSITION));
				Booking booking = new Booking(
						account,
						jsonObject.getString(BOOKING_ID),
						bookingPosition,
						vehicle,
						reservationTime);
				result.add(booking);
				Log.d(Endpoint.class.getCanonicalName(), booking.getBookingId());
				Log.d(Endpoint.class.getCanonicalName(), booking.getReservationTime().getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Booking getBooking(String loc) {
		Booking result = null;
		String url = String.format(URL_GETBOOKINGS, loc);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(BOOKING));
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			JSONObject jAccount = jsonObject.getJSONObject(ACCOUNT);
			Account account = new Account(
					jAccount.getString(ACCOUNT_ID),
					jAccount.getString(DESCRIPTION));
			JSONObject jVehicle = jsonObject.getJSONObject(VEHICLE);
			Position position = getPositionFromJsonObjekt(jVehicle.getJSONObject(POSITION));
			Vehicle vehicle = new Vehicle(
					jVehicle.getString(VIN),
					jVehicle.getString(NUMBER_PLATE),
					position,
					jVehicle.getString(EXTERIOR),
					jVehicle.getString(INTERIOR),
					jVehicle.getString(FUEL),
					jVehicle.getString(ENGINE_TYPE));
			JSONObject jReservationTime = jsonObject.getJSONObject(RESERVATION_TIME);
			JSONObject jTimeZone = jReservationTime.getJSONObject(TIMEZONE);
			JSONObject innerJTimeZone = jTimeZone.getJSONObject(TIMEZONE);
			TimeZone innerTimeZone = new TimeZone(
					innerJTimeZone.getString(DST_SAVINGS),
					innerJTimeZone.getString(ID),
					innerJTimeZone.getString(DISPLAY_NAME),
					innerJTimeZone.getString(RAW_OFFSET));
			TimeZone timeZone = new TimeZone(
					jTimeZone.getString(DST_SAVINGS),
					jTimeZone.getString(ID),
					jTimeZone.getBoolean(DIRTY),
					jTimeZone.getString(DISPLAY_NAME),
					innerTimeZone,
					jTimeZone.getString(RAW_OFFSET));
			ReservationTime reservationTime = new ReservationTime(
					jReservationTime.getString(FIRST_DAY_OF_WEEK), 
					jReservationTime.getString(GREGORIAN_CHANGE), 
					jReservationTime.getBoolean(LENIENT), 
					jReservationTime.getString(MINIMAL_DAYS_IN_FIRST_WEEK), 
					jReservationTime.getString(TIME), 
					jReservationTime.getString(TIME_IN_MILLIS), 
					timeZone);
			Position bookingPosition = getPositionFromJsonObjekt(jsonObject.getJSONObject(BOOKING_POSITION));
			Booking booking = new Booking(
					account,
					jsonObject.getString(BOOKING_ID),
					bookingPosition,
					vehicle,
					reservationTime);
			result = booking;
			Log.d(Endpoint.class.getCanonicalName(), booking.getBookingId());
			Log.d(Endpoint.class.getCanonicalName(), booking.getReservationTime().getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Booking> createBooking(String loc, String vin, String accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	public CanceledBooking cancelBooking(String bookingID) {
		CanceledBooking result = null;
		String url = String.format(URL_CANCELBOOKING, bookingID);
		String data = getDataByURL(url);
		try {
			JSONObject jsonObj = new JSONObject(data);
			JSONArray jsonArray = new JSONArray(jsonObj.getString(CANCEL_BOOKING));
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			CanceledBooking canceledBooking = new CanceledBooking(
					jsonObject.getString(CANCEL_FEE),
					jsonObject.getString(CANCEL_FEE_CURRENCY),
					jsonObject.getBoolean(CANCEL_FEE_EXISTS));
			result = canceledBooking;
			Log.d(Endpoint.class.getCanonicalName(), canceledBooking.getCancelFee());
			Log.d(Endpoint.class.getCanonicalName(), canceledBooking.getCancelFeeCurrency());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getDataByURL(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		Log.d(Endpoint.class.getName(), "connect to: " + url);
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
		Coordinate coordiante = new Coordinate(
				jsonArray.getDouble(1),
				jsonArray.getDouble((0)));
		return coordiante;
	}
	
	private Coordinate getCoordinatesFromJsonObjekt(JSONObject jsonObjekt) throws JSONException{
		Coordinate coordiante = new Coordinate(
				jsonObjekt.getDouble(LATITUDE), 
				jsonObjekt.getDouble((LONGITUDE)));
		return coordiante;
	}
	
	private Position getPositionFromJsonObjekt(JSONObject jsonObjekt) throws JSONException{
		Position position = new Position(
				jsonObjekt.getDouble(LATITUDE),
				jsonObjekt.getDouble(LONGITUDE),
				jsonObjekt.getString(ADRESS));
		return position;
	}
	
}
