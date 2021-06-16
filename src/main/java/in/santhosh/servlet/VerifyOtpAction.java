package in.santhosh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifyOtpAction
 */
@WebServlet("/VerifyOtpAction")
public class VerifyOtpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int userotp = Integer.parseInt(request.getParameter("otp"));
			int generatedOpt = Integer.parseInt(request.getParameter("gOtp"));
			if (userotp == generatedOpt) {
				response.sendRedirect("NewPassword.jsp");

			} else {
				String errorMessage = "Entered otp is incorrect";
				response.sendRedirect("ForgotPassword.jsp?errorMessage=" + errorMessage);
			}
		} catch (NumberFormatException e) {
			response.sendRedirect("ForgotPassword.jsp?errorMessage=" + e);

		}
	}
}
