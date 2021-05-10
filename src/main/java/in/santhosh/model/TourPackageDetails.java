package in.santhosh.model;

import java.time.LocalDate;

public class TourPackageDetails {
	/**
	 * This class has package details
	 */
	public String packageName;
	public int packagePrice;
	public int numberOfDays;
	public LocalDate startDate;
	public LocalDate endDate;

	public TourPackageDetails(String packageName, int packagePrice, int numberOfDays, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.packageName = packageName;
		this.packagePrice = packagePrice;
		this.numberOfDays = numberOfDays;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "packageName=" + packageName + " \n" + "packagePrice=" + packagePrice + "\n" + "numberOfDays="
				+ numberOfDays + "\n" + "Journey start Date=" + startDate + "\n" + "Journey end date=" + endDate;
	}

}
