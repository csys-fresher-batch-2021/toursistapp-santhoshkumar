package in.santhosh.model;

import java.time.LocalDate;

public class TourPackageDetails {
	/**
	 * This class has package details
	 */
	private String packageName;
	private int packagePrice;
	private int numberOfDays;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(int packagePrice) {
		this.packagePrice = packagePrice;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

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
