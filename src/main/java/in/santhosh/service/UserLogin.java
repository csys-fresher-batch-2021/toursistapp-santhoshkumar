package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.LoginDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.ServiceException;
import in.santhosh.model.UserDetail;
import in.santhosh.validator.UserValidation;

public class UserLogin {
	private UserLogin() {

	}

	/**
	 * This method checks whether the given mobile number are matched
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return
	 */
	public static boolean validLogin(long mobileNumber, String password) {
		boolean validLogin;
		try {
			LoginDao dao = new LoginDao();
			validLogin = dao.loginDao(mobileNumber, password);
		} catch (DBException e) {
			throw new ServiceException("unable to verify user credentials");
		}
		return validLogin;

	}

	/**
	 * This method is used to get user detail
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static List<UserDetail> getUserDetail(long mobileNumber) {
		LoginDao dao = new LoginDao();
		return dao.getallDetailsOfUser(mobileNumber);

	}

	/**
	 * This method validates the new password and its update the password
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param userId
	 * @return
	 */
	public static boolean updateUserPassword(String newPassword, int userId) {
		boolean isUpdated = false;
		LoginDao dao = new LoginDao();
		if (UserValidation.isValidatePassword(newPassword)) {
			dao.updatePassword(newPassword, userId);
			isUpdated = true;
		}
		return isUpdated;
	}

	/**
	 * This method checks whether user entered old password is correct
	 * 
	 * @param userId
	 * @param oldPassword
	 * @return
	 */
	public static boolean passwordExists(int userId, String oldPassword) {
		boolean isMatched = false;
		LoginDao dao = new LoginDao();
		isMatched = dao.validOldPassword(userId, oldPassword);
		return isMatched;
	}

	/**
	 * This method is used to update the new password of user
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return
	 */
	public static boolean updateForgotPassword(long mobileNumber, String password) {
		boolean isChanged = false;
		LoginDao dao = new LoginDao();
		try {
			if (UserValidation.isValidatePassword(password)) {
				dao.updateForgotPassword(mobileNumber, password);
				isChanged = true;
			}
		} catch (DBException e) {
			throw new ServiceException("unable to change the password");
		}
		return isChanged;
	}

}
