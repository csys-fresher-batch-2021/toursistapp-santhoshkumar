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
	private static final String HOTEL_NAME = "hotel_name";

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
			String sql = "INSERT INTO package_detail(package_name,package_price,number_of_days,start_date,end_date,"
					+ "hotel_name)VALUES(?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packages.getPackageName());
			pst.setInt(2, packages.getPackagePrice());
			pst.setInt(3, packages.getNumberOfDays());
			pst.setDate(4, startDate);
			pst.setDate(5, endDate);
			pst.setString(6, packages.getHotelName());
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
			String sql = "SELECT package_name,package_price,number_of_days,start_date,"
					+ "end_date,hotel_name FROM package_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				String hotelName = rs.getString(HOTEL_NAME);

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate, hotelName);
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
			String sql = "DELETE FROM package_detail WHERE package_name=? AND package_price=? AND number_of_days=? "
					+ "AND start_date =? AND end_date=? AND hotel_name=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packageDetail.getPackageName());
			pst.setInt(2, packageDetail.getPackagePrice());
			pst.setInt(3, packageDetail.getNumberOfDays());
			pst.setDate(4, startDate);
			pst.setDate(5, endDate);
			pst.setString(6, packageDetail.getHotelName());
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
			String sql = "SELECT package_name,package_price,number_of_days,"
					+ "start_date,end_date,hotel_name FROM package_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString(PACKAGE_NAME);
				int packagePrice = rs.getInt(PACKAGE_PRICE);
				int numberOfDays = rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate = rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate = rs.getDate(END_DATE).toLocalDate();
				String hotelName = rs.getString(HOTEL_NAME);

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate, hotelName);
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
			String sql = "SELECT country_name,flight_name,source,destination,departure_time,"
					+ "arrival_time,status,journey_date FROM flight_detail WHERE country_name=? AND journey_date=?";
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

	/**
	 * This method is used to get all return flight details
	 * 
	 * @param packageDetail
	 * @param flightStatus
	 * @return
	 */

	public List<FlightDetail> getFlightReturnDetails(TourPackageDetail packageDetail, String flightStatus) {
		Connection connection = null;
		PreparedStatement pst = null;
		List<FlightDetail> flightDetails = new ArrayList<>();
		try {
			Date startDate = Date.valueOf(packageDetail.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT country_name,flight_name,source,destination,"
					+ "departure_time,arrival_time,status,journey_date FROM flight_detail "
					+ "WHERE country_name=? AND journey_date=? AND status=?";
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

	/**
	 * This method is used to add hotel name
	 * 
	 * @param countryName
	 * @param imageLocation
	 */
	public void addHotelImage(String hotelName, String imageLocation) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtil.getConnection();
			File file = new File("E:\\projectimage\\" + imageLocation);
			FileInputStream fis = new FileInputStream(file);
			ps = connection.prepareStatement("INSERT INTO hotel_image VALUES (?, ?)");
			ps.setString(1, hotelName);
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
	public byte[] retireveHotelImage(String hotelName) {
		Connection connection = null;
		Statement st = null;
		byte[] imgBytes = null;
		try {
			connection = ConnectionUtil.getConnection();
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT image FROM hotel_image WHERE hotel_name ='" + hotelName + "'");
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
	/**
	 * This method is used to filter the package for easy access
	 * @param countryName
	 * @param packagePrice
	 * @param days
	 * @return
	 */
	public List<TourPackageDetail> customSearchPackage(String countryName,int packagePrice,int days)
	{
		Connection connection = null;
		PreparedStatement pst = null;
		List<TourPackageDetail> packageDetail=new ArrayList<>();
		try {
			connection=ConnectionUtil.getConnection();
			String sql;
			if(countryName!=null && packagePrice==0 && days==0)
			{
				sql="SELECT package_name,package_price,number_of_days,"
						+ "start_date,end_date,hotel_name FROM package_detail WHERE package_name=?";
				pst=connection.prepareStatement(sql);
				pst.setString(1,countryName);
			}
			else if(countryName==null && packagePrice!=0 && days==0)
			{
				sql="SELECT package_name,package_price,number_of_days,"
						+ "start_date,end_date,hotel_name FROM package_detail WHERE package_price=?";
				pst=connection.prepareStatement(sql);
				pst.setInt(1,packagePrice);
			}
			else if(countryName==null && packagePrice==0 && days!=0)
			{
				sql="SELECT package_name,package_price,number_of_days,"
						+ "start_date,end_date,hotel_name FROM package_detail WHERE number_of_days=?";
				pst=connection.prepareStatement(sql);
				pst.setInt(1,days);
			}
			else if(countryName!=null && packagePrice>0 && days==0)
			{
				sql="SELECT package_name,package_price,number_of_days,"
						+ "start_date,end_date,hotel_name FROM package_detail WHERE package_name=? AND package_price=?";
				pst=connection.prepareStatement(sql);
				pst.setString(1,countryName);
				pst.setInt(2,packagePrice);
			}
			else if(countryName!=null && packagePrice==0 && days!=0)
			{
				sql="SELECT package_name,package_price,number_of_days,"
						+ "start_date,end_date,hotel_name FROM package_detail WHERE package_name=? AND number_of_days=?";
				pst=connection.prepareStatement(sql);
				pst.setString(1,countryName);
				pst.setInt(2,days);
			}
			else if(countryName==null && packagePrice!=0 && days!=0)
			{
				sql="SELECT package_name,package_price,number_of_days,"
						+ "start_date,end_date,hotel_name FROM package_detail WHERE package_price=? AND number_of_days=?";
				pst=connection.prepareStatement(sql);
				pst.setInt(1,packagePrice);
				pst.setInt(2,days);
			}
			else if(countryName!=null && packagePrice!=0 && days!=0)
			{
				sql="SELECT package_name,package_price,number_of_days,"
						+ "start_date,end_date,hotel_name FROM package_detail "
						+ "WHERE package_name=? AND package_price=? AND number_of_days=?";
				pst=connection.prepareStatement(sql);
				pst.setString(1,countryName);
				pst.setInt(2,packagePrice);
				pst.setInt(3,days);
			}
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String packageName=rs.getString(PACKAGE_NAME);
				int totalPrice=rs.getInt(PACKAGE_PRICE);
				int totalDays=rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate=rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate=rs.getDate(END_DATE).toLocalDate();
				String hotelName=rs.getString(HOTEL_NAME);
				TourPackageDetail detail=new TourPackageDetail(packageName,totalPrice,totalDays,startDate,endDate,hotelName);
				packageDetail.add(detail);
			}
			
		}
		catch(ClassNotFoundException | SQLException e) {
			throw new DBException("unable to fetch package detail");
		}
		 finally {
				ConnectionUtil.close(pst, connection);
		}
		return packageDetail;
	}
	/**
	 * This package is used to search package by country name
	 * @param countryName
	 * @return
	 */
	public List<TourPackageDetail> searchPackageByCountry(String countryName) {
		Connection connection = null;
		PreparedStatement pst = null;
		List<TourPackageDetail> packageDetail=new ArrayList<>();
		try {
			connection =ConnectionUtil.getConnection();
			String sql="SELECT package_name,package_price,number_of_days,"
					+ "start_date,end_date,hotel_name FROM package_detail WHERE package_name=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1,countryName);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String packageName=rs.getString(PACKAGE_NAME);
				int totalPrice=rs.getInt(PACKAGE_PRICE);
				int totalDays=rs.getInt(NUMBER_OF_DAYS);
				LocalDate startDate=rs.getDate(START_DATE).toLocalDate();
				LocalDate endDate=rs.getDate(END_DATE).toLocalDate();
				String hotelName=rs.getString(HOTEL_NAME);
				TourPackageDetail detail=new TourPackageDetail(packageName,totalPrice,totalDays,startDate,endDate,hotelName);
				packageDetail.add(detail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to search package by country");
		}
		finally {
			ConnectionUtil.close(pst, connection);
	}
		return packageDetail;
		
	}
}
