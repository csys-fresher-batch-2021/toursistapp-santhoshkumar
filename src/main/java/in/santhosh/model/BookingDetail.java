package in.santhosh.model;

import java.time.LocalDate;

public class BookingDetail {
	private String packageName;
	private int packagePrice;
	private int numberOfDays;
	private LocalDate startDate;
	private LocalDate endDate;
	private int id;
	private int numberOfPerson;
	private double totalPrice;
	private String status;

	public String getStatus() {
		return status;
	}

	public double getTotalPrice() {
		return totalPrice;
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

	public BookingDetail(String packageName, int packagePrice, int numberOfDays, LocalDate startDate, LocalDate endDate,
			int id, int numberOfPerson, double totalPrice, String status) {
		super();
		this.packageName = packageName;
		this.packagePrice = packagePrice;
		this.numberOfDays = numberOfDays;
		this.startDate = startDate;
		this.endDate = endDate;
		this.id = id;
		this.numberOfPerson = numberOfPerson;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public int getNumberOfPerson() {
		return numberOfPerson;
	}

	public BookingDetail(String packageName, int packagePrice, int numberOfDays, LocalDate startDate, LocalDate endDate,
			int id, int numberOfPerson) {
		super();
		this.packageName = packageName;
		this.packagePrice = packagePrice;
		this.numberOfDays = numberOfDays;
		this.startDate = startDate;
		this.endDate = endDate;
		this.id = id;
		this.numberOfPerson = numberOfPerson;
	}

	public BookingDetail(String packageName, int packagePrice, int numberOfDays, LocalDate startDate, LocalDate endDate,
			int id, int numberOfPerson, double totalPrice) {
		super();
		this.packageName = packageName;
		this.packagePrice = packagePrice;
		this.numberOfDays = numberOfDays;
		this.startDate = startDate;
		this.endDate = endDate;
		this.id = id;
		this.numberOfPerson = numberOfPerson;
		this.totalPrice = totalPrice;
	}
}
