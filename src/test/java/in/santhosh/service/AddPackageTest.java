package in.santhosh.service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.TourPackageDetail;

public class AddPackageTest {
	/**
	 * In this method all data are correct so this method returns true;
	 */
	@Test
	public void addPackageTestWithCorrectDetails() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05, 25);
		endDate = LocalDate.of(2021, 05, 30);
		TourPackageDetail packages = new TourPackageDetail("Dubai", 15000, 5, startDate, endDate);
		boolean isvalidPackage = Packages.addPackage(packages);
		assertEquals(true, isvalidPackage);
	}

	/**
	 * In this method the country name is given with numbers so it returns false
	 */
	@Test
	public void addPackageTestWithInvalidCountryName() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05, 13);
		endDate = LocalDate.of(2021, 05, 15);
		TourPackageDetail packages = new TourPackageDetail("000Maldives", 15000, 5, startDate, endDate);
		try {
			Packages.addPackage(packages);
		} catch (Exception e) {
			assertEquals("unable to add package",e.getMessage());
		}
	}

	/**
	 * In this method number of days is 0 so this method returns false
	 */
	@Test
	public void addPackageTestWithNumberOfDaysAsZero() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05, 10);
		endDate = LocalDate.of(2021, 05, 15);
		TourPackageDetail packages = new TourPackageDetail("Maldives", 15000, 0, startDate, endDate);
		try {
			Packages.addPackage(packages);
		} catch (ServiceException e) {
			assertEquals("unable to add package",e.getMessage());
		}
	}

	/**
	 * In this method start date is wrong so this method returns false
	 */
	@Test
	public void addPackageTestWithWrongStartDate() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2020, 05, 10);
		endDate = LocalDate.of(2021, 05, 15);
		TourPackageDetail packages = new TourPackageDetail("Maldives", 20000, 0, startDate, endDate);
		try {
			Packages.addPackage(packages);
		} catch (ServiceException e) {
			assertEquals("unable to add package",e.getMessage());
		}
	}

	/**
	 * In this method endDate is the before date of startDate so it returns false
	 */
	@Test
	public void addPackageTestWithWrongEndDate() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05, 13);
		endDate = LocalDate.of(2021, 05, 06);
		TourPackageDetail packages = new TourPackageDetail("Maldives", 15000, 0, startDate, endDate);
		try {
			Packages.addPackage(packages);
		} catch (ServiceException e) {
			assertEquals("unable to add package",e.getMessage());
		}
	}

	/**
	 * In this method all data are correct so this method returns true;
	 */
	@Test
	public void addPackageTestWithCorrectDetails2(){
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05, 25);
		endDate = LocalDate.of(2021, 05, 30);
		TourPackageDetail packages = new TourPackageDetail("Germany", 15000, 5, startDate, endDate);
		boolean isvalidPackage = Packages.addPackage(packages);
		assertTrue(isvalidPackage);
	}

	@Test
	public void addPackageTestWithInvalidCountryName1() {
		LocalDate startDate;
		LocalDate endDate;
		startDate = LocalDate.of(2021, 05, 13);
		endDate = LocalDate.of(2021, 05, 18);
		TourPackageDetail packages = new TourPackageDetail("", 20000, 5, startDate, endDate);
		try {
			Packages.addPackage(packages);
		} catch (ServiceException e) {
			assertEquals("unable to add package",e.getMessage());
		}
	}

}
