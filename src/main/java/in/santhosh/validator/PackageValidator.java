package in.santhosh.validator;

import java.util.List;

import in.santhosh.Dao.PackageDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.PackageValidationException;
import in.santhosh.exception.ValidationException;
import in.santhosh.model.TourPackageDetail;

public class PackageValidator {
	private PackageValidator() {

	}

	/**
	 * This method checks whether it is a valid package
	 * 
	 * @param packages
	 * @return
	 */
	public static boolean validatePackage(TourPackageDetail packages) {
		boolean isValidPackage = true;
		try {
			if (!Validation.stringValidation(packages.getPackageName())) {
				isValidPackage = false;
			}
			if (!Validation.dateValidation(packages.getStartDate())) {
				isValidPackage = false;
			}
			if (!Validation.dateValidationEnd(packages.getStartDate(), packages.getEndDate())) {
				isValidPackage = false;
			}
			if (!Validation.dayValidation(packages.getNumberOfDays())) {
				isValidPackage = false;
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new PackageValidationException("Enter all details correctly");

		}
		return isValidPackage;
	}

	/**
	 * This method is used to check whether the package is already exists
	 * 
	 * @param packages
	 * @return
	 */

	public static boolean existsingPackage(TourPackageDetail packages) {
		boolean isExistPackage = true;
		PackageDao dao = new PackageDao();
		List<TourPackageDetail> packageList;
		try {
			packageList = dao.getAllPackages();
		} catch (DBException e) {
			e.printStackTrace();
			throw new PackageValidationException("Package does not exists");
		}
		for (TourPackageDetail tourPackage : packageList) {

			if (tourPackage.getPackageName().equalsIgnoreCase(packages.getPackageName())
					&& tourPackage.getPackagePrice() == packages.getPackagePrice()
					&& tourPackage.getNumberOfDays() == packages.getNumberOfDays()
					&& tourPackage.getStartDate().equals(packages.getStartDate())
					&& tourPackage.getEndDate().equals(packages.getEndDate())) {
				isExistPackage = false;
				break;

			}

		}
		return isExistPackage;
	}

}