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
 * Servlet implementation class PackageBookingAction
 */
@WebServlet("/PackageBookingAction")
public class PackageBookingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String packageName = request.getParameter("packageName");
			int packagePrice = Integer.parseInt(request.getParameter("packagePrice"));
			int numberOfDays = Integer.parseInt(request.getParameter("numberOfDays"));
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
			int id = Integer.parseInt(request.getParameter("id"));
			int numberOfPreson = Integer.parseInt(request.getParameter("person"));
			String status = request.getParameter("status");
			String comment = request.getParameter("comment");
			BookingDetail bookingDetail = new BookingDetail(packageName, packagePrice, numberOfDays, startDate, endDate,
					id, numberOfPreson);
			double totalPrice = Booking.calculateBill(bookingDetail);
			if (Booking.bookingPackage(bookingDetail, totalPrice, status, comment)) {
				response.sendRedirect("FinalBookingPage.jsp");
			}
		} catch (ServiceException | NumberFormatException| IOException e) {
			String errorMessage = e.getMessage();
			response.sendRedirect("BookingDetail.jsp?errorMessage=" + errorMessage);
		}

	}

}
