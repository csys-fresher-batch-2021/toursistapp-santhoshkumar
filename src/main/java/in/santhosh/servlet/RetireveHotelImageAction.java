package in.santhosh.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.service.Packages;

/**
 * Servlet implementation class RetireveHotelImageAction
 */
@WebServlet("/RetireveHotelImageAction")
public class RetireveHotelImageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hotelName = request.getParameter("hotelName");
		try {
			byte[] image = Packages.retireveHotelImage(hotelName);
			OutputStream obj = response.getOutputStream();
			obj.write(image);
		} catch (ServiceException e) {
			String errorMessage = "unable to retireve image";
			response.sendRedirect("BookingDetail.jsp?errorMessage=" + errorMessage);
		}

	}

}
