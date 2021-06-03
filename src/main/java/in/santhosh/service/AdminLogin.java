package in.santhosh.service;

import in.santhosh.dao.AdminDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.ServiceException;
import in.santhosh.validator.UserValidation;

public class AdminLogin {
	private AdminLogin() {

	}

	/**
	 * This method is used to check whether mobile number and password are correct
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return
	 */

	public static boolean adminLogin(long mobileNumber, String password) {
		boolean validLogin = false;
		if (UserValidation.validateMobileNumber(mobileNumber) && UserValidation.isValidatePassword(password)) {
			AdminDao dao = new AdminDao();
			try {
				validLogin = dao.validAdminLogin(mobileNumber, password);
			} catch (DBException e) {
				throw new ServiceException("invalid Login credentials");
			}

		}
		return validLogin;

	}

}
