package in.santhosh.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.model.TourPackageDetail;
import in.santhosh.service.Packages;
import in.santhosh.validator.PackageValidator;
import in.santhosh.validator.Validation;

/**
 * Servlet implementation class PackageAction
 */
@WebServlet("/AddPackageAction")
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

		TourPackageDetail packages = new TourPackageDetail(country, price, days, startDate, endDate);
		try {
			if (PackageValidator.existsingPackage(packages)) {
				if (Validation.differenceBetweenDate(startDate, endDate) == days) {
					boolean isvalidPackage = Packages.addPackage(packages);
					if (isvalidPackage) {
						String infoMessage = "Package added successfully";
						response.sendRedirect("ListOfPackages.jsp");
					} else {
						String message = "Invalid Details";
						response.sendRedirect("AddPackage.jsp?errorMessage=" + message);
					}
				} else {
					String invalidDate = "Enter start and end date correctly";
					response.sendRedirect("AddPackage.jsp?invalidDate=" + invalidDate);
				}
			} else {
				String existsMessage = "Package already exists";
				response.sendRedirect("AddPackage.jsp?existsMessage=" + existsMessage);
			}
		} catch (RuntimeException e) {
			String message = e.getMessage();
			response.sendRedirect("AddPackage.jsp?message=" + message);
		}
	}

}
