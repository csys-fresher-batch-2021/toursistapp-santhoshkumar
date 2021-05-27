package in.santhosh.validator;

import in.santhosh.exception.FlightValidationException;
import in.santhosh.exception.ValidationException;
import in.santhosh.model.FlightDetail;

public class FlightDetailValidation {
	private FlightDetailValidation()
	{
		
	}
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
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new FlightValidationException("Enter all details correctly");
		}
		return validDetail;
	}

}
