package in.santhosh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.santhosh.exception.DBException;
import in.santhosh.model.BookingDetail;
import in.santhosh.util.ConnectionUtil;

public class BookingDao {
	private static final String TOTAL_PRICE = "total_price";
	private static final String NUMBER_OF_PERSONS = "number_of_persons";
	private static final String END_DATE = "end_date";
	private static final String START_DATE = "start_date";
	private static final String NUMBER_OF_DAYS = "number_of_days";
	private static final String PACKAGE_PRICE = "package_price";
	private static final String PACKAGE_NAME = "package_name";
	private static final String USER_ID = "user_id";
	private static final String STATUS = "status";
	private static final String COMMENT="comment";

	/**
	 * This method is used to add all booking details into database
	 * 
	 * @param bookingDetail
	 * @param totalPrice
	 */

	public void addBookingDetail(BookingDetail bookingDetail, double totalPrice,String status,String comment ) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(bookingDetail.getStartDate());
			Date endDate = Date.valueOf(bookingDetail.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO booking_detail(user_id,package_name,package_price,number_of_days,start_date,"
					+ "end_date,number_of_persons,total_price,status,comment)VALUES(?,?,?,?,?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, bookingDetail.getId());
			pst.setString(2, bookingDetail.getPackageName());
			pst.setInt(3, bookingDetail.getPackagePrice());
			pst.setInt(4, bookingDetail.getNumberOfDays());
			pst.setDate(5, startDate);
			pst.setDate(6, endDate);
			pst.setInt(7, bookingDetail.getNumberOfPerson());
			pst.setFloat(8, (float) totalPrice);
			pst.setString(9,status);
			pst.setString(10,comment);
			pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to add booking details into database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method used to get user booking details
	 * 
	 * @param id
	 * @return
	 */
	public List<BookingDetail> userBooking(int id) {
		Connection connection = null;
		PreparedStatement pst = null;
		List<BookingDetail> details = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT* FROM booking_detail WHERE user_id=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(USER_ID);
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				int numberOfPersons = rs.getInt(NUMBER_OF_PERSONS);
				double price = rs.getFloat(TOTAL_PRICE);
				String status=rs.getString(STATUS);
				String comment=rs.getString(COMMENT);
				BookingDetail bookingDetail = new BookingDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate, userId, numberOfPersons, price,status,comment);
				details.add(bookingDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch user booking");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return details;

	}

	/**
	 * This method is used to get all booking details
	 * 
	 * @return
	 */
	public List<BookingDetail> getAllBookings() {
		Connection connection = null;
		PreparedStatement pst = null;
		List<BookingDetail> allBookingdetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT* FROM booking_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(USER_ID);
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				int numberOfPersons = rs.getInt(NUMBER_OF_PERSONS);
				double price = rs.getFloat(TOTAL_PRICE);
				String status=rs.getString(STATUS);
				String comment=rs.getString(COMMENT);
				BookingDetail bookingDetail = new BookingDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate, userId, numberOfPersons, price,status,comment);
				allBookingdetails.add(bookingDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch all booking details");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return allBookingdetails;

	}

	/**
	 * This method used to cancel booking for user
	 * 
	 * @param bookingDetail
	 */
	public void cancelBooking(BookingDetail bookingDetail) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(bookingDetail.getStartDate());
			Date endDate = Date.valueOf(bookingDetail.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "DELETE FROM booking_detail WHERE user_id=? AND package_name=? AND package_price=? AND "
					+ "number_of_days=? AND start_date=? AND end_date=? AND number_of_persons=? AND total_price=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, bookingDetail.getId());
			pst.setString(2, bookingDetail.getPackageName());
			pst.setInt(3, bookingDetail.getPackagePrice());
			pst.setInt(4, bookingDetail.getNumberOfDays());
			pst.setDate(5, startDate);
			pst.setDate(6, endDate);
			pst.setInt(7, bookingDetail.getNumberOfPerson());
			pst.setFloat(8, (float) bookingDetail.getTotalPrice());
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to delete booking in database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method is used to add cancelled list for admin
	 * 
	 * @param bookingDetail
	 */

	public void addCancelBookingDetail(BookingDetail bookingDetail) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(bookingDetail.getStartDate());
			Date endDate = Date.valueOf(bookingDetail.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO cancelled_booking(user_id,package_name,package_price,number_of_days,start_date,"
					+ "end_date,number_of_persons,total_price)VALUES(?,?,?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, bookingDetail.getId());
			pst.setString(2, bookingDetail.getPackageName());
			pst.setInt(3, bookingDetail.getPackagePrice());
			pst.setInt(4, bookingDetail.getNumberOfDays());
			pst.setDate(5, startDate);
			pst.setDate(6, endDate);
			pst.setInt(7, bookingDetail.getNumberOfPerson());
			pst.setFloat(8, (float) bookingDetail.getTotalPrice());
			pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to add cancel booking details into database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method is used to get all cancelled list.
	 * 
	 * @return
	 */
	public List<BookingDetail> getAllCancelledList() {
		Connection connection = null;
		PreparedStatement pst = null;
		List<BookingDetail> allCancelledBookingdetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT* FROM cancelled_booking";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(USER_ID);
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				int numberOfPersons = rs.getInt(NUMBER_OF_PERSONS);
				double price = rs.getFloat(TOTAL_PRICE);
				String status = rs.getString(STATUS);
				BookingDetail bookingDetail = new BookingDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate, userId, numberOfPersons, price, status);
				allCancelledBookingdetails.add(bookingDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch all booking details");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return allCancelledBookingdetails;

	}
	/**
	 * This method is used to update the cancelled status
	 * @param countryName
	 * @param status
	 */
	public void updateTourStatus(String countryName,String status,String comment,LocalDate date)
	{
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(date);
			connection =ConnectionUtil.getConnection();
			String sql="UPDATE booking_detail SET status=?,comment=? WHERE package_name=? AND start_date >?";
			pst=connection.prepareStatement(sql);
			pst.setString(1,status);
			pst.setString(2,comment);
			pst.setString(3,countryName);
			pst.setDate(4, startDate);
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to update status");
		}
		finally {
			ConnectionUtil.close(pst, connection);
		}
	}
}
