package in.santhosh.service;

import static org.junit.Assert.*;

import org.junit.Test;

import in.santhosh.exception.ServiceException;

public class SearchPackageByPriceTest {

	@Test
	public void searchPackageByPricexists() {
		int price=30000;
	
		boolean existsPackage = false;
		try {
			existsPackage=Packages.packageExistsByPrice(price);
		} catch (ServiceException e) {
			assertTrue(existsPackage);
			
		}
	}
	@Test
	public void searchPackageByPricexists1() {
		int price=10000;
	
		boolean existsPackage = false;
		try {
			existsPackage=Packages.packageExistsByPrice(price);
		} catch (ServiceException e) {
			assertFalse(existsPackage);
			
		}
	}
	@Test
	public void searchPackageByPriceTest() {
		int price=0;
		try {
			Packages.searchPackageByPackagePrice(price);
		} catch (ServiceException e) {
			assertEquals("unable to fetch package by package price",e.getMessage());
		}
	}


}
