package in.santhosh.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.ContactUsDetails;
import in.santhosh.service.ContactDetail;

/**
 * Servlet implementation class contactUsAction
 */
@WebServlet("/contactUsAction")
public class ContactUsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		long mobileNumber=Long.parseLong(request.getParameter("mobileNumber"));
		String countryName=request.getParameter("countryName");
		int packagePrice=Integer.parseInt(request.getParameter("price"));
		int numberOfDays=Integer.parseInt(request.getParameter("numberOfDays"));
		
		LocalDate startDate=LocalDate.parse(request.getParameter("startDate"));
		LocalDate endDate=LocalDate.parse(request.getParameter("endDate"));
		ContactUsDetails contactDetails=new ContactUsDetails(name,mobileNumber,countryName,packagePrice,startDate,endDate);
		try {
			if(!ContactDetail.existsEnquiry(contactDetails)) {
			if(ContactDetail.contactDetail(contactDetails))
			{
				String infoMessage="Response sended successfully";
				response.sendRedirect("BookingDetail.jsp?infoMessage="+infoMessage+"&packageName="+countryName+
						"&packagePrice="+packagePrice+"&startDate="+startDate+"&endDate="+endDate+"&NumberOfDays="+numberOfDays);
				
			}
			else {
				String errorMessage = "Invalid Details";
				response.sendRedirect("BookingDetail.jsp?errorMessage="+errorMessage+"&packageName="+countryName+
						"&packagePrice="+packagePrice+"&startDate="+startDate+"&endDate="+endDate+"&NumberOfDays="+numberOfDays);
				
			}
			}else { 
				String existsMessage = "you have already responded";
				response.sendRedirect("BookingDetail.jsp?existsMessage="+existsMessage+"&packageName="+countryName+
						"&packagePrice="+packagePrice+"&startDate="+startDate+"&endDate="+endDate+"&NumberOfDays="+numberOfDays);
			}
		}catch(ServiceException e){
			String message = e.getMessage();
			response.sendRedirect("BookingDetail.jsp?message=" + message);
			
		}
		
		
	}

}
