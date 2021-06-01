package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.RegistrationDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.RegistrationValidationException;
import in.santhosh.exception.ServiceException;
import in.santhosh.model.UserDetail;
import in.santhosh.validator.RegistrationValidation;

public class UserRegistration {
	private UserRegistration()
	{
		
	}
	public static boolean userRegistration(UserDetail userDetail)
	{
		boolean validRegistration=false;
		try {
			if(RegistrationValidation.validRegistration(userDetail)){
				RegistrationDao dao=new RegistrationDao();
				dao.addUserDetail(userDetail);
				validRegistration=true;
			}
		} catch (DBException e) {
			throw new ServiceException("unable to register");
		}
		catch(RegistrationValidationException e)
		{
			String message=e.getMessage();
			throw new ServiceException(message);
		}
		return validRegistration;
		
	}
	public static boolean existingUser(UserDetail userDetail)
	{
		boolean isMatched=false;
			try {
				RegistrationDao dao=new RegistrationDao();
				List<UserDetail> userList=dao.getAllUser();
				for (UserDetail user : userList) {

					if (user.getMobileNumber()==userDetail.getMobileNumber()){
						isMatched = true;
						break;

					}

				}
			} catch (DBException e) {
				throw new ServiceException("unable to fetch userdetail in database");
			}
			return isMatched;
	}

}
