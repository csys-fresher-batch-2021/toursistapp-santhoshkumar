package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.FlightDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.FlightValidationException;
import in.santhosh.exception.ServiceException;
import in.santhosh.model.FlightDetail;
import in.santhosh.validator.FlightDetailValidation;


public class Flights {
	private Flights()
	{
		
	}
	/**
	 * This method is used to add flight
	 * @param flightDetail
	 * @return
	 */
	public static boolean addFlights(FlightDetail flightDetail) {
		boolean validFlightDetail=false;
		try {
			if(FlightDetailValidation.validFlightDetail(flightDetail)) {
				FlightDao dao=new FlightDao();
				dao.addFlight(flightDetail);
				validFlightDetail=true;
			}
		}catch(DBException | FlightValidationException e) {
			throw new ServiceException("unable to add flights into the database");
			
		}
		return validFlightDetail;
	}
	/**
	 * This method used to display flight details
	 * @return
	 */
	public static List<FlightDetail> displayFlight() {
		FlightDao dao = new FlightDao();
		List<FlightDetail> flightList;
		try {
			flightList = dao.displayFlight();
		} catch (ServiceException e) {
			throw new ServiceException("unable to get all flight details");
		}
		return flightList;
	}
	/**
	 * This method used to remove flights
	 * @param flightDetail
	 * @return
	 */
	
	public static boolean removeFlight(FlightDetail flightDetail)
	{
		boolean isMatched=false;
		try {
			FlightDao dao=new FlightDao();
			List<FlightDetail> flightList=dao.getAllFlight();
			for(FlightDetail flights:flightList)
			{
				if(flights.getCountryName().equals(flightDetail.getCountryName())
						&& flights.getFlightName().equalsIgnoreCase(flightDetail.getFlightName())
						&& flights.getSource().equalsIgnoreCase(flightDetail.getSource())
						&& flights.getDestination().equalsIgnoreCase(flightDetail.getDestination())
						&& flights.getDeparture().equals(flightDetail.getDeparture())
						&&flights.getArrival().equals(flightDetail.getArrival())
						&&flights.getStatus().equals(flightDetail.getStatus())
						&&flights.getJourneyDate().equals(flightDetail.getJourneyDate()))
				{
					isMatched=true;
				}
						
			}
			if(isMatched)
			{
				dao.removePackage(flightDetail);
			}
		} catch (ServiceException e) {
			throw new ServiceException("unable to remove flights");
		}
		return isMatched;
		
	}
	public static boolean existingFlightDetail(FlightDetail flightDetail)
	{
		boolean isExists=false;
		try {
			FlightDao dao=new FlightDao();
			List<FlightDetail> flightList=dao.getAllFlight();
			for(FlightDetail flights:flightList)
			{
				if(flights.getCountryName().equals(flightDetail.getCountryName())
						&& flights.getFlightName().equalsIgnoreCase(flightDetail.getFlightName())
						&& flights.getSource().equalsIgnoreCase(flightDetail.getSource())
						&& flights.getDestination().equalsIgnoreCase(flightDetail.getDestination())
						&& flights.getDeparture().equals(flightDetail.getDeparture())
						&&flights.getArrival().equals(flightDetail.getArrival())
						&&flights.getStatus().equals(flightDetail.getStatus())
						&&flights.getJourneyDate().equals(flightDetail.getJourneyDate()))
				{
					isExists=true;
				}
						
			}
		} catch (ServiceException e) {
			throw new ServiceException("unable to remove flights");
		}
		return isExists;
		
	}

}
