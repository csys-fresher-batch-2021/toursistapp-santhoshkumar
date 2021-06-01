<%@page import="in.santhosh.service.Packages"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.model.TourPackageDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Search package by price</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<%String role = (String)session.getAttribute("ROLE");
		String LoginUser=(String)session.getAttribute("LOGINUSER");
		int packagePrice=Integer.parseInt(request.getParameter("packagePrice"));
	%>
	<h3>List of packages</h3>
		<table class="table table-bordered">
			<caption>List all the packages with details</caption>
			<thead>
				<tr>
					<th scope="col">S.No</th>
					<th scope="col">Country Name</th>
					<th scope="col">Package Price</th>
					<th scope="col">Days</th>
					<th scope="col">Journey Start Date</th>
					<th scope="col">Journey End Date</th>
					<th scope="col">Hotel Name</th>
					<th scope="col"></th>
				</tr>
				<%
				//PackageDao dao=new PackageDao();
				List<TourPackageDetail> packageDetails =Packages.searchPackageByPackagePrice(packagePrice);
				int i = 0;
				for (TourPackageDetail packages : packageDetails) {
					i++;
				%>
				<tr>
					<td><%=i%></td>
					<td><%=packages.getPackageName()%></td>
					<td><%=packages.getPackagePrice()%></td>
					<td><%=packages.getNumberOfDays()%></td>
					<td><%=packages.getStartDate()%></td>
					<td><%=packages.getEndDate()%></td>
					<td><%=packages.getHotelName() %></td>
					<%if(LoginUser!=null && LoginUser.equalsIgnoreCase("user")) {%>
					<td><a href="BookingPackageAction?packageName=<%=packages.getPackageName()%>
					&packagePrice=<%=packages.getPackagePrice()%>
					&numberOfDays=<%=packages.getNumberOfDays()%>
					&startDate=<%=packages.getStartDate()%>
					&endDate=<%=packages.getEndDate()%>
					&hotelName=<%=packages.getHotelName()%>"
							class="btn btn-primary">Book</a></td>
					<%} %>
				</tr>
				<%
				}
				%>
			</thead>
		</table>
		<%if(role !=null && role.equalsIgnoreCase("admin")){%>
		<a href="AddPackage.jsp">Add Package</a>
		<%} %>
</main>

</body>
</html>