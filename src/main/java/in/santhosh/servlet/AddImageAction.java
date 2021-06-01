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
 * Servlet implementation class AddImageAction
 */
@WebServlet("/AddImageAction")
public class AddImageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countryName=request.getParameter("countryName");
		String imageLocation=request.getParameter("myfile");
		try {
			Packages.addPackageImage(countryName, imageLocation);
			response.sendRedirect("AddImages.jsp");
		} catch (ServiceException e) {
			String errorMessage="unable to add image";
			response.sendRedirect("AddImageAction.jsp?errorMessage=" + errorMessage);
		}
	
	}

	
}
