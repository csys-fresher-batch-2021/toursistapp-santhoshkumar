package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.UserDetail;
import in.santhosh.service.UserRegistration;

/**
 * Servlet implementation class RegistrationAction
 */
@WebServlet("/RegistrationAction")
public class RegistrationAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String gender=request.getParameter("gender");
		long mobileNumber=Long.parseLong(request.getParameter("mobileNumber"));
		String password=request.getParameter("password");
		String reTypePassword=request.getParameter("retypepassword");
		
		UserDetail user=new UserDetail(name,age,gender,mobileNumber,password,reTypePassword);
		try {
			if(password.equals(reTypePassword))
			{
				if(!UserRegistration.existingUser(user))
				{
					if(UserRegistration.userRegistration(user))
					{
						response.sendRedirect("AdminLogin.jsp");
					}
					else {
						String errorMessage = "Invalid Details";
						response.sendRedirect("Registration.jsp?errorMessage=" + errorMessage);
					}
					
				}
				else {
					String existsMessage = "Already Registered";
					response.sendRedirect("Registration.jsp?existsMessage=" + existsMessage);
					
				}
			}
			else {
				String errorMessage = "Password and Retype password not matched";
				response.sendRedirect("Registration.jsp?errorMessage=" + errorMessage);
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("Registration.jsp?message=" + message);
			e.printStackTrace();
		}
		
		
		
		
	}

}
