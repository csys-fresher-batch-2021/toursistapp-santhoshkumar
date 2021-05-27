package in.santhosh.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.FlightDetail;

public class AddFlightTest {

	@Test
	public void addFlightWithCorrectDetails() {
		String countryName="Singapore";
		String flightName="Air india";
		LocalTime departureTime=LocalTime.of(21, 40);
		LocalTime arrivalTime=LocalTime.of(05,30);
		String status="Depart";
		String source="coimbatore";
		String destination="Singapore";
		LocalDate date=LocalDate.of(2021, 05, 30);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		boolean validFlightDetail=Flights.addFlights(flightDetail);
		assertEquals(true,validFlightDetail);
	}
	@Test
	public void addFlightWithWrongCountryName() {
		String countryName=" ";
		String flightName="Air india";
		LocalTime departureTime=LocalTime.of(21, 40);
		LocalTime arrivalTime=LocalTime.of(05,30);
		String status="Depart";
		String source="coimbatore";
		String destination="Singapore";
		LocalDate date=LocalDate.of(2021, 06, 01);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		try {
			Flights.addFlights(flightDetail);
		} catch (ServiceException e) {
			assertEquals("unable to add flights into the database",e.getMessage());
		}
	}
	@Test
	public void addFlightWithWrongInvalidFlightName() {
		String countryName="Malaysia";
		String flightName="";
		LocalTime departureTime=LocalTime.of(23, 40);
		LocalTime arrivalTime=LocalTime.of(07,30);
		String status="Depart";
		String source="coimbatore";
		String destination="Singapore";
		LocalDate date=LocalDate.of(2021, 06, 01);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		try {
			Flights.addFlights(flightDetail);
		} catch (ServiceException e) {
			assertEquals("unable to add flights into the database",e.getMessage());
		}
	}
	@Test
	public void addFlightWithWrongInvalidSourceName() {
		String countryName="Malaysia";
		String flightName="";
		LocalTime departureTime=LocalTime.of(23, 40);
		LocalTime arrivalTime=LocalTime.of(07,30);
		String status="Depart";
		String source="coimbatore2111";
		String destination="Singapore";
		LocalDate date=LocalDate.of(2021, 06, 01);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		try {
			Flights.addFlights(flightDetail);
		} catch (ServiceException e) {
			assertEquals("unable to add flights into the database",e.getMessage());
		}
	}

}
