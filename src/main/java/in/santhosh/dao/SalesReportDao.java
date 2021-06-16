package in.santhosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.santhosh.exception.DBException;
import in.santhosh.model.BookingDetail;
import in.santhosh.model.SalesDetail;
import in.santhosh.util.ConnectionUtil;

public class SalesReportDao {
	/**
	 * This method is used to find the total number of booking for specific country
	 * 
	 * @return
	 */
	public List<SalesDetail> totalSalesReport() {
		Connection connection = null;
		PreparedStatement pst = null;
		List<SalesDetail> details = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT package_name,COUNT(package_name) AS total_booking FROM booking_detail "
					+ "GROUP BY package_name ORDER BY total_booking DESC";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String countryName = rs.getString("package_name");
				int totalBooking = rs.getInt("total_booking");
				SalesDetail salesDetail = new SalesDetail(countryName, totalBooking);
				details.add(salesDetail);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch sales details");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return details;

	}

	/**
	 * This method is used to get report of specific country
	 * 
	 * @param countryName
	 * @return
	 */

	public List<BookingDetail> countryReport(String countryName) {
		Connection connection = null;
		PreparedStatement pst = null;
		List<BookingDetail> details = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM booking_detail WHERE package_name=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String packageName = rs.getString("package_name");
				int packagePrice = rs.getInt("package_price");
				int numberOfDays = rs.getInt("number_of_days");
				LocalDate startDate = rs.getDate("start_date").toLocalDate();
				LocalDate endDate = rs.getDate("end_date").toLocalDate();
				int numberOfPersons = rs.getInt("number_of_persons");
				double price = rs.getFloat("total_price");
				BookingDetail bookingDetail = new BookingDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate, userId, numberOfPersons, price);
				details.add(bookingDetail);

			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch the sales details");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return details;

	}

	/**
	 * This method is used to get total booking price for specific country
	 * 
	 * @param countryName
	 * @return
	 */
	public int countryTotalBookingPrice(String countryName) {
		Connection connection = null;
		PreparedStatement pst = null;
		int totalPrice = 0;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT  SUM(total_price) AS total_price FROM  booking_detail WHERE package_name=? AND status=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2,"Confirmed");
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				totalPrice = rs.getInt("total_price");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch the total booking price ");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return totalPrice;

	}

}
