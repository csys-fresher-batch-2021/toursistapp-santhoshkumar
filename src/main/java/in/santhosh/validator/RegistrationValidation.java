package in.santhosh.validator;

import in.santhosh.exception.RegistrationValidationException;
import in.santhosh.exception.ValidationException;
import in.santhosh.model.UserDetail;

public class RegistrationValidation {
	
	private RegistrationValidation()
	{
		
	}
	/**
	 * This method is used to validate the user details
	 * @param userDetail
	 * @return
	 */
	public static boolean validRegistration(UserDetail userDetail)
	{
		boolean isValidRegistration=true;
		try {
			if (!Validation.stringValidation(userDetail.getName()) && 
					!Validation.stringValidation(userDetail.getGender())){
				isValidRegistration = false;
			}
			if(!UserValidation.validateMobileNumber(userDetail.getMobileNumber()))
			{
				isValidRegistration=false;
			}
			if(!UserValidation.isValidatePassword(userDetail.getPassword()) 
					&& !UserValidation.isValidatePassword(userDetail.getReTypePassword())) {
				isValidRegistration=false;
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new RegistrationValidationException(e.getMessage());
		}
		return isValidRegistration;
	}

}
