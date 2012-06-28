package c2G.mobile.api.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.oauth.OAuthService;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import android.util.Log;
import c2G.mobile.api.objekts.Account;
import c2G.mobile.api.objekts.Booking;
import c2G.mobile.api.objekts.CanceledBooking;
import c2G.mobile.api.objekts.Position;
import c2G.mobile.api.objekts.ReservationTime;
import c2G.mobile.api.objekts.TimeZone;
import c2G.mobile.api.objekts.Vehicle;

public class PrivateEndpoint extends Endpoint {
	
	OAuthService service;
	Token accessToken;
	
	public PrivateEndpoint(OAuthService service,Token accessToken) {
		this.service = service;
		this.accessToken = accessToken;
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
			Position position = super.getPositionFromJsonObjekt(jVehicle.getJSONObject(POSITION));
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
		List<Booking> result = new ArrayList<Booking>();
		String url = String.format(URL_CREATEBOOKING, loc, vin, accountID);
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
	    OAuthRequest request = new OAuthRequest(Verb.GET, url);
        service.signRequest(accessToken, request);
		Log.d(Endpoint.class.getName(), "connect to: " + url);
		try {
	        Response response = request.send();
			if (response.isSuccessful()) {
				InputStream content = response.getStream();
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
	
}
