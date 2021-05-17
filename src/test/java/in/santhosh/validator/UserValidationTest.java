package in.santhosh.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserValidationTest {

	@Test
	public void mobileNumberValidationWithCorrectNumber() {
		long mobileNumber=9865940407l;
		boolean validMobileNumber=UserValidation.validateMobileNumber(mobileNumber);
		assertTrue(validMobileNumber);
		
	}
	@Test
	public void mobileNumberValidationWithWrongNumber() {
		long mobileNumber=1865940407l;
		boolean validMobileNumber=UserValidation.validateMobileNumber(mobileNumber);
		assertFalse(validMobileNumber);
		
	}
	@Test
	public void mobileNumberValidationWithLengthNotEqualToTen() {
		long mobileNumber=186594040l;
		boolean validMobileNumber=UserValidation.validateMobileNumber(mobileNumber);
		assertFalse(validMobileNumber);
		
	}
	@Test
	public void mobileNumberValidationWithSpecialCharacter() {
		long mobileNumber=986594*40l;
		boolean validMobileNumber=UserValidation.validateMobileNumber(mobileNumber);
		assertFalse(validMobileNumber);
		
	}
	@Test
	public void passwordValidationWithCorrectDetail() {
		String password="Santhosh";
		boolean validPassword=UserValidation.isValidatePassword(password);
		assertTrue(validPassword);
		
	}
	@Test
	public void passwordValidationWithLengthLesserThanEight() {
		String password="Santho";
		boolean validPassword=UserValidation.isValidatePassword(password);
		assertFalse(validPassword);
		
	}
	@Test
	public void passwordValidationWithFirstLetterAsSmall() {
		String password="santhosh";
		boolean validPassword=UserValidation.isValidatePassword(password);
		assertFalse(validPassword);
		
	}
	

}
