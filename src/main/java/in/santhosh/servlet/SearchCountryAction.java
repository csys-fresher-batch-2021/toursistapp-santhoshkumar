package in.santhosh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.TourPackageDetail;
import in.santhosh.service.Packages;
import in.santhosh.util.GsonUtil;

/**
 * Servlet implementation class SearchCountryAction
 */
@WebServlet("/SearchCountryAction")
public class SearchCountryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String packageName = request.getParameter("countryName");
		HttpSession session = request.getSession();
		session.setAttribute("SEARCHCOUNTRY", packageName);
		try {

			List<TourPackageDetail> details = Packages.searchCountry(packageName);
			if (!details.isEmpty()) {
				Gson gson = GsonUtil.create();
				String packageDetailJson = gson.toJson(details);
				PrintWriter out = response.getWriter();
				out.print(packageDetailJson);
				out.flush();
			} else {
				String errorMessage = "No packages found";
				response.sendRedirect("SearchCountry.jsp?errorMessage=" + errorMessage);
			}
		} catch (ServiceException e) {
			response.sendRedirect("SearchCountry.jsp?errorMessage=" + e.getMessage());
		}
	}

}
