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
 * Servlet implementation class SearchPackageByPriceAction
 */
@WebServlet("/SearchPackageByPriceAction")
public class SearchPackageByPriceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int packagePrice=Integer.parseInt(request.getParameter("packagePrice"));
		try {
			if(Packages.packageExistsByPrice(packagePrice)){
			Packages.searchPackageByPackagePrice(packagePrice);
			response.sendRedirect("ListOfPackagesByPrice.jsp?packagePrice="+packagePrice);
			}
			else {
				String existsMessage = "No packages found";
				response.sendRedirect("SearchPackageByPrice.jsp?existsMessage=" + existsMessage);
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("SearchPackageByPrice.jsp?message=" + message);
		}
		
		
		
	}

	

}
