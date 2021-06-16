package in.santhosh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.santhosh.model.UserDetail;
import in.santhosh.service.SendOtp;
import in.santhosh.service.UserLogin;

/**
 * Servlet implementation class SendOtpAction
 */
@WebServlet("/SendOtpAction")
public class SendOtpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String mobileNumber = request.getParameter("mobileNumber");
			long number = Long.parseLong(mobileNumber);
			List<UserDetail> detail = UserLogin.getUserDetail(number);
			if (!detail.isEmpty()) {

				String otp = SendOtp.userOpt(mobileNumber);
				HttpSession session = request.getSession();
				session.setAttribute("LOGINUSEROTP", otp);
				session.setAttribute("USERNUMBER", number);
				response.sendRedirect("VerifyOtp.jsp");
			} else {
				String errorMessage = "Your mobile number is not registered";
				response.sendRedirect("ForgotPassword.jsp?errorMessage=" + errorMessage);
			}
		} catch (NumberFormatException e) {
			String errorMessage = "Enter mobile Number correctly";
			response.sendRedirect("ForgotPassword.jsp?errorMessage=" + errorMessage);
		}
	}

}
