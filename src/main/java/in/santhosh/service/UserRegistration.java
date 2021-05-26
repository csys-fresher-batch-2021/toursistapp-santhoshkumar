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
		} catch (DBException| RegistrationValidationException e) {
			throw new ServiceException("unable to register");
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

					if (user.getName().equalsIgnoreCase(userDetail.getName())
							&& user.getAge() == userDetail.getAge()
							&& user.getGender().equalsIgnoreCase(userDetail.getGender())
							&& user.getMobileNumber()==userDetail.getMobileNumber()
							&& user.getPassword().equals(userDetail.getPassword())
							&& user.getReTypePassword().equals(userDetail.getReTypePassword())){
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
