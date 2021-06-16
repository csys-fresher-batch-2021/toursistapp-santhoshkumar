package in.santhosh.model;

import java.time.LocalDate;

public class ContactUsDetails {

	private String name;
	private long mobileNumber;
	private String countryName;
	private int packagePrice;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public String getCountryName() {
		return countryName;
	}

	public int getPackagePrice() {
		return packagePrice;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}


	public ContactUsDetails(String name, long mobileNumber, String countryName, int packagePrice, LocalDate startDate,
			LocalDate endDate, String status) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.countryName = countryName;
		this.packagePrice = packagePrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

}
