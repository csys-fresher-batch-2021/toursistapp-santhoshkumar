package in.santhosh.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.FlightDetail;
import in.santhosh.service.Flights;

/**
 * Servlet implementation class AddFlightDetailAction
 */
@WebServlet("/AddFlightDetailAction")
public class AddFlightDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String countryName = request.getParameter("countryName");
		String flightName = request.getParameter("flightName");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		LocalTime departureTime = LocalTime.parse(request.getParameter("depatureTime"));
		LocalTime arrivalTime = LocalTime.parse(request.getParameter("arrivalTime"));
		String status = request.getParameter("status");
		LocalDate date = LocalDate.parse(request.getParameter("startDate"));
		FlightDetail flightDetail = new FlightDetail(countryName, flightName, departureTime, arrivalTime, status,
				source, destination, date);
		try {
			if (!Flights.existingFlightDetail(flightDetail)) {

				if (Flights.addFlights(flightDetail)) {
					response.sendRedirect("ListOfFlight.jsp");
				} else {
					String errorMessage = "Invalid Details";
					response.sendRedirect("AddFlight.jsp?errorMessage=" + errorMessage);
				}
			} else {
				String existsMessage = "Package already exists";
				response.sendRedirect("AddFlight.jsp?existsMessage=" + existsMessage);
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("AddFlight.jsp?message=" + message);
		}

	}

}
