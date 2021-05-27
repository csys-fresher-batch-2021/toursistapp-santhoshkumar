<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add flight</title>
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
		String existsMessage=request.getParameter("existsMessage");
		if(existsMessage != null){
			out.println("<font color='red'>" + existsMessage + "</font>");
		}
	%>
	<form action="AddFlightDetailAction">
	<label for="countryName"><strong>Country Name</strong></label><br>
		<select name="countryName">
		  <option value="">Select a country</option>
		  <option value="Dubai">Dubai</option>
		  <option value="Maldives">Maldives</option>
		  <option value="Germany">Germany</option>
		  <option value="Singapore">Singapore</option>
		  <option value="Malaysia">Malaysia</option>
		  <option value="Manali">Manali</option>
		</select>
		<br>
		<label for="flightName"><strong>Flight</strong></label><br>
		<input type="text" name="flightName" placeholder="Enter flight name" required><br>
		<label for="source"><strong>From</strong></label><br>
		<input type="text" name="source" placeholder="Enter the source" required><br>
		<label for="destination"><strong>To</strong></label><br>
		<input type="text" name="destination" placeholder="Enter the destination" required><br>
		<label for="startDate"><strong>Journey start date</strong></label><br>
		<input type="date" name="startDate" placeholder="Journey start date" required><br>
		<label for="depatureTime"><strong>Departure time</strong></label><br>
		<input type="time" name="depatureTime" placeholder="Depature time" required><br>
		<label for="arrivalTime"><strong>Arrival time</strong></label><br>
		<input type="time" name="arrivalTime" placeholder="Arrival time" required><br>
		<label for="status"><strong>Status</strong></label><br>
		<input type="radio" value="Depart" name="status" checked >Depart   
		<input type="radio" value="Return" name="status"> Return<br>   
		<br>
		<button type="submit" class="btn btn-primary">Submit</button>
		<button  type="reset" class="btn btn-danger">Reset</button>
	
	</form>
	
	
	
</main>
</body>
</html>