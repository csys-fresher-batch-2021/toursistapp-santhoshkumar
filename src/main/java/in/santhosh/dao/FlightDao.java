package in.santhosh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import in.santhosh.exception.DBException;
import in.santhosh.model.FlightDetail;
import in.santhosh.util.ConnectionUtil;

public class FlightDao {
	/**
	 * This method adds all the flight details
	 * 
	 * @param flightDetail
	 */

	public void addFlight(FlightDetail flightDetail) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Time depatureTime = Time.valueOf(flightDetail.getDeparture());
			Time arrivalTime = Time.valueOf(flightDetail.getArrival());
			Date journeyDate = Date.valueOf(flightDetail.getJourneyDate());
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO flight_detail(country_name,flight_name,source,destination,departure_time,"
					+ "arrival_time,status,journey_date)VALUES(?,?,?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, flightDetail.getCountryName());
			pst.setString(2, flightDetail.getFlightName());
			pst.setString(3, flightDetail.getSource());
			pst.setString(4, flightDetail.getDestination());
			pst.setTime(5, depatureTime);
			pst.setTime(6, arrivalTime);
			pst.setString(7, flightDetail.getStatus());
			pst.setDate(8, journeyDate);
			pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to add flights into database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * This method returns all flight details
	 * 
	 * @return
	 */
	public List<FlightDetail> displayFlight() {
		Connection connection = null;
		PreparedStatement pst = null;
		List<FlightDetail> flightList = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM flight_detail";
			pst = connection.prepareStatement(sql);
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
				flightList.add(flightDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {

			throw new DBException("unable to fetch data flight detail from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return flightList;
	}

	/**
	 * This method is used to remove flight
	 * 
	 * @param flightDetail
	 */
	public void removePackage(FlightDetail flightDetail) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Time depatureTime = Time.valueOf(flightDetail.getDeparture());
			Time arrivalTime = Time.valueOf(flightDetail.getArrival());
			Date journeyDate = Date.valueOf(flightDetail.getJourneyDate());
			connection = ConnectionUtil.getConnection();
			String sql = "DELETE FROM flight_detail WHERE country_name=? AND flight_name=? AND source=? AND destination=? "
					+ "AND departure_time=? AND arrival_time=? AND status=? AND journey_date=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, flightDetail.getCountryName());
			pst.setString(2, flightDetail.getFlightName());
			pst.setString(3, flightDetail.getSource());
			pst.setString(4, flightDetail.getDestination());
			pst.setTime(5, depatureTime);
			pst.setTime(6, arrivalTime);
			pst.setString(7, flightDetail.getStatus());
			pst.setDate(8, journeyDate);

			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to remove flights from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method is used to get all flight details
	 * 
	 * @return
	 */
	public List<FlightDetail> getAllFlight() {
		Connection connection = null;
		PreparedStatement pst = null;
		List<FlightDetail> flight = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM flight_detail";
			pst = connection.prepareStatement(sql);
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
				flight.add(flightDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {

			throw new DBException("unable to get all flight details from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return flight;

	}

}
