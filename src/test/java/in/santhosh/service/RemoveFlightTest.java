package in.santhosh.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import in.santhosh.model.FlightDetail;

public class RemoveFlightTest {

	@Test
	public void removeFlightWithCorrectDetail() {
		String countryName="Dubai";
		String flightName="Indigo";
		LocalTime departureTime=LocalTime.of(22, 40);
		LocalTime arrivalTime=LocalTime.of(04,40);
		String status="Return";
		String source="Dubai";
		String destination="Coimbatore";
		LocalDate date=LocalDate.of(2021, 05, 28);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		boolean validFlightDetail=Flights.removeFlight(flightDetail);
		assertTrue(validFlightDetail);
		
	}
	@Test
	public void removeFlightWithCorrectDetail1() {
		String countryName="Dubai";
		String flightName="Indigo";
		LocalTime departureTime=LocalTime.of(22, 40);
		LocalTime arrivalTime=LocalTime.of(04,40);
		String status="Return";
		String source="Dubai";
		String destination="Coimbatore";
		LocalDate date=LocalDate.of(2021, 05, 28);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		boolean validFlightDetail=Flights.removeFlight(flightDetail);
		assertFalse(validFlightDetail);
		
	}

}
