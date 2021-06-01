package in.santhosh.service;

import java.time.LocalDate;
import java.util.List;

import in.santhosh.dao.PackageDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.PackageValidationException;
import in.santhosh.exception.ServiceException;
import in.santhosh.exception.ValidationException;
import in.santhosh.model.FlightDetail;
import in.santhosh.model.TourPackageDetail;
import in.santhosh.validator.PackageValidator;
import in.santhosh.validator.Validation;


public class Packages {

	private Packages() {

	}

	/**
	 * This method adds Packages
	 * 
	 * @param packages
	 */

	public static boolean addPackage(TourPackageDetail packages) {
		boolean isValidPackage = false;
		try {
			if (PackageValidator.validatePackage(packages)) {
				PackageDao dao = new PackageDao();
				dao.addPackage(packages);
				isValidPackage = true;
			}
		} catch (DBException | PackageValidationException e) {
			throw new ServiceException("unable to add package");
		}
		return isValidPackage;
	}

	/**
	 * This method display all the packages
	 * 
	 * @return
	 */
	public static List<TourPackageDetail> displayPackages() {
		PackageDao dao = new PackageDao();
		List<TourPackageDetail> packageList = dao.displayPackage();
		return packageList;
	}

	/**
	 * This method remove packages
	 * 
	 * @param countryName
	 * @return
	 */
	public static boolean removePackage(TourPackageDetail packageDetail) {
		boolean isMatched = false;

		try {
			PackageDao dao = new PackageDao();
			List<TourPackageDetail> packageList = dao.getAllPackages();
			for(TourPackageDetail packageL:packageList)
			{
				System.out.println(packageL.getPackageName());
				System.out.println(packageL.getPackagePrice());
				System.out.println(packageL.getNumberOfDays());
				System.out.println(packageL.getStartDate());
				System.out.println(packageL.getEndDate());
				System.out.println(packageL.getHotelName());
			}
			for (TourPackageDetail tourPackage : packageList) {

				if (tourPackage.getPackageName().equalsIgnoreCase(packageDetail.getPackageName())
						&& tourPackage.getPackagePrice() == packageDetail.getPackagePrice()
						&& tourPackage.getNumberOfDays() == packageDetail.getNumberOfDays()
						&& tourPackage.getStartDate().equals(packageDetail.getStartDate())
						&& tourPackage.getEndDate().equals(packageDetail.getEndDate())
						&&tourPackage.getHotelName().equalsIgnoreCase(packageDetail.getHotelName())){
					isMatched = true;
					break;

				}

			}
			if (isMatched) {
				dao.removePackage(packageDetail);
			} else {
				throw new ServiceException("unable to delete package from database");
			}
		} catch (DBException e) {
			throw new ServiceException("Unable to delete package from database");
		}
		return isMatched;
	}
	
	/**
	 * This package is used to fetch packages by country name
	 * @param countryName
	 * @return
	 */
	public static List<TourPackageDetail> searchPackageByName(String countryName)
	{
		PackageDao dao=new PackageDao();
		List<TourPackageDetail> packageList = null;
		try {
			if(Validation.stringValidation(countryName)) {
			
			packageList=dao.searchPackageByCountryName(countryName);
			}
		} catch (ValidationException | DBException e) {
			throw new ServiceException("unable to fetch package by country name");
		}
		return packageList;
	}
	
