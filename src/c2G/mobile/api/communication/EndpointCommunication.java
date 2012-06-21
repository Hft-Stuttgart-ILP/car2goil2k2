package c2G.mobile.api.communication;

import java.util.List;

import c2G.mobile.api.objekts.Account;
import c2G.mobile.api.objekts.Booking;
import c2G.mobile.api.objekts.CanceledBooking;
import c2G.mobile.api.objekts.GasStation;
import c2G.mobile.api.objekts.Location;
import c2G.mobile.api.objekts.ParkingSpot;
import c2G.mobile.api.objekts.Vehicle;

/**
 * @author brought to you by 1st
 *
 */
public interface EndpointCommunication {
	
	//URL format Strings
	static final String URL_GETALLPARKINGSPOTS = "http://www.car2go.com/api/v2.1/parkingspots?loc=%s&oauth_consumer_key=%s&format=json";
	static final String URL_GETALLLOCATIONS = "http://www.car2go.com/api/v2.1/locations?oauth_consumer_key=%s&format=json";
	static final String URL_GETALLGASSTATIONS = "http://www.car2go.com/api/v2.1/gasstations?loc=%S&oauth_consumer_key=%s&format=json";
	static final String URL_GETALLFREEVEHICLES = "http://www.car2go.com/api/v2.1/vehicles?loc=%s&oauth_consumer_key=%s&format=json";
	static final String URL_GETALLACCOUNTS = "https://www.car2go.com/api/v2.1/accounts?loc=%s&format=json";
	static final String URL_GETBOOKINGS = "https://www.car2go.com/api/v2.1/bookings?loc=%s&format=json";
	static final String URL_GETBOOKING = "https://www.car2go.com/api/v2.1/booking?loc=%s&format=json";
	static final String URL_CREATEBOOKING = "https://www.car2go.com/api/v2.1/bookings?loc=%s&vin=%s&account=%s&format=json";
	static final String URL_CANCELBOOKING = "https://www.car2go.com/api/v2.1/booking/%s&format=json";
	
	//JSON Keywords
	static final String PLACEMARKS = "placemarks";
	static final String COORDINATES = "coordinates";
	static final String ADRESS = "address";
	static final String VIN = "vin";
	static final String NAME = "name";
	static final String EXTERIOR = "exterior";
	static final String INTERIOR = "interior";
	static final String FUEL = "fuel";
	static final String ENGINE_TYPE = "engineType";
	static final String TOTAL_CAPACITY = "totalCapacity";
	static final String USED_CAPACITY = "usedCapacity";
	static final String COUNTRY_CODE = "countryCode";
	static final String DEFAULT_LANGUAGE = "defaultLanguage";
	static final String CHARGING_POLE = "chargingPole";
	static final String LOCATION_ID = "locationId";
	static final String LOCATION = "location";
	static final String LOCATION_NAME = "locationName";
	static final String MAPS_SECTION = "mapSection";
	static final String CENTER = "center";
	static final String LOWER_RIGHT = "lowerRight";
	static final String UPPER_LEFT = "upperLeft";
	static final String TIMEZONE = "timezone";
	static final String LATITUDE = "latitude";
	static final String LONGITUDE = "longitude";
	static final String ACCOUNT = "account";
	static final String ACCOUNT_ID = "accountId";
	static final String DESCRIPTION = "description";
	static final String BOOKING = "booking";
	static final String BOOKING_ID = "bookingId";
	static final String BOOKING_POSITION = "bookingposition";
	static final String RESERVATION_TIME = "reservationTime";
	static final String FIRST_DAY_OF_WEEK = "firstDayOfWeek";
	static final String GREGORIAN_CHANGE = "gregorianChange";
	static final String LENIENT = "lenient";
	static final String MINIMAL_DAYS_IN_FIRST_WEEK = "minimalDaysInFirstWeek";
	static final String TIME = "time";
	static final String TIME_IN_MILLIS = "timeInMillis";
	static final String TIME_ZONE = "timeInMillis";
	static final String DST_SAVINGS = "DSTSavings";
	static final String ID = "ID";
	static final String DIRTY = "dirty";
	static final String DISPLAY_NAME = "displayName";
	static final String LAST_RULE_INSTANCE = "lastRuleInstance";
	static final String VEHICLE = "vehicle";
	static final String NUMBER_PLATE = "numberPlate";
	static final String POSITION = "position";
	static final String RAW_OFFSET = "rawOffset";
	static final String RETURN_VALUE = "returnValue";
	static final String CANCEL_BOOKING = "cancelBooking";
	static final String CANCEL_FEE = "cancelFee";
	static final String CANCEL_FEE_CURRENCY = "cancelFeeCurrency";
	static final String CANCEL_FEE_EXISTS = "cancelFeeExists";
	
