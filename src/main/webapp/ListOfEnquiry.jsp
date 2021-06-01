<%@page import="in.santhosh.service.ContactDetail"%>
<%@page import="in.santhosh.model.ContactUsDetails"%>
<%@page import="java.util.List"%>
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
	<td><%=enquiryDetails.getPackagePrice()%></td>	
	<td><%=enquiryDetails.getStartDate()%></td>
	<td><%=enquiryDetails.getEndDate() %></td>
	</tr>
	<%} %>	
	</thead>
	</table>
	
	
</main>
</body>
</html>