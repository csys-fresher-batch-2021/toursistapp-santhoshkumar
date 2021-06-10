<%@page import="in.santhosh.model.BookingDetail"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.service.SalesReport"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Country sales report</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<%
	String countryName=request.getParameter("countryName");
	%>
	<%int totalPrice=SalesReport.totalPriceForSpecificCountry(countryName); %>
	<br>
	<br>
	<span class="border border-primary"><strong>Total Booking Price:<%=totalPrice%></strong></span>
	<br>
	<br>
	<table class="table table-bordered">
	<caption>Booking Details</caption>
	<thead>
	<tr>
	<th scope="col">S.No</th>
	<th scope="col">User ID</th>
	<th scope="col">Package Name</th>
	<th scope="col">Package Price</th>
	<th scope="col">Number Of Days</th>
	<th scope="col">Start Date</th>
	<th scope="col">End Date</th>
	<th scope="col">Number Of Person</th>
	<th scope="col">Total Price</th>
	</tr>
	<%
	List<BookingDetail> bookingDetail=SalesReport.specificCountryReport(countryName);
	int i=0;
	for(BookingDetail details:bookingDetail){
		i++;
	%>
	<tr>
	<td><%=i%></td>
	<td><%=details.getId()%></td>
	<td><%=details.getPackageName()%></td>
	<td><%=details.getPackagePrice()%></td>
	<td><%=details.getNumberOfDays()%></td>
	<td><%=details.getStartDate()%></td>
	<td><%=details.getEndDate()%></td>
	<td><%=details.getNumberOfPerson()%></td>
	<td><%=details.getTotalPrice()%></td>
	</tr>
	<%} %>
	
	
	
	</thead>
	</table>
</main>
</body>
</html>