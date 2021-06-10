package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.SalesReportDao;
import in.santhosh.exception.DBException;
import in.santhosh.model.BookingDetail;
import in.santhosh.model.SalesDetail;

public class SalesReport {
	private SalesReport() {

	}

	/**
	 * This method is used to get package sales report details
	 * 
	 * @return
	 */
	public static List<SalesDetail> packageSalesReport() {
		SalesReportDao dao = new SalesReportDao();
		return dao.totalSalesReport();

	}

	/**
	 * This method is used to get specific package sales report
	 * 
	 * @param countryName
	 * @return
	 */
	public static List<BookingDetail> specificCountryReport(String countryName) {
		SalesReportDao dao = new SalesReportDao();
		try {
			return dao.countryReport(countryName);
		} catch (Exception e) {
			throw new DBException("unable to get details of specific country");
		}
	}

	/**
	 * This method is used to get total price for specific package
	 * 
	 * @param countryName
	 * @return
	 */
	public static int totalPriceForSpecificCountry(String countryName) {
		SalesReportDao dao = new SalesReportDao();
		return dao.countryTotalBookingPrice(countryName);

	}

}
