package in.santhosh.model;

import java.time.LocalDate;

public class TourPackageDetail {
	/**
	 * This class has package details
	 */
	private String packageName;
	private int packagePrice;
	private int numberOfDays;
	private LocalDate startDate;
	private LocalDate endDate;
	private String hotelName;

	public String getHotelName() {
		return hotelName;
	}

	public String getPackageName() {
		return packageName;
	}

	public int getPackagePrice() {
		return packagePrice;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public TourPackageDetail(String packageName, int packagePrice, int numberOfDays, LocalDate startDate,
			LocalDate endDate, String hotelName) {
		super();
		this.packageName = packageName;
		this.packagePrice = packagePrice;
		this.numberOfDays = numberOfDays;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelName = hotelName;
	}

	@Override
	public String toString() {
		return "packageName=" + packageName + " \n" + "packagePrice=" + packagePrice + "\n" + "numberOfDays="
				+ numberOfDays + "\n" + "Journey start Date=" + startDate + "\n" + "Journey end date=" + endDate;
	}

}
