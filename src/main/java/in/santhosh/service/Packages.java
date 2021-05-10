package in.santhosh.service;

import java.util.ArrayList;
import java.util.List;

import in.santhosh.model.TourPackageDetails;
import in.santhosh.validator.PackageValidator;

public class Packages {
	private static List<TourPackageDetails> packageList = new ArrayList<TourPackageDetails>();

	/**
	 * This method adds Packages
	 * 
	 * @param packages
	 */

	public static boolean addPackage(TourPackageDetails packages) {
		boolean isValidPackage=false;
		if (PackageValidator.validatePackage(packages)) {
			packageList.add(packages);
			isValidPackage=true;
		} else {
			System.out.println("please enter all fields correctly");
		}
		return isValidPackage;
	}

}
