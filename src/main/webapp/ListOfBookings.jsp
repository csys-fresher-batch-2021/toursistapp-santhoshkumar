<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.model.BookingDetail"%>
<%@page import="in.santhosh.service.Booking"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>List Of Bookings</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	%>
	<h3>List of Bookings</h3>
	<table class="table table-striped">
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
	<th scope="col">Status</th>
	<th scope="col">Comment</th>
	</tr>
	
	<%List<BookingDetail> bookingDetail=Booking.listOfBookings();
	int i=0;
	for(BookingDetail details:bookingDetail){
		i++;
	%>
	<tr>
	<td><%=i%></td>
	<td><%=details.getId()%></td>
	<td><%=details.getPackageName()%></td>
	<td>Rs.<%=details.getPackagePrice()%></td>
	<td><%=details.getNumberOfDays()%></td>
	<td><%=formatter.format(details.getStartDate())%></td>
	<td><%=formatter.format(details.getEndDate())%></td>
	<td><%=details.getNumberOfPerson()%></td>
	<td>Rs.<%=details.getTotalPrice()%></td>
	<td><%=details.getStatus() %></td>
	<td><%=details.getComment() %></td>
	</tr>
	<%} %>
	</thead>
	
	</table>
</main>
</body>
</html>