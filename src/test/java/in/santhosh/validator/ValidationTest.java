package in.santhosh.validator;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

import in.santhosh.exception.ValidationException;
import in.santhosh.model.TourPackageDetail;

public class ValidationTest {

	@Test
	public void stringValidationWithCorrectDetail() {
		String testString="Maldives";
		boolean validString=Validation.stringValidation(testString);
		assertEquals(true,validString);
	}
	@Test
	public void stringValidationWithWrongDetail() {
		String testString=" ";
		try {
			Validation.stringValidation(testString);
		} catch (ValidationException e) {

			assertEquals("Entered value is null",e.getMessage());
		}
	}
	@Test
	public void stringValidationWithWrongDetail1() {
		String testString="Maldives9999";
		try {
			Validation.stringValidation(testString);
		} catch (ValidationException e) {
			assertEquals("Entered value contains number",e.getMessage());
		}
	}
	@Test
	public void dateValidationWithCorrectDate() {
		LocalDate date;
		date=LocalDate.of(2021, 05, 30);
		boolean validDate=Validation.dateValidation(date);
		assertTrue(validDate);
	}
	@Test
	public void dateValidationWithWrongDate() {
		LocalDate date;
		date=LocalDate.of(2021, 05, 10);
		try {
			Validation.dateValidation(date);
		} catch (ValidationException e) {
			assertEquals("Enter start date is before current date",e.getMessage());
		}
	}
	@Test
	public void dateValidationWithWrongEndDate() {
		LocalDate startDate;
		LocalDate endDate;
		startDate=LocalDate.of(2021, 06, 15);
		endDate=LocalDate.of(2021, 06,10);
		try {
			Validation.dateValidationEnd(startDate, endDate);
		} catch (ValidationException e) {
			assertEquals("Entered end date is before the start date",e.getMessage());
			
		}
	}
	@Test
	public void dateValidationWithCorrectEndDate() {
		LocalDate startDate;
		LocalDate endDate;
		startDate=LocalDate.of(2021, 05, 17);
		endDate=LocalDate.of(2021, 05, 23);
		boolean validDate=Validation.dateValidationEnd(startDate, endDate);
		assertTrue(validDate);
	}
	@Test
	public void dayValidationWithCorrectDetail() {
		LocalDate startDate;
		LocalDate endDate;
		startDate=LocalDate.of(2021, 05, 18);
		endDate=LocalDate.of(2021, 05, 23);
		int validDate=Validation.differenceBetweenDate(startDate, endDate);
		assertEquals(5,validDate);
	}
	@Test
	public void packageValidationWithOutExistingPackage() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 06,15 );
		endDate = LocalDate.of(2021, 06, 10);
		String hotelName="hilton";
		TourPackageDetail packages1 = new TourPackageDetail("Maldives", 15000, 5, startDate, endDate,hotelName);
		boolean existingProduct=PackageValidator.existsingPackage(packages1);
		assertTrue(existingProduct);
	}
	@Test
	public void packageValidationWithExistingPackage() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05,21);
		endDate = LocalDate.of(2021, 05, 26);
		String hotelName="hilton";
		TourPackageDetail packages1 = new TourPackageDetail("Dubai", 15000, 5, startDate, endDate,hotelName);
		boolean existingProduct=PackageValidator.existsingPackage(packages1);
		assertFalse(existingProduct);
	}


}
