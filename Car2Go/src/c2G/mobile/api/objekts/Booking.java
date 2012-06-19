package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class Booking {

	Account account;
	String bookingId;
	Position bookingPosition;
	Vehicle vehicle;
	ReservationTime reservationTime;
	
	public Booking(Account account, String bookingId, Position bookingPosition,
			Vehicle vehicle, ReservationTime reservationTime) {
		this.account = account;
		this.bookingId = bookingId;
		this.bookingPosition = bookingPosition;
		this.vehicle = vehicle;
		this.reservationTime = reservationTime;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Position getBookingPosition() {
		return bookingPosition;
	}

	public void setBookingPosition(Position bookingPosition) {
		this.bookingPosition = bookingPosition;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ReservationTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(ReservationTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	
	
}
