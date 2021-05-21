package in.santhosh.validator;

public class UserValidation {

	private UserValidation() {

	}

	/**
	 * This method is used to validate the user mobile number
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean validateMobileNumber(long mobileNumber) {
		boolean validMobileNumber = true;

		int mobileNumerLength = 10;
		char firstCharacterForMobileNumber = '6';
		String mobileNumberString = String.valueOf(mobileNumber);
		for (int index = 1; index < mobileNumberString.length(); index++) {
			if (mobileNumberString.length() != mobileNumerLength
					|| mobileNumberString.charAt(0) < firstCharacterForMobileNumber
					|| !(mobileNumberString.charAt(index) >= '0' && mobileNumberString.charAt(index) <= '9')) {
				validMobileNumber = false;
			}
		}
		return validMobileNumber;
	}

	/**
	 * This method is used to
	 * 
	 * @param password
	 * @return
	 */
	public static boolean isValidatePassword(String password) {
		int passwordLength = 8;
		boolean validPassword = true;
		if (password.trim().length() < passwordLength || !(password.charAt(0) >= 'A' && password.charAt(0) <= 'Z')) {
			validPassword = false;
		}
		return validPassword;

	}

}
