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
	public void addContactUsDetail(ContactUsDetails details) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(details.getStartDate());
			Date endDate = Date.valueOf(details.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "insert into contact_detail(name,mobile_number,country_name,package_price,start_date,end_date)values(?,?,?,?,?,?)";
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

	public boolean existsEnquiryDetail(ContactUsDetails details) {
		boolean isExistsEnquiry = false;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(details.getStartDate());
			Date endDate = Date.valueOf(details.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "select name from contact_detail where name=? and mobile_number=? and country_name=? and package_price=? and start_date=? and end_date=?";
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
			String sql = "select* from contact_detail";
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
