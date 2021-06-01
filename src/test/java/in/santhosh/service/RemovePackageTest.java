package in.santhosh.service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.TourPackageDetail;

public class RemovePackageTest {
	
	@Test
	public void removePackageWithPackageNameTest1() {
		String packageName="Dubai";
		int packagePrice=15000;
		int numberOfDays=5;
		LocalDate startDate=LocalDate.of(2021, 05, 25);
		LocalDate endDate=LocalDate.of(2021, 05, 30);
		String hotelName="hilton";
		TourPackageDetail packages = new TourPackageDetail(packageName,packagePrice,numberOfDays,startDate,endDate,hotelName);
		boolean removePackage = Packages.removePackage(packages);
		assertEquals(true, removePackage);
	}
	@Test
	public void removePackageWithEmptyValueTest() {
		String packageName=" ";
		int packagePrice=15000;
		int numberOfDays=5;
		LocalDate startDate=LocalDate.of(2021, 05, 21);
		LocalDate endDate=LocalDate.of(2021, 05, 26);
		String hotelName="Berjaya Times Square Hotel";
		TourPackageDetail packages = new TourPackageDetail(packageName,packagePrice,numberOfDays,startDate,endDate,hotelName);
		try {
			Packages.removePackage(packages);
		} catch (ServiceException e) {
			assertEquals("unable to delete package from database",e.getMessage());
		}
	}
	@Test
	public void removePackageWithPackageNameTest2() {
		String packageName="Dubai";
		int packagePrice=15000;
		int numberOfDays=5;
		LocalDate startDate=LocalDate.of(2021, 05, 25);
		LocalDate endDate=LocalDate.of(2021, 05, 30);
		String hotelName="hilton";
		TourPackageDetail packages = new TourPackageDetail(packageName,packagePrice,numberOfDays,startDate,endDate,hotelName);
		try {
			Packages.removePackage(packages);
		} catch (ServiceException e) {
			assertEquals("unable to delete package from database",e.getMessage());
		}
	}

}
