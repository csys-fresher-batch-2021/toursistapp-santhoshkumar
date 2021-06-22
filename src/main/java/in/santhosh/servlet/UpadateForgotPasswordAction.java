package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.santhosh.exception.ServiceException;
import in.santhosh.service.UserLogin;

/**
 * Servlet implementation class UpadateForgotPasswordAction
 */
@WebServlet("/UpadateForgotPasswordAction")
public class UpadateForgotPasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
			String password = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
			if (password.equals(newPassword)) {
				UserLogin.updateForgotPassword(mobileNumber, newPassword);
				response.sendRedirect("UserLogin.jsp");
			} else {
				String errorMessage = "Enter password and retype password correctly";
				response.sendRedirect("NewPassword.jsp?errorMessage=" + errorMessage);
			}
		} catch (ServiceException | NumberFormatException | IOException e) {
			response.sendRedirect("NewPassword.jsp?errorMessage=" + e);
		}
	}

}
