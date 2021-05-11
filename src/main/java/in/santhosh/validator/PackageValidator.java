package in.santhosh.validator;

import in.santhosh.model.TourPackageDetails;

public class PackageValidator {
	private PackageValidator()
	{
		
	}
	public static boolean validatePackage(TourPackageDetails packages) {
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

}
