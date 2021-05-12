package in.santhosh.validator;

import java.time.LocalDate;
import java.time.LocalTime;

public class Validation {
	private Validation() {

	}

	static LocalDate date = LocalDate.now();
	static LocalTime time = LocalTime.now();

	/**
	 * This method validates string
	 * 
	 * @param validateString
	 * @return
	 */

	public static boolean stringValidation(String validateString) {
		boolean isValidString = true;
		if (validateString.equals(" ") || validateString.equals("")) {
			isValidString = false;
		}
		for (int index = 0; index < validateString.length(); index++) {
			if (validateString.charAt(index) >= '0' && validateString.charAt(index) <= '9') {
				isValidString = false;
			}
		}

		return isValidString;
	}

	/**
	 * This method validates the journey start date
	 * 
	 * @param validateDate
	 * @return
	 */
	public static boolean dateValidation(LocalDate validateDate) {
		boolean isValidStartDate = true;
		if (validateDate.isBefore(date)) {
			isValidStartDate = false;
		}
		return isValidStartDate;

	}

	/**
	 * This method validates the journey end date
	 * 
	 * @param startDate
	 * @param validateDate
	 * @return
	 */
	public static boolean dateValidationEnd(LocalDate startDate, LocalDate validateDate) {
		boolean isValidateEndDate = true;
		if (validateDate.isBefore(startDate)) {
			isValidateEndDate = false;
		}
		return isValidateEndDate;

	}

	/**
	 * This method validates the flight time
	 * 
	 * @param flightTime
	 * @return
	 */
	public static boolean timeValidation(LocalTime flightTime) {
		boolean isValidTime = true;
		if (flightTime.isBefore(time)) {
			isValidTime = false;
		}
		return isValidTime;
	}

	/**
	 * This method is used to validate no of passengers
	 */
	public static boolean dayValidation(int days) {
		boolean isValidNumberOfDays = true;
		if (days <= 0) {
			isValidNumberOfDays = false;
		}
		return isValidNumberOfDays;

	}

}
