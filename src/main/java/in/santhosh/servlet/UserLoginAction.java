package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.santhosh.exception.ServiceException;
import in.santhosh.service.UserLogin;

/**
 * Servlet implementation class UserLoginAction
 */
@WebServlet("/UserLoginAction")
public class UserLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long mobileNo = Long.parseLong(request.getParameter("mobileNumber"));
		String password = request.getParameter("password");
		try {
			if(UserLogin.validLogin(mobileNo, password))
			{
				HttpSession session = request.getSession();
				session.setAttribute("LOGINUSER", "USER");
				response.sendRedirect("index.jsp");
			}
			else {
					String errorMessage = "Invalid Details";
					response.sendRedirect("UserLogin.jsp?errorMessage=" + errorMessage);
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("UserLogin.jsp?message=" + message);
		}
		
	
	}

}
