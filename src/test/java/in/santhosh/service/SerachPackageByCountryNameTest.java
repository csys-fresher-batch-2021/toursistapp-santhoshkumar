package in.santhosh.service;

import static org.junit.Assert.*;

import org.junit.Test;

import in.santhosh.exception.ServiceException;

public class SerachPackageByCountryNameTest {

	@Test
	public void searchPackageByCountryNameWithWrongName() {
		String countryName="";
		try {
			Packages.searchPackageByName(countryName);
		} catch (ServiceException e) {
			assertEquals("unable to fetch package by country name",e.getMessage());
		}
	}
	@Test
	public void searchPackageByCountryExists() {
		String countryName="Dubai";
	
		boolean existsPackage = false;
		try {
			existsPackage=Packages.packageExistsByCountryName(countryName);
		} catch (ServiceException e) {
			assertTrue(existsPackage);
			
		}
	}
	@Test
	public void searchPackageByCountryExists2() {
		String countryName="Manali";
	
		boolean existsPackage = false;
		try {
			existsPackage=Packages.packageExistsByCountryName(countryName);
		} catch (ServiceException e) {
			assertFalse(existsPackage);
			
		}
	}
	@Test
	public void searchPackageByCountryExists3() {
		String countryName=" ";
	
		try {
			Packages.packageExistsByCountryName(countryName);
		} catch (ServiceException e) {
			assertEquals("unable to fetch package list from database",e.getMessage());
			
		}
	}

}
