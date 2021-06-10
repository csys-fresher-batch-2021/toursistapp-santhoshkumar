package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.service.SalesReport;

/**
 * Servlet implementation class SalesReportAction
 */
@WebServlet("/SalesReportAction")
public class SalesReportAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countryName=request.getParameter("countryName");
		SalesReport.specificCountryReport(countryName);
		response.sendRedirect("CountrySalesReport.jsp?countryName="+countryName);
		
	}


}