	/**
	 * This method is used to check whether the user selected package is available
	 * @param countryName
	 * @return
	 */
	public static boolean packageExistsByCountryName(String countryName)
	{
		boolean packageExists=false;
		PackageDao dao=new PackageDao();
		List<TourPackageDetail> packageList;
		try {
			packageList=dao.searchPackageByCountryName(countryName);
			if(!packageList.isEmpty())
			{
				packageExists=true;
			}
		} catch (DBException e) {
			throw new ServiceException("unable to fetch package list from database");
		}
		return packageExists;
		
	}
	/**
	 * This method is used to check whether the user selected package price is available
	 * @param price
	 * @return
	 */
	public static boolean packageExistsByPrice(int price)
	{
		boolean packageExists=false;
		PackageDao dao=new PackageDao();
		List<TourPackageDetail> packageDetail;
		try {
			packageDetail=dao.searchPackageByPrice(price);
			if(!packageDetail.isEmpty())
			{
				packageExists=true;
			}
		} catch (DBException e) {
			throw new ServiceException("unable to fetch package list");
		}
		return packageExists;
		
	}
	/**
	 * This method is used to fetch package detail using user selected package price
	 * @param price
	 * @return
	 */
	public static List<TourPackageDetail> searchPackageByPackagePrice(int price)
	{
		PackageDao dao=new PackageDao();
		List<TourPackageDetail> packageList = null;
		try {
			if(Validation.packagePrice(price)) {
			packageList=dao.searchPackageByPrice(price);
			}
		} catch (DBException e) {
			
			throw new ServiceException("unable to fetch package by package price");
		}
		return packageList;
	}
	/**
	 * This method is used to check whether the user selected days is available
	 * @param price
	 * @return
	 */
	public static boolean packageExistsByDays(int days)
	{
		boolean packageExists=false;
		PackageDao dao=new PackageDao();
		List<TourPackageDetail> packageDetail;
		try {
			packageDetail=dao.searchPackageByDays(days);
			if(!packageDetail.isEmpty())
			{
				packageExists=true;
			}
		} catch (DBException e) {
			throw new ServiceException("unable to fetch package detail");
		}
		return packageExists;
		
	}
	/**
	 * This method is used to search package by days 
	 * @param days
	 * @return
	 */
	public static List<TourPackageDetail> searchPackageByDays(int days)
	{
		PackageDao dao=new PackageDao();
		List<TourPackageDetail> packageList = null;
		try {
			if(Validation.dayValidation(days)){
			packageList=dao.searchPackageByDays(days);
			}
		} catch (DBException|ValidationException e) {
			
			throw new ServiceException("unable to fetch package by number of days");
		}
		return packageList;
	}
	
	/**
	 * This method used to get flight detail for user selected package
	 * @param packageDetail
	 * @return
	 */
	public static List<FlightDetail> userSelectedPackage(TourPackageDetail packageDetail)
	{
		LocalDate date=packageDetail.getStartDate();
		LocalDate previousDate=date.minusDays(1);
		PackageDao dao=new PackageDao();
		List<FlightDetail> flightDetail;
		try {
			flightDetail=dao.getFlightDetails(packageDetail,previousDate);
		} catch (ServiceException e) {
			throw new ServiceException("unable to get flight details");
		}
		return flightDetail;
		
	}
	/**
	 * This method is used to fetch the user selected package flight detail
	 * @param packageDetail
	 * @param flightStatus
	 * @return
	 */
	public static List<FlightDetail> userSelectedPackageReturnFlightDetail(TourPackageDetail packageDetail,String flightStatus)
	{
		PackageDao dao=new PackageDao();
		List<FlightDetail> flightReturnDetails;
		try {
			flightReturnDetails=dao.getFlightReturnDetails(packageDetail,flightStatus);
		}catch (ServiceException e) {
			throw new ServiceException("unable to get flight details");
		}
		return flightReturnDetails;
	}
	/**
	 * This method is used to add image into the database
	 * @param countryName
	 * @param imageLocation
	 */
	public static void addPackageImage(String countryName,String imageLocation)
	{
		PackageDao dao=new PackageDao();
		try {
			dao.addImage(countryName,imageLocation);
		} catch (DBException e) {
			throw new ServiceException("unable to add image into the database");
		}
	}
	/**
	 * This method is used to retrieve image from database
	 * @param countryName
	 * @return
	 */
	public static byte[] retireveImage(String countryName)
	{
		PackageDao dao=new PackageDao();
		byte[] image;
		try {
			image=dao.retireveImage(countryName);
		} catch (DBException e) {
			throw new ServiceException("unable to retireve image");
			
		}
		return image;
	}
}
