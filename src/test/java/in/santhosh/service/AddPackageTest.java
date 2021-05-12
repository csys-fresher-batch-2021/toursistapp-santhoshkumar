package in.santhosh.service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import in.santhosh.model.TourPackageDetails;
import in.santhosh.service.Packages;

public class AddPackageTest {
	/**
	 * In this method all data are correct so this method returns true;
	 */
		@Test
		public void addPackageTest() {
			LocalDate startDate;
			LocalDate endDate;
			startDate=LocalDate.of(2021, 05, 12);
			endDate=LocalDate.of(2021, 05, 17);
			TourPackageDetails packages=new TourPackageDetails("Dubai",15000,5,startDate,endDate);
			boolean isvalidPackage=Packages.addPackage(packages);
			assertEquals(true,isvalidPackage);
		}
		/**
		 * In this method the country name is given with numbers so it returns false
		 */
		@Test
		public void addPackageTest1() {
			LocalDate startDate;
			LocalDate endDate;
			startDate=LocalDate.of(2021, 05, 10);
			endDate=LocalDate.of(2021, 05, 15);
			TourPackageDetails packages=new TourPackageDetails("Maldive000s",15000,5,startDate,endDate);
			boolean isvalidPackage=Packages.addPackage(packages);
			assertFalse(isvalidPackage);
		}
		/**
		 * In this method number of days is 0 so this method returns false
		 */
		@Test
		public void addPackageTest2() {
			LocalDate startDate;
			LocalDate endDate;
			startDate=LocalDate.of(2021, 05, 10);
			endDate=LocalDate.of(2021, 05, 15);
			TourPackageDetails packages=new TourPackageDetails("Maldives",15000,0,startDate,endDate);
			boolean isvalidPackage=Packages.addPackage(packages);
			assertFalse(isvalidPackage);
		}
		/**
		 * In this method start date is wrong so this method returns false
		 */
		@Test
		public void addPackageTest3() {
			LocalDate startDate;
			LocalDate endDate;
			startDate=LocalDate.of(2020, 05, 10);
			endDate=LocalDate.of(2021, 05, 15);
			TourPackageDetails packages=new TourPackageDetails("Maldives",20000,0,startDate,endDate);
			boolean isvalidPackage=Packages.addPackage(packages);
			assertFalse(isvalidPackage);
		}
		/**
		 * In this method endDate is the before date of startDate so it returns false
		 */
		@Test
		public void addPackageTest4() {
			LocalDate startDate;
			LocalDate endDate;
			startDate=LocalDate.of(2021, 05, 10);
			endDate=LocalDate.of(2021, 05, 06);
			TourPackageDetails packages=new TourPackageDetails("Maldives",15000,0,startDate,endDate);
			boolean isvalidPackage=Packages.addPackage(packages);
			assertFalse(isvalidPackage);
		}
		/**
		 * In this method all data are correct so this method returns true;
		 */
		@Test
		public void addPackageTest5() {
			LocalDate startDate;
			LocalDate endDate;
			startDate=LocalDate.of(2021, 05, 12);
			endDate=LocalDate.of(2021, 05, 17);
			TourPackageDetails packages=new TourPackageDetails("Germany",15000,5,startDate,endDate);
			boolean isvalidPackage=Packages.addPackage(packages);
			assertTrue(isvalidPackage);
		}
		@Test
		public void addPackageTest6() {
			LocalDate startDate;
			LocalDate endDate;
			startDate=LocalDate.of(2021, 05, 10);
			endDate=LocalDate.of(2021, 05, 15);
			TourPackageDetails packages=new TourPackageDetails("",20000,5,startDate,endDate);
			boolean isvalidPackage=Packages.addPackage(packages);
			assertFalse(isvalidPackage);
		}


		
}
