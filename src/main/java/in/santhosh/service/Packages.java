package in.santhosh.service;

import java.util.ArrayList;
import java.util.List;

import in.santhosh.model.TourPackageDetail;
import in.santhosh.validator.PackageValidator;

public class Packages {

	private Packages() {

	}

	private static final List<TourPackageDetail> packageList = new ArrayList<>();

	/**
	 * This method adds Packages
	 * 
	 * @param packages
	 */

	public static boolean addPackage(TourPackageDetail packages) {
		boolean isValidPackage = false;
		if (PackageValidator.validatePackage(packages)) {
			getPackagelist().add(packages);
			isValidPackage = true;
		}
		return isValidPackage;
	}

	/**
	 * This method display all the packages
	 * 
	 * @return
	 */
	public static List<TourPackageDetail> displayPackages() {
		return getPackagelist();
	}

	public static List<TourPackageDetail> getPackagelist() {
		return packageList;
	}

}
