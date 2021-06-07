package in.santhosh.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import in.santhosh.exception.ValidationException;

public class Validation {
	private Validation() {

	}

	/**
	 * This method validates string
	 * 
	 * @param validateString
	 * @return
	 */

	public static boolean stringValidation(String validateString) {
		boolean isValidString = true;
		if (validateString.equals(" ") || validateString.equals("")) {
			throw new ValidationException("Entered value is null");
		}
		for (int index = 0; index < validateString.length(); index++) {
			if (validateString.charAt(index) >= '0' && validateString.charAt(index) <= '9') {
				throw new ValidationException("Entered value contains number");
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
		LocalDate date = LocalDate.now();
		boolean isValidStartDate = true;
		if (validateDate.isBefore(date)) {
			throw new ValidationException("Enter start date is before current date");
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
			throw new ValidationException("Entered end date is before the start date");

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
		LocalTime time = LocalTime.now();
		boolean isValidTime = true;
		if (flightTime.isBefore(time)) {
			throw new ValidationException("Entered time is before the current time");
		}
		return isValidTime;
	}

	/**
	 * This method is used to validate no of passengers
	 */
	public static boolean dayValidation(int days) {
		boolean isValidNumberOfDays = true;
		if (days <= 0) {
			throw new ValidationException("Entered number of days is less than or equal to zero");
		}
		return isValidNumberOfDays;

	}

	/**
	 * This method calculate the difference between two days
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int differenceBetweenDate(LocalDate startDate, LocalDate endDate) {
		Period difference = Period.between(startDate, endDate);
		return difference.getDays();

	}

	/**
	 * This method checks whether the package price is greater than 10000
	 * 
	 * @param price
	 * @return
	 */
	public static boolean packagePrice(int price) {
		boolean validPackagePrice = true;
		if (price <= 10000) {
			validPackagePrice = false;
		}
		return validPackagePrice;

	}

}
