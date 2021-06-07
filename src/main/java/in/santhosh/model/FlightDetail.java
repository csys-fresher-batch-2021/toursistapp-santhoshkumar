package in.santhosh.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightDetail {

	private String countryName;
	private String flightName;
	private LocalTime departure;
	private LocalTime arrival;
	private String status;
	private String source;
	private String destination;
	private LocalDate journeyDate;

	public String getCountryName() {
		return countryName;
	}

	public String getFlightName() {
		return flightName;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public LocalTime getDeparture() {
		return departure;
	}

	public LocalTime getArrival() {
		return arrival;
	}

	public String getStatus() {
		return status;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public FlightDetail(String countryName, String flightName, LocalTime departure, LocalTime arrival, String status,
			String source, String destination, LocalDate journeyDate) {
		super();
		this.countryName = countryName;
		this.flightName = flightName;
		this.departure = departure;
		this.arrival = arrival;
		this.status = status;
		this.source = source;
		this.destination = destination;
		this.journeyDate = journeyDate;
	}

}
