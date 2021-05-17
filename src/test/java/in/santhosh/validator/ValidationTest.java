package in.santhosh.validator;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

import in.santhosh.model.TourPackageDetail;
import in.santhosh.service.Packages;

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
		boolean validString=Validation.stringValidation(testString);
		assertFalse(validString);
	}
	@Test
	public void stringValidationWithWrongDetail1() {
		String testString="Maldives9999";
		boolean validString=Validation.stringValidation(testString);
		assertFalse(validString);
	}
	@Test
	public void dateValidationWithCorrectDate() {
		LocalDate date;
		date=LocalDate.of(2021, 05, 20);
		boolean validDate=Validation.dateValidation(date);
		assertTrue(validDate);
	}
	@Test
	public void dateValidationWithWrongDate() {
		LocalDate date;
		date=LocalDate.of(2021, 05, 10);
		boolean validDate=Validation.dateValidation(date);
		assertFalse(validDate);
	}
	@Test
	public void dateValidationWithWrongEndDate() {
		LocalDate startDate;
		LocalDate endDate;
		startDate=LocalDate.of(2021, 05, 17);
		endDate=LocalDate.of(2021, 05, 10);
		boolean validDate=Validation.dateValidationEnd(startDate, endDate);
		assertFalse(validDate);
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
		startDate = LocalDate.of(2021, 05, 18);
		endDate = LocalDate.of(2021, 05, 23);
		TourPackageDetail packages = new TourPackageDetail("Dubai", 15000, 5, startDate, endDate);
		Packages.addPackage(packages);
		TourPackageDetail packages1 = new TourPackageDetail("Maldives", 15000, 5, startDate, endDate);
		boolean existingProduct=PackageValidator.existsingPackage(packages1);
		assertTrue(existingProduct);
		
		
		
	}
	@Test
	public void packageValidationWithExistingPackage() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05, 18);
		endDate = LocalDate.of(2021, 05, 23);
		TourPackageDetail packages = new TourPackageDetail("Dubai", 15000, 5, startDate, endDate);
		Packages.addPackage(packages);
		TourPackageDetail packages1 = new TourPackageDetail("Dubai", 15000, 5, startDate, endDate);
		boolean existingProduct=PackageValidator.existsingPackage(packages1);
		assertFalse(existingProduct);
		
		
		
	}


}
