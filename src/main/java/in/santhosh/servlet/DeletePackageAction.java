package in.santhosh.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.TourPackageDetail;
import in.santhosh.service.Packages;

/**
 * Servlet implementation class DeletePackageAction
 */
@WebServlet("/DeletePackageAction")
public class DeletePackageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LocalDate startDate;
		LocalDate endDate;
		int price = 0;
		int days = 0;
		String packageName = request.getParameter("packageName");
		price = Integer.parseInt(request.getParameter("packagePrice"));
		days = Integer.parseInt(request.getParameter("numberOfDays"));
		startDate = LocalDate.parse(request.getParameter("startDate"));
		endDate = LocalDate.parse(request.getParameter("endDate"));
		TourPackageDetail packageList = new TourPackageDetail(packageName, price, days, startDate, endDate);

		try {
			boolean isMatched = Packages.removePackage(packageList);
			if (isMatched) {
				String infoMessage = "Removed successfully";
				response.sendRedirect("ListOfPackages.jsp?infoMessage=" + infoMessage);
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("ListOfPackages.jsp?message=" + message);

		}

	}
}