	/**Provides a list of car2go parking spots for a specific location like Ulm or Austin.
	 * A OAuth Consumer Key is required.
	 * http Request Type: public
	 * Can be provided as KML. (NOT YET IMPLEMENTED)
	 * @param loc - Location e.g. ulm
	 * @param oauth_consumer_key - valid OAuth Consumer Key
	 * @return List<ParkingSpot> inner type c2G.mobile.api.objekts.ParkingSpot.java
	 * @see More information about KML can be found at: https://developers.google.com/kml/documentation/kmlreference?hl=de
	 */
	public List<ParkingSpot> getAllParkingSpots(String loc, String oauth_consumer_key);
	
	/**Provides a list of all locations car2go is operating for like Ulm or Austin.
	 * A OAuth Consumer Key is required.
	 * http Request Type: public
	 * @param oauth_consumer_key - valid OAuth Consumer Key
	 * @return List<Location> inner type c2G.mobile.api.objekts.Location.java
	 */
	public List<Location> getAllLocations(String oauth_consumer_key);
	
	/**Provides a list of car2go gas stations in context of specific location like Ulm or Austin. 
	 * A OAuth Consumer Key is needed.
	 * http Request Type: public
	 * Can be provided as KML. (NOT YET IMPLEMENTED)
	 * @param loc - Location e.g. ulm
	 * @param oauth_consumer_key - valid OAuth Consumer Key
	 * @return List<GasStation> inner type c2G.mobile.api.objekts.GasStation.java
	 * @see More information about KML can be found at: https://developers.google.com/kml/documentation/kmlreference?hl=de
	 */
	public List<GasStation> getAllPGasStations(String loc, String oauth_consumer_key);
	
	/**Provides a list of all free car2go vehicles for a given location like Ulm or Austin.
	 * A OAuth Consumer Key is needed.
	 * http Request Type: public
	 * Can be provided as KML. (NOT YET IMPLEMENTED)
	 * @param loc - Location e.g. ulm
	 * @param oauth_consumer_key - valid OAuth Consumer Key
	 * @return List<Vehicle> inner type c2G.mobile.api.objekts.Vehicle.java
	 * @see More information about KML can be found at: https://developers.google.com/kml/documentation/kmlreference?hl=de
	 */
	public List<Vehicle> getAllFreeVehicles(String loc, String oauth_consumer_key);
	
	/**NOT YET IMPLEMENTED
	 * Provides a list of all current bookings of a user.
	 * Access to this function is restricted.
	 * See OAuth documentation for more details.
	 * http Request Type: private
	 * @param loc - Location e.g. ulm
	 * @return List<Account> inner type c2G.mobile.api.objekts.Account.java
	 */
	public List<Account> getAllAccounts(String loc);
	
	/**NOT YET IMPLEMENTED
	 * Provides a list of all current bookings of a user. 
	 * Access to this function is restricted. 
	 * See OAuth documentation for more details.
	 * http Request Type: private
	 * @param loc - Location e.g. ulm
	 * @return List<Booking> inner type c2G.mobile.api.objekts.Booking.java
	 */
	public List<Booking> getBookings(String loc);
	
	/**NOT YET IMPLEMENTED
	 * Provides the detailed information of a recently booked vehicle for the current user.
	 * The vehicle must have been assigned to the authenticated user. 
	 * Access to this function is restricted. See OAuth documentation for more details. 
	 * http Request Type: private
	 * @param loc - Location e.g. ulm
	 * @return List<Booking> inner type c2G.mobile.api.objekts.Booking.java
	 */
	public Booking getBooking(String loc);
	
	/**NOT YET IMPLEMENTED
	 * Create a new short-term booking for a user. 
	 * Access to this function is restricted. 
	 * See OAuth documentation for more details. 
	 * http Request Type: private
	 * @param loc - Location e.g. ulm
	 * @param vin - Vehicle Identification Number
	 * @param accountID - current user Account ID
	 * @return List<Booking> inner type c2G.mobile.api.objekts.Booking.java
	 */
	public List<Booking> createBooking(String loc, String vin, String accountID);
	
	/**NOT YET IMPLEMENTED
	 * This function provides cancellation of an existing booking. 
	 * Access to this function is restricted. See OAuth documentation for more details.
	 * http Request Type: private
	 * @param bookingID
	 * @return List<CanceledBooking> inner type c2G.mobile.api.objekts.CanceledBooking.java
	 */
	public CanceledBooking cancelBooking(String bookingID);
}
