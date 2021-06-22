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
	private static final String REGISTRATION_JSP_ERROR_MESSAGE = "Registration.jsp?errorMessage=";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
			String password = request.getParameter("password");
			String reTypePassword = request.getParameter("retypepassword");
			UserDetail user = new UserDetail(name, age, gender, mobileNumber, password, reTypePassword);
			if (password.equals(reTypePassword)) {
				if (!UserRegistration.existingUser(user)) {
					if (UserRegistration.userRegistration(user)) {
						response.sendRedirect("AdminLogin.jsp");
					} else {
						String errorMessage = "Invalid Details";
						response.sendRedirect(REGISTRATION_JSP_ERROR_MESSAGE + errorMessage);
					}

				} else {
					String existsMessage = "Already Registered";
					response.sendRedirect("Registration.jsp?existsMessage=" + existsMessage);

				}
			} else {
				String errorMessage = "Password and Retype password not matched";
				response.sendRedirect(REGISTRATION_JSP_ERROR_MESSAGE + errorMessage);
			}
		} catch (ServiceException | NumberFormatException | IOException e) {
			String errorMessage = e.getMessage();
			response.sendRedirect(REGISTRATION_JSP_ERROR_MESSAGE + errorMessage);
			e.printStackTrace();
		}

	}

}
