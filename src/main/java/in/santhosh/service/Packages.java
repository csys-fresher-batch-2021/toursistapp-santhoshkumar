package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.PackageDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.PackageValidationException;
import in.santhosh.exception.ServiceException;

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
			e.printStackTrace();
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
			for (TourPackageDetail tourPackage : packageList) {

				if (tourPackage.getPackageName().equalsIgnoreCase(packageDetail.getPackageName())
						&& tourPackage.getPackagePrice() == packageDetail.getPackagePrice()
						&& tourPackage.getNumberOfDays() == packageDetail.getNumberOfDays()
						&& tourPackage.getStartDate().equals(packageDetail.getStartDate())
						&& tourPackage.getEndDate().equals(packageDetail.getEndDate())) {
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
			e.printStackTrace();
			throw new ServiceException("Unable to delete package from database");
		}
		return isMatched;
	}

}
