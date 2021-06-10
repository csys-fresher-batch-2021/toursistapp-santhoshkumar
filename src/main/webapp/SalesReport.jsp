<%@page import="in.santhosh.model.SalesDetail"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.service.SalesReport"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Sales Report</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<h3>Sales Report</h3>
	<table class="table table-bordered">
	<caption>List of all sales details </caption>
	<thead>
	<tr>
	<th scope="col">S.NO</th>
	<th scope="col">Country</th>
	<th scope="col">Number Of Persons Booked</th>
	<th scope="col"></th>
	</tr>
	<%
	List<SalesDetail> detail=SalesReport.packageSalesReport();
	int i=0;
	for(SalesDetail salesDetail:detail){
		i++;
	%>
	
	<tr>
	<td><%=i %></td>
	<td><%=salesDetail.getCountryName()%></td>
	<td><%=salesDetail.getTotalBooking() %></td>
	<td> <a href="SalesReportAction?countryName=<%=salesDetail.getCountryName()%>
	&totalBooking=<%=salesDetail.getTotalBooking()%>" class="btn btn-info" >Click Here</a></td>
	</tr>
	
	<%} %>
</thead>
</table>
</main>
</body>
</html>