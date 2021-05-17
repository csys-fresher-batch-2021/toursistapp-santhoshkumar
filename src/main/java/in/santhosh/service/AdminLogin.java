package in.santhosh.service;

import java.util.HashMap;
import java.util.Map;

import in.santhosh.validator.UserValidation;

public class AdminLogin {
	private AdminLogin() {

	}

	private static final Map<Long, String> adminDetail = new HashMap<>();
	static {
		adminDetail.put(9865940407l, "Admin123");
		adminDetail.put(8778482577l, "Admin123");
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
		if (UserValidation.validateMobileNumber(mobileNumber) && UserValidation.isValidatePassword(password)
				&& adminDetail.containsKey(mobileNumber)) {
			String adminPassword = adminDetail.get(mobileNumber);
			if (adminPassword.equals(password)) {
				validLogin = true;
			}
		}
		return validLogin;

	}

}
