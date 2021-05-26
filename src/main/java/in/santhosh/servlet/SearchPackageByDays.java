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
 * Servlet implementation class SearchPackageByDays
 */
@WebServlet("/SearchPackageByDays")
public class SearchPackageByDays extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int days=Integer.parseInt(request.getParameter("days"));
		try {
			if(Packages.packageExistsByDays(days)){
			Packages.searchPackageByDays(days);
			response.sendRedirect("ListOfPackagesByDays.jsp?days="+days);
			}
			else {
				String existsMessage = "No packages found";
				response.sendRedirect("SearchPackageByNumberOfDays.jsp?existsMessage=" + existsMessage);
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("SearchPackageByNumberOfDays.jsp?message=" + message);
		}
	}


}
