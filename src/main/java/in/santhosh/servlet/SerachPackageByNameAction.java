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
 * Servlet implementation class SerachPackageByNameAction
 */
@WebServlet("/SerachPackageByNameAction")
public class SerachPackageByNameAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String countryName = request.getParameter("countryName");
		try {
			if (Packages.packageExistsByCountryName(countryName)) {
				Packages.searchPackageByName(countryName);
				response.sendRedirect("ListOfPackagesByCountry.jsp?countryName=" + countryName);
			} else {
				String existsMessage = "No packages found";
				response.sendRedirect("SearchPackageByName.jsp?existsMessage=" + existsMessage);
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("SearchPackageByName.jsp?message=" + message);
		}
	}

}
