package in.santhosh.validator;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import in.santhosh.exception.FlightValidationException;
import in.santhosh.model.FlightDetail;

public class FlightDetailValidationTest {

	@Test
	public void FlightDetailValidationWithCorrectDetail() {
		String countryName="Singapore";
		String flightName="Air india";
		LocalTime departureTime=LocalTime.of(21, 40);
		LocalTime arrivalTime=LocalTime.of(05,30);
		String status="Depart";
		String source="coimbatore";
		String destination="Singapore";
		LocalDate date=LocalDate.of(2021, 05, 30);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		boolean validFlightDetail=FlightDetailValidation.validFlightDetail(flightDetail);
		assertTrue(validFlightDetail);
		
	}
	@Test
	public void FlightDetailValidationWithInvalidCountryName() {
		String countryName="";
		String flightName="Air india";
		LocalTime departureTime=LocalTime.of(23, 40);
		LocalTime arrivalTime=LocalTime.of(07,30);
		String status="Depart";
		String source="coimbatore";
		String destination="Singapore";
		LocalDate date=LocalDate.of(2021, 05, 30);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		try {
			FlightDetailValidation.validFlightDetail(flightDetail);
		} catch (FlightValidationException e) {
			assertEquals("Enter all details correctly",e.getMessage());
		}
		
	}@Test
	public void FlightDetailValidationWithInvalidFlightName() {
		String countryName="Malaysia";
		String flightName="AirIn343dia";
		LocalTime departureTime=LocalTime.of(22, 40);
		LocalTime arrivalTime=LocalTime.of(05,30);
		String status="Depart";
		String source="coimbatore";
		String destination="Singapore";
		LocalDate date=LocalDate.of(2021, 05, 30);
		FlightDetail flightDetail=new FlightDetail(countryName,flightName,departureTime,arrivalTime,status,source,destination,date);
		try {
			FlightDetailValidation.validFlightDetail(flightDetail);
		} catch (FlightValidationException e) {
			assertEquals("Enter all details correctly",e.getMessage());
		}
		
	}

}
