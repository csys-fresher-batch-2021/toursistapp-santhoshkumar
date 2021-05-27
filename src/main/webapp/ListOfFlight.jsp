<%@page import="java.util.List"%>
<%@page import="in.santhosh.service.Flights"%>
<%@page import="in.santhosh.model.FlightDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Delete Flights</title>
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
	<h3>List of flights</h3>
	<table class="table table-bordered">
	<caption>List of flight details</caption>
	
	<thead>
	<tr>
	<th scope="col">S.No</th>
	<th scope="col">Country name</th>
	<th scope="col">Flight Name</th>
	<th scope="col">Source</th>
	<th scope="col">Destination</th>
	<th scope="col">Journey date</th>
	<th scope="col">Departure time</th>
	<th scope="col">Arrival time</th>
	<th scope="col">Status</th>
	<th scope="col"></th>
	</tr>
	<% 
	List<FlightDetail> flights=Flights.displayFlight();
	int i=0;
	for(FlightDetail flightList:flights){
	i++;
	%>
	<tr>
	<td><%=i%></td>
	<td><%=flightList.getCountryName()%></td>
	<td><%=flightList.getFlightName() %></td>
	<td><%=flightList.getSource() %></td>
	<td><%=flightList.getDestination()%></td>
	<td><%=flightList.getJourneyDate() %></td>
	<td><%=flightList.getDeparture() %></td>
	<td><%=flightList.getArrival() %></td>
	<td><%=flightList.getStatus() %></td>
	<td>
	<a href="DeleteFlightAction?countryName=<%=flightList.getCountryName()%>
	&flightName=<%=flightList.getFlightName() %>
	&source=<%=flightList.getSource() %>
	&destination=<%=flightList.getDestination() %>
	&departureTime=<%=flightList.getDeparture() %>
	&arrivalTime=<%=flightList.getArrival() %>
	&status=<%=flightList.getStatus() %>
	&journeyDate=<%=flightList.getJourneyDate() %>" class="btn btn-danger">Delete</a>
	</td>
	</tr>
	<%
	}
	%>
	</thead>
	</table>
</main>
</body>
</html>