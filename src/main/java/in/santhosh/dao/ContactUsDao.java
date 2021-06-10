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
import in.santhosh.model.ContactUsDetails;
import in.santhosh.util.ConnectionUtil;

public class ContactUsDao {
	/**
	 * This method is used to add contact detail into the database
	 * 
	 * @param details
	 */
	public void addContactUsDetail(ContactUsDetails details) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(details.getStartDate());
			Date endDate = Date.valueOf(details.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO contact_detail(name,mobile_number,country_name,package_price,start_date,"
					+ "end_date)VALUES(?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, details.getName());
			pst.setLong(2, details.getMobileNumber());
			pst.setString(3, details.getCountryName());
			pst.setInt(4, details.getPackagePrice());
			pst.setDate(5, startDate);
			pst.setDate(6, endDate);
			pst.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new DBException("unable to add details into database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * This method is used to find whether contact detail is already exists for the
	 * same package
	 * 
	 * @param details
	 * @return
	 */
	public boolean existsEnquiryDetail(ContactUsDetails details) {
		boolean isExistsEnquiry = false;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(details.getStartDate());
			Date endDate = Date.valueOf(details.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT name FROM contact_detail WHERE name=? AND mobile_number=? AND country_name=? "
					+ "AND package_price=? AND start_date=? AND end_date=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, details.getName());
			pst.setLong(2, details.getMobileNumber());
			pst.setString(3, details.getCountryName());
			pst.setInt(4, details.getPackagePrice());
			pst.setDate(5, startDate);
			pst.setDate(6, endDate);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				isExistsEnquiry = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new DBException("unable to fetch details into database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return isExistsEnquiry;
	}

	/**
	 * This method used to fetch all enquiry details from database
	 * 
	 * @return
	 * @return
	 */
	public List<ContactUsDetails> displayEnquiry() {
		Connection connection = null;
		PreparedStatement pst = null;
		List<ContactUsDetails> userSelectedDetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT* FROM contact_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				long mobileNumber = rs.getLong("mobile_number");
				String countryName = rs.getString("country_name");
				int packagePrice = rs.getInt("package_price");
				LocalDate startDate = rs.getDate("start_date").toLocalDate();
				LocalDate endDate = rs.getDate("end_date").toLocalDate();
				ContactUsDetails details = new ContactUsDetails(name, mobileNumber, countryName, packagePrice,
						startDate, endDate);
				userSelectedDetails.add(details);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch all details");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return userSelectedDetails;

	}
}
