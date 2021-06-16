 
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="in.santhosh.model.ContactUsDetails"%>
<%@page import="in.santhosh.model.BookingDetail"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.service.Booking"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>My Booking</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<%
		String errorMessage = request.getParameter("errorMessage");
		if(errorMessage != null){
			out.println("<font color='red'>" + errorMessage + "</font>");
		}
		%>
		<%
		String infoMessage = request.getParameter("infoMessage");
		if(infoMessage != null){
			out.println("<font color='green'>" + infoMessage + "</font>");
		}
		%>
	<% int userId=(int) session.getAttribute("LOGINUSER_ID");%>
	<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	%>
	<h3>My Bookings</h3>
	<table class="table table-striped">
	<caption>My Booking Details</caption>
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
	<th scope="col"></th>
	</tr>
	
	<%
	List<BookingDetail> bookingDetail=Booking.userBookingDetail(userId);
	int i=0;
	for(BookingDetail details:bookingDetail){
		i++;
	%>
	<%
	LocalDate date=LocalDate.now();
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
	<%if(details.getStartDate().isAfter(date) && details.getStatus().equals("Confirmed")){%>
	<td>
	<a href="DeleteBookingAction?id=<%=details.getId()%>
	&packageName=<%=details.getPackageName()%>
	&packagePrice=<%=details.getPackagePrice()%>
	&numberOfDays=<%=details.getNumberOfDays()%>
	&startDate=<%=details.getStartDate()%>
	&endDate=<%=details.getEndDate()%>
	&numberOfPersons=<%=details.getNumberOfPerson()%>
	&totalPrice=<%=details.getTotalPrice()%>"class="btn btn-danger" >Cancel</a>
	</td>
	<%} %>
	</tr>
	<% } %>
	</thead>
	</table>
</main>
</body>
</html>