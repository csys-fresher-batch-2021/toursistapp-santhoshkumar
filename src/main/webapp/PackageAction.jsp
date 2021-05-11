<%@page import="in.santhosh.service.Packages"%>
<%@page import="java.time.LocalDate"%>
<%@page import="in.santhosh.model.TourPackageDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">

		<%
		LocalDate startDate, endDate;
		String country = request.getParameter("countryName");
		int price = Integer.parseInt(request.getParameter("packagePrice"));
		int days = Integer.parseInt(request.getParameter("days"));
		startDate = LocalDate.parse(request.getParameter("startDate"));
		endDate = LocalDate.parse(request.getParameter("endDate"));

		TourPackageDetails packages = new TourPackageDetails(country, price, days, startDate, endDate);
		boolean isvalidPackage = Packages.addPackage(packages);

		if (isvalidPackage) {
			String infoMessage = "Package added successfully";
			response.sendRedirect("AddPackage.jsp?infoMessage=" + infoMessage);
		} else {
			String message = "Invalid Details";
			response.sendRedirect("AddPackage.jsp?errorMessage=" + message);

			//response.sendRedirect("AddPackage.jsp?errorMessage=" + message);
		}
		%>


	</main>

</body>
</html>