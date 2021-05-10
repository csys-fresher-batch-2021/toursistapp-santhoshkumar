package in.santhosh.validator;

import in.santhosh.model.TourPackageDetails;

public class PackageValidator {
	public static boolean validatePackage(TourPackageDetails packages) {
		boolean isValidPackage = true;
		if (!Validation.stringValidation(packages.packageName)) {
			isValidPackage = false;
		}
		if (!Validation.dateValidation(packages.startDate)) {
			isValidPackage = false;
		}
		if (!Validation.dateValidationEnd(packages.startDate, packages.endDate)) {
			isValidPackage = false;
		}
		if (!Validation.dayValidation(packages.numberOfDays)) {
			isValidPackage = false;
		}
		return isValidPackage;
	}

}
