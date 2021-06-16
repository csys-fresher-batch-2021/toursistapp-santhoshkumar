package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.service.Booking;

/**
 * Servlet implementation class CancelAllPackagesAction
 */
@WebServlet("/CancelAllPackagesAction")
public class CancelAllPackagesAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String countryName = request.getParameter("countryName");
		String cancelReason = request.getParameter("cancelReason");
		String status = request.getParameter("status");
		if (Booking.updateJourneyStatus(countryName, status, cancelReason)) {
			response.sendRedirect("ListOfBookings.jsp");
		}

	}
}
