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
 * Servlet implementation class BookingPackageAction
 */
@WebServlet("/BookingPackageAction")
public class BookingPackageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate startDate;
		LocalDate endDate;
		int price = 0;
		int days = 0;
		String packageName = request.getParameter("packageName");
		price = Integer.parseInt(request.getParameter("packagePrice"));
		days = Integer.parseInt(request.getParameter("numberOfDays"));
		startDate = LocalDate.parse(request.getParameter("startDate"));
		endDate = LocalDate.parse(request.getParameter("endDate"));
		String hotelName=request.getParameter("hotelName");
		TourPackageDetail packageList = new TourPackageDetail(packageName, price, days, startDate, endDate,hotelName);
		
		try {
			Packages.userSelectedPackage(packageList);
			response.sendRedirect("BookingDetail.jsp?packageName="+packageName+"&packagePrice="+price+
					"&NumberOfDays="+days+"&startDate="+startDate+"&endDate="+endDate+"&hotelName="+hotelName);
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("ListOfPackages.jsp?message=" + message);

		}
		

	}

}
