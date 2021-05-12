package in.santhosh.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.model.TourPackageDetails;
import in.santhosh.service.Packages;

/**
 * Servlet implementation class PackageAction
 */
@WebServlet("/PackageAction")
public class PackageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LocalDate startDate;
		LocalDate endDate;
		int price = 0;
		int days = 0;
		String country = request.getParameter("countryName");
		try {
			price = Integer.parseInt(request.getParameter("packagePrice"));
		} catch (NumberFormatException e) {
			System.out.println("Invalid values");
		}
		try {
			days = Integer.parseInt(request.getParameter("days"));
		} catch (NumberFormatException e) {
			System.out.println("Invalid values");
		}
		startDate = LocalDate.parse(request.getParameter("startDate"));
		endDate = LocalDate.parse(request.getParameter("endDate"));

		TourPackageDetails packages = new TourPackageDetails(country, price, days, startDate, endDate);
		boolean isvalidPackage = Packages.addPackage(packages);

		if (isvalidPackage) {
			String infoMessage = "Package added successfully";
			response.sendRedirect("AddPackage.jsp?infoMessage=" + infoMessage);
		} else {
			String message = "Invalid Details";
			response.sendRedirect("AddPackage.jsp?errorMessage=" + message);

		}
	}

}