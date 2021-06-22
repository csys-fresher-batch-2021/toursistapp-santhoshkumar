package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.service.ContactDetail;

/**
 * Servlet implementation class UpdateEnquiryStatus
 */
@WebServlet("/UpdateEnquiryStatus")
public class UpdateEnquiryStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long mobileNumber=Long.parseLong(request.getParameter("mobileNumber"));
			ContactDetail.updateEnquiry(mobileNumber);
			response.sendRedirect("ListOfEnquiry.jsp");
		} catch (NumberFormatException  | IOException e) {
			response.sendRedirect("ListOfEnquiry.jsp?errorMessage="+e);
			
		} 
		
	}

	
}
