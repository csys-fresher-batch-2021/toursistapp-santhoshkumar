<%@page import="in.santhosh.dao.PackageDao"%>
<%@page import="in.santhosh.model.TourPackageDetail"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.service.Packages"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>List of packages</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	
	
	
		<%
		String infoMessage = request.getParameter("infoMessage");
		if (infoMessage != null) {
			out.println("<font color='green'>" + infoMessage + "</font>");
		}
		%>
		<%
		String role = (String) session.getAttribute("ROLE");
		String LoginUser = (String) session.getAttribute("LOGINUSER");
		%>
		<h3>List of packages</h3>
		<div class="row">
			<%
			PackageDao dao1 = new PackageDao();
			List<TourPackageDetail> packageDetails1 = Packages.displayPackages();
			int j = 0;
			for (TourPackageDetail packages : packageDetails1) {
				j++;
			%>
			<div class="col-4">
				<div class="card">
					<img class="card-img-top" src="RetireveHotelImageAction?hotelName=<%=packages.getHotelName() %>" height="300" alt=<%=packages.getHotelName()%>>
					<div class="card-body">
						<p class="card-text">
							<strong>Country Name : </strong><%=packages.getPackageName()%></p>
						<p class="card-text">
							<strong>Package Price : </strong><%=packages.getPackagePrice()%></p>
						<p class="card-text">
							<strong>Number of days : </strong><%=packages.getNumberOfDays()%></p>
						<p class="card-text">
							<strong>Start date : </strong><%=packages.getStartDate()%></p>
						<p class="card-text">
							<strong>End date : </strong><%=packages.getEndDate()%></p>
						<p class="card-text">
							<strong>Hotel name : </strong><%=packages.getHotelName()%></p>
						<%
						if (role != null && role.equalsIgnoreCase("admin")) {
						%>
						<a
							href="DeletePackageAction?packageName=<%=packages.getPackageName()%>
						&packagePrice=<%=packages.getPackagePrice()%>
						&numberOfDays=<%=packages.getNumberOfDays()%>
						&startDate=<%=packages.getStartDate()%>
						&endDate=<%=packages.getEndDate()%>
						&hotelName=<%=packages.getHotelName()%>"
							class="btn btn-danger">Delete</a>
						<%
						}
						%>
						<%
						if (LoginUser != null && LoginUser.equalsIgnoreCase("user")) {
						%>
						<a
							href="BookingPackageAction?packageName=<%=packages.getPackageName()%>
					&packagePrice=<%=packages.getPackagePrice()%>
					&numberOfDays=<%=packages.getNumberOfDays()%>
					&startDate=<%=packages.getStartDate()%>
					&endDate=<%=packages.getEndDate()%>
					&hotelName=<%=packages.getHotelName()%>"
							class="btn btn-primary">Book</a>
						<%
						}
						%>
					</div>
				</div>
				<br> <br>
			</div>
			<%
			}
			%>
		</div>
		<%
		if (role != null && role.equalsIgnoreCase("admin")) {
		%>
		<a href="AddPackage.jsp">Add Package</a>
		<%
		}
		%>

	</main>
</body>
</html>
