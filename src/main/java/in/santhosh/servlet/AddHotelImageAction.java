package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.service.Packages;

/**
 * Servlet implementation class AddHotelImageAction
 */
@WebServlet("/AddHotelImageAction")
public class AddHotelImageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hotelName = request.getParameter("hotelName");
		String imageLocation = request.getParameter("myfile");
		try {
			Packages.addHotelImage(hotelName, imageLocation);
			response.sendRedirect("AddHotelImage.jsp");
		} catch (ServiceException e) {
			String errorMessage = "unable to add image";
			response.sendRedirect("AddHotelImage.jsp?errorMessage=" + errorMessage);
		}
	}
}
