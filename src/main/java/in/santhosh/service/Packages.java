package in.santhosh.service;

import java.time.LocalDate;
import java.util.List;

import in.santhosh.dao.PackageDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.PackageValidationException;
import in.santhosh.exception.ServiceException;
import in.santhosh.model.FlightDetail;
import in.santhosh.model.TourPackageDetail;
import in.santhosh.validator.PackageValidator;

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
		return dao.displayPackage();
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
			for (TourPackageDetail tourPackage : packageList) {

				if (tourPackage.getPackageName().equalsIgnoreCase(packageDetail.getPackageName())
						&& tourPackage.getPackagePrice() == packageDetail.getPackagePrice()
						&& tourPackage.getNumberOfDays() == packageDetail.getNumberOfDays()
						&& tourPackage.getStartDate().equals(packageDetail.getStartDate())
						&& tourPackage.getEndDate().equals(packageDetail.getEndDate())
						&& tourPackage.getHotelName().equalsIgnoreCase(packageDetail.getHotelName())) {
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
	 * This method used to get flight detail for user selected package
	 * 
	 * @param packageDetail
	 * @return
	 */
	public static List<FlightDetail> userSelectedPackage(TourPackageDetail packageDetail) {
		LocalDate date = packageDetail.getStartDate();
		LocalDate previousDate = date.minusDays(1);
		PackageDao dao = new PackageDao();
		List<FlightDetail> flightDetail;
		try {
			flightDetail = dao.getFlightDetails(packageDetail, previousDate);
		} catch (ServiceException e) {
			throw new ServiceException("unable to get flight details");
		}
		return flightDetail;

	}

	/**
	 * This method is used to fetch the user selected package flight detail
	 * 
	 * @param packageDetail
	 * @param flightStatus
	 * @return
	 */
	public static List<FlightDetail> userSelectedPackageReturnFlightDetail(TourPackageDetail packageDetail,
			String flightStatus) {
		PackageDao dao = new PackageDao();
		List<FlightDetail> flightReturnDetails;
		try {
			flightReturnDetails = dao.getFlightReturnDetails(packageDetail, flightStatus);
		} catch (ServiceException e) {
			throw new ServiceException("unable to get flight details");
		}
		return flightReturnDetails;
	}

	/**
	 * This method is used to add image into the database
	 * 
	 * @param countryName
	 * @param imageLocation
	 */
	public static void addPackageImage(String countryName, String imageLocation) {
		PackageDao dao = new PackageDao();
		try {
			dao.addImage(countryName, imageLocation);
		} catch (DBException e) {
			throw new ServiceException("unable to add image into the database");
		}
	}

	/**
	 * This method is used to retrieve image from database
	 * 
	 * @param countryName
	 * @return
	 */
	public static byte[] retireveImage(String countryName) {
		PackageDao dao = new PackageDao();
		byte[] image;
		try {
			image = dao.retireveImage(countryName);
		} catch (DBException e) {
			throw new ServiceException("unable to retireve image");

		}
		return image;
	}

	/**
	 * This method is used to add hotel image
	 * 
	 * @param hotelName
	 * @param imageLocation
	 */
	public static void addHotelImage(String hotelName, String imageLocation) {
		PackageDao dao = new PackageDao();
		try {
			dao.addHotelImage(hotelName, imageLocation);
		} catch (DBException e) {
			throw new ServiceException("unable to add image into the database");
		}

	}

	/**
	 * This method is used to retrieve hotel image
	 * 
	 * @param countryName
	 * @return
	 */
	public static byte[] retireveHotelImage(String countryName) {
		PackageDao dao = new PackageDao();
		byte[] image;
		try {
			image = dao.retireveHotelImage(countryName);
		} catch (DBException e) {
			throw new ServiceException("unable to retireve image");

		}
		return image;
	}
	/**
	 * This method is used to get package detail by user selected input
	 * @param countryName
	 * @param packagePrice
	 * @param days
	 * @return
	 */
	public static List<TourPackageDetail> customSearch(String countryName,String  packagePrice,String days)
	{
		PackageDao dao=new PackageDao();
		int price=0;
		int totalDays=0;
		if(packagePrice.length()>=1)
		{
			price=Integer.parseInt(packagePrice);
		}
		if(days.length()>=1)
		{
			 totalDays=Integer.parseInt(days);
		}
		if(countryName.length()<=0)
		{
			countryName=null;
		}
		return dao.customSearchPackage(countryName, price, totalDays);
	}
}
