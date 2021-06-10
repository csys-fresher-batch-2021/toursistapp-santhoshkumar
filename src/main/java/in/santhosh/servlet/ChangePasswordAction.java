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
 * Servlet implementation class ChangePasswordAction
 */
@WebServlet("/ChangePasswordAction")
public class ChangePasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String oldPassword = request.getParameter("password");
			String newPasssword = request.getParameter("newpassword");
			int userId = Integer.parseInt(request.getParameter("userId"));
			if (UserLogin.passwordExists(userId, oldPassword)) {
				if (UserLogin.updateUserPassword(newPasssword, userId)) {
					String infoMessage = "Updated Successfully";
					response.sendRedirect("ChangePassword.jsp?infoMessage=" + infoMessage);
				}
			} else {
				String message = "Enter old password correctly";
				response.sendRedirect("ChangePassword.jsp?errorMessage=" + message);
			}
		} catch (ServiceException | NumberFormatException e) {
			response.sendRedirect("ChangePassword.jsp?errorMessage=" + e);
		}

	}

}
