<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="in.santhosh.service.ContactDetail"%>
<%@page import="in.santhosh.model.ContactUsDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Enquiry</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	%>
	<h3>List of enquiry</h3>
	<table class="table table-bordered">
	<caption>List of all enquiry</caption>
	<thead>
	<tr>
	<th scope="col">S.NO</th>
	<th scope="col">Name</th>
	<th scope="col">Mobile Number</th>
	<th scope="col">Country Name</th>
	<th scope="col">Package Price</th>
	<th scope="col">Start Date</th>
	<th scope="col">End Date</th>
	<th scope="col">Status</th>
	<th scope ="col"></th>
	</tr>
	<%
	List<ContactUsDetails> details=ContactDetail.displayAllEnquiry();
	int i=0;
	for(ContactUsDetails enquiryDetails:details){
		i++;
	%> 
	<tr>
	<td><%=i%></td>
	<td><%=enquiryDetails.getName() %></td>
	<td><%=enquiryDetails.getMobileNumber()%></td>	
	<td><%=enquiryDetails.getCountryName()%></td>	
	<td>Rs.<%=enquiryDetails.getPackagePrice()%></td>	
	<td><%=formatter.format(enquiryDetails.getStartDate())%></td>
	<td><%=formatter.format(enquiryDetails.getEndDate())%></td>
	<td><%=enquiryDetails.getStatus() %></td>
	<% if(enquiryDetails.getStatus().equals("Pending")){%>
	<td><a href="UpdateEnquiryStatus?mobileNumber=<%=enquiryDetails.getMobileNumber() %>" class="btn btn-primary">Done</a>
	</td>	
	<%}%>
	</tr>
	<%} %>
	</thead>
	</table>
	
	
</main>
</body>
</html>