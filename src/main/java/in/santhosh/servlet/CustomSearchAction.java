package in.santhosh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.santhosh.exception.ServiceException;
import in.santhosh.model.TourPackageDetail;
import in.santhosh.service.Packages;
import in.santhosh.util.GsonUtil;

/**
 * Servlet implementation class CustomSearchAction
 */
@WebServlet("/CustomSearchAction")
public class CustomSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String packageName=request.getParameter("countryName");
		String packagePrice=request.getParameter("packagePrice");
		String days=request.getParameter("days");
		try {
			
			List<TourPackageDetail> details=Packages.customSearch(packageName, packagePrice, days);
			if(!details.isEmpty()) {
				Gson gson =GsonUtil.create();
				String packageDetailJson = gson.toJson(details);
				PrintWriter out = response.getWriter();
				out.print(packageDetailJson);
				out.flush();
			}
			else {
				String errorMessage="No packages found";
				response.sendRedirect("CustomSearch.jsp?errorMessage="+errorMessage);
			}
		} catch (ServiceException e) {
			response.sendRedirect("CustomSearch.jsp?errorMessage=" + e.getMessage());
		} 
	}


}
