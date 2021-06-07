package in.santhosh.validator;

import in.santhosh.exception.FlightValidationException;
import in.santhosh.exception.ValidationException;
import in.santhosh.model.FlightDetail;

public class FlightDetailValidation {
	private FlightDetailValidation()
	{
		
	}
	/**
	 * This method is used to validate all flight details
	 * @param flightDetail
	 * @return
	 */
	public static boolean validFlightDetail(FlightDetail flightDetail)
	{
		boolean validDetail=true;
		try {
			if(!Validation.stringValidation(flightDetail.getCountryName())) {
				validDetail=false;
			}
			if(!Validation.stringValidation(flightDetail.getFlightName())) {
				validDetail=false;
			}
			if(!Validation.stringValidation(flightDetail.getDestination()))
			{
				validDetail=false;
			}
			if(!Validation.stringValidation(flightDetail.getSource()))
			{
				validDetail=false;
			}
			if(!Validation.stringValidation(flightDetail.getStatus()))
			{
				validDetail=false;
			}
			if(!Validation.dateValidation(flightDetail.getJourneyDate())) {
				validDetail=false;
			}
		} catch (ValidationException e) {
			throw new FlightValidationException("Enter all details correctly");
		}
		return validDetail;
	}

}
