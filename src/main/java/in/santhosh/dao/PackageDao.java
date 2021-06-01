package in.santhosh.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import in.santhosh.exception.DBException;
import in.santhosh.model.FlightDetail;
import in.santhosh.model.TourPackageDetail;
import in.santhosh.util.ConnectionUtil;

public class PackageDao {
	private static final String END_DATE = "end_date";
	private static final String START_DATE = "start_date";
	private static final String NUMBER_OF_DAYS = "number_of_days";
	private static final String PACKAGE_PRICE = "package_price";
	private static final String PACKAGE_NAME = "package_name";
	private static final String HOTEL_NAME="hotel_name";

	/**
	 * This method is use to add package into database
	 * 
	 * @param packages
	 */
	public void addPackage(TourPackageDetail packages) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(packages.getStartDate());
			Date endDate = Date.valueOf(packages.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "insert into package_detail(package_name,package_price,number_of_days,start_date,end_date,hotel_name) values(?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packages.getPackageName());
			pst.setInt(2, packages.getPackagePrice());
			pst.setInt(3, packages.getNumberOfDays());
			pst.setDate(4, startDate);
			pst.setDate(5, endDate);
			pst.setString(6,packages.getHotelName());
			pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Package cannot be added to the database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * This method is used to fetch all packageDetail from database
	 * 
	 * @return
	 */
	public List<TourPackageDetail> displayPackage() {
		List<TourPackageDetail> packageDetails = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select* from package_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				String hotelName=rs.getString(HOTEL_NAME);

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate,hotelName);
				packageDetails.add(packages);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch records from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

		return packageDetails;

	}

	/**
	 * This method is used to remove package from database
	 * 
	 * @param packageDetail
	 */
	public void removePackage(TourPackageDetail packageDetail) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(packageDetail.getStartDate());
			Date endDate = Date.valueOf(packageDetail.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "delete from package_detail where package_name=? AND package_price=? AND number_of_days=? AND start_date =? AND end_date=? AND hotel_name=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packageDetail.getPackageName());
			pst.setInt(2, packageDetail.getPackagePrice());
			pst.setInt(3, packageDetail.getNumberOfDays());
			pst.setDate(4, startDate);
			pst.setDate(5, endDate);
			pst.setString(6,packageDetail.getHotelName());
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Cannot delete the package from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * This method is used to return all the package details from database
	 */

	public List<TourPackageDetail> getAllPackages() {
		Connection connection = null;
		PreparedStatement pst = null;

		List<TourPackageDetail> packageDetails = new ArrayList<>();

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "Select * from package_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				String hotelName=rs.getString(HOTEL_NAME);

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate,hotelName);
				packageDetails.add(packages);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Cannot get all the records from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

		return packageDetails;
	}

	/**
	 * This method is used to fetch packages by country name from database
	 * 
	 * @return
	 */
	public List<TourPackageDetail> searchPackageByCountryName(String countryName) {
		Connection connection = null;
		PreparedStatement pst = null;

		List<TourPackageDetail> packageDetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "Select * from package_detail where package_name=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				String hotelName=rs.getString(HOTEL_NAME);


				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate,hotelName);
				packageDetails.add(packages);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Cannot fetch package detial by country name  from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return packageDetails;
	}

	/**
	 * This method used to fetch packages by price from database
	 * 
	 * @param price
	 * @return
	 */
	public List<TourPackageDetail> searchPackageByPrice(int price) {
		Connection connection = null;
		PreparedStatement pst = null;

		List<TourPackageDetail> packageDetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "Select * from package_detail where package_price=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, price);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				String hotelName=rs.getString(HOTEL_NAME);


				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate,hotelName);
				packageDetails.add(packages);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Cannot fetch package detail by price from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return packageDetails;
	}

	/**
	 * This method used to fetch packages by number of days from database
	 * 
	 * @param price
	 * @return
	 */
	public List<TourPackageDetail> searchPackageByDays(int days) {
		Connection connection = null;
		PreparedStatement pst = null;

		List<TourPackageDetail> packageDetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "Select * from package_detail where number_of_days=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, days);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				String hotelName=rs.getString(HOTEL_NAME);


				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate,hotelName);
				packageDetails.add(packages);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Cannot fetch package detail by price from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return packageDetails;
	}

	/**
	 * This method is used to fetch flight detail for user selected package
	 * 
	 * @param packageDetail
	 * @param previousDate
	 * @return
	 */
	public List<FlightDetail> getFlightDetails(TourPackageDetail packageDetail, LocalDate previousDate) {
		Connection connection = null;
		PreparedStatement pst = null;
		List<FlightDetail> flightDetails = new ArrayList<>();
		try {
			Date startDate = Date.valueOf(previousDate);
			connection = ConnectionUtil.getConnection();
			String sql = "select * from flight_detail where country_name=? and journey_date=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packageDetail.getPackageName());
			pst.setDate(2, startDate);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String countryName = rs.getString("country_name");
				String flightName = rs.getString("flight_name");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				LocalTime departureTime = rs.getTime("departure_time").toLocalTime();
				LocalTime arrivalTime = rs.getTime("arrival_time").toLocalTime();
				String status = rs.getString("status");
				LocalDate date = rs.getDate("journey_date").toLocalDate();
				FlightDetail flightDetail = new FlightDetail(countryName, flightName, departureTime, arrivalTime,
						status, source, destination, date);
				flightDetails.add(flightDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Cannot fetch flight detail from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return flightDetails;

	}

	public List<FlightDetail> getFlightReturnDetails(TourPackageDetail packageDetail, String flightStatus) {
		Connection connection = null;
		PreparedStatement pst = null;
		List<FlightDetail> flightDetails = new ArrayList<>();
		try {
			Date startDate = Date.valueOf(packageDetail.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "select * from flight_detail where country_name=? and journey_date=? and status=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packageDetail.getPackageName());
			pst.setDate(2, startDate);
			pst.setString(3, flightStatus);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String countryName = rs.getString("country_name");
				String flightName = rs.getString("flight_name");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				LocalTime departureTime = rs.getTime("departure_time").toLocalTime();
				LocalTime arrivalTime = rs.getTime("arrival_time").toLocalTime();
				String status = rs.getString("status");
				LocalDate date = rs.getDate("journey_date").toLocalDate();
				FlightDetail flightDetail = new FlightDetail(countryName, flightName, departureTime, arrivalTime,
						status, source, destination, date);
				flightDetails.add(flightDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Cannot fetch flight detail from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return flightDetails;
	}

	/**
	 * This method is used to add country image
	 * 
	 * @param countryName
	 * @param imageLocation
	 */
	public void addImage(String countryName, String imageLocation) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtil.getConnection();
			File file = new File("E:\\projectimage\\" + imageLocation);
			FileInputStream fis = new FileInputStream(file);
			ps = connection.prepareStatement("INSERT INTO image_detail VALUES (?, ?)");
			ps.setString(1, countryName);
			ps.setBinaryStream(2, fis, file.length());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(ps, connection);
		}
	}

	/**
	 * This method is used to retrieve image from database
	 * 
	 * @param countryName
	 * @return
	 */
	public byte[] retireveImage(String countryName) {
		Connection connection = null;
		Statement st = null;
		byte[] imgBytes = null;
		try {
			connection = ConnectionUtil.getConnection();
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT image FROM image_detail WHERE country_name ='" + countryName + "'");
			if (rs != null) {
				while (rs.next()) {
					imgBytes = rs.getBytes(1);
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(st, connection);
		}
		return imgBytes;

	}
}
