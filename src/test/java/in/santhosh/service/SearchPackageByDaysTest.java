package in.santhosh.service;

import static org.junit.Assert.*;

import org.junit.Test;

import in.santhosh.exception.ServiceException;

public class SearchPackageByDaysTest {

	@Test
	public void searchPackageByDaysExists() {
		int days=5;
	
		boolean existsPackage = false;
		try {
			existsPackage=Packages.packageExistsByDays(days);
		} catch (ServiceException e) {
			assertTrue(existsPackage);
			
		}
	}
	@Test
	public void searchPackageByDaysExists1() {
		int days=2;
	
		boolean existsPackage = false;
		try {
			existsPackage=Packages.packageExistsByDays(days);
		} catch (ServiceException e) {
			assertTrue(existsPackage);
			
		}
	}
	@Test
	public void searchPackageByDaysTest() {
		int days=0;
		try {
			Packages.searchPackageByDays(days);
		} catch (ServiceException e) {
			assertEquals("unable to fetch package by number of days",e.getMessage());
		}
	}

}
