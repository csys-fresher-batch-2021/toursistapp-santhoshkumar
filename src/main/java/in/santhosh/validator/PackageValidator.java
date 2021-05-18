package in.santhosh.validator;

import in.santhosh.model.TourPackageDetail;
import in.santhosh.service.Packages;

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
		return isValidPackage;
	}
	/**
	 * This method is used to check whether the package is already exists
	 * @param packages
	 * @return
	 */

	public static boolean existsingPackage(TourPackageDetail packages) {
		boolean isExistPackage = true;
		for (TourPackageDetail packageDetails : Packages.getPackagelist()) {
			if (packageDetails.getPackageName().equals(packages.getPackageName())
					&& packageDetails.getPackagePrice() == packages.getPackagePrice()
					&& packageDetails.getNumberOfDays() == packages.getNumberOfDays()
					&& packageDetails.getStartDate().equals(packages.getStartDate())
					&& packageDetails.getEndDate().equals(packages.getEndDate())) {
				isExistPackage = false;
				break;
			}

		}
		return isExistPackage;
	}

}