package in.santhosh.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.BookingDetail;
import in.santhosh.service.Booking;

/**
 * Servlet implementation class DeleteBookingAction
 */
@WebServlet("/DeleteBookingAction")
public class DeleteBookingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String packageName = request.getParameter("packageName");
			int packagePrice = Integer.parseInt(request.getParameter("packagePrice"));
			int numberOfDays = Integer.parseInt(request.getParameter("numberOfDays"));
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
			int numberOfPersons = Integer.parseInt(request.getParameter("numberOfPersons"));
			double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
			BookingDetail bookingDetail = new BookingDetail(packageName, packagePrice, numberOfDays, startDate, endDate,
					id, numberOfPersons, totalPrice);

			Booking.addCancelBooking(bookingDetail);
			if (Booking.deleteBooking(bookingDetail)) {
				String infoMessage = "Cancelled Successfully";
				response.sendRedirect("UserBookingDetail.jsp?infoMessage=" + infoMessage);
			}
		} catch (ServiceException | NumberFormatException e) {
			String errorMessage = e.getMessage();
			response.sendRedirect("UserBookingDetail.jsp?errorMessage=" + errorMessage);
		}

	}

}
