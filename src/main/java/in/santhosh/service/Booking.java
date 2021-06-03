package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.BookingDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.ServiceException;
import in.santhosh.model.BookingDetail;

public class Booking {
	private static final int GST = 5;

	private Booking() {

	}

	/**
	 * This method is used to total fare price with gst
	 * 
	 * @param bookingDetail
	 * @return
	 */
	public static double calculateBill(BookingDetail bookingDetail) {
		int packagePrice = bookingDetail.getPackagePrice();
		int person = bookingDetail.getNumberOfPerson();
		int totalPackagePriceWithOutGst = packagePrice * person;
		return totalPackagePriceWithOutGst + ((totalPackagePriceWithOutGst * GST) / 100.0);
	}

	/**
	 * This method is used to add the booking details
	 * 
	 * @param bookingDetail
	 * @param totalPrice
	 * @return
	 */
	public static boolean bookingPackage(BookingDetail bookingDetail, double totalPrice) {
		boolean validBooking = false;
		BookingDao dao = new BookingDao();
		try {
			dao.addBookingDetail(bookingDetail, totalPrice);
			validBooking = true;
		} catch (DBException e) {
			throw new ServiceException("unable to book package");
		}
		return validBooking;

	}

	/**
	 * This method is used to get specific user booking details
	 * 
	 * @param id
	 * @return
	 */
	public static List<BookingDetail> userBookingDetail(int id) {
		List<BookingDetail> bookingDetail = null;
		BookingDao dao = new BookingDao();
		try {
			bookingDetail = dao.userBooking(id);
		} catch (Exception e) {
			throw new ServiceException("unable to find your booking");
		}
		return bookingDetail;
	}

	/**
	 * This method is used to get all booking details
	 * 
	 * @return
	 */
	public static List<BookingDetail> listOfBookings() {
		BookingDao dao = new BookingDao();
		return dao.getAllBookings();

	}

	/**
	 * This method is used to cancel bookings
	 * 
	 * @param bookingDetail
	 * @return
	 */
	public static boolean deleteBooking(BookingDetail bookingDetail) {
		boolean isMatched = false;
		BookingDao dao = new BookingDao();
		try {
			dao.cancelBooking(bookingDetail);
			isMatched = true;

		} catch (DBException e) {
			throw new ServiceException("unable to cancel your bookings");
		}
		return isMatched;

	}

	public static void addCancelBooking(BookingDetail bookinDetail) {
		BookingDao dao = new BookingDao();
		try {
			dao.addCancelBookingDetail(bookinDetail);
		} catch (DBException e) {
			throw new ServiceException("unable to add cancel booking details");
		}

	}

	public static List<BookingDetail> listOfCancelledBooking() {
		BookingDao dao = new BookingDao();

		return dao.getAllCancelledList();

	}
}