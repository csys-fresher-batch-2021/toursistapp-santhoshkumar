package in.santhosh.model;

public class SalesDetail {

	private String countryName;
	private int totalBooking;

	public String getCountryName() {
		return countryName;
	}

	public int getTotalBooking() {
		return totalBooking;
	}

	public SalesDetail(String countryName, int totalBooking) {
		super();
		this.countryName = countryName;
		this.totalBooking = totalBooking;
	}
}
