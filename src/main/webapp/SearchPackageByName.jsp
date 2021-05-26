<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Search package by name</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	 <%
		String existsMessage=request.getParameter("existsMessage");
		if(existsMessage != null){
			out.println("<font color='red'>" + existsMessage + "</font>");
		}
		%>
	<h3>Search package by country name</h3>
	<form action="SerachPackageByNameAction">
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
		<br>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	
	
	
	
</main>
</body>
</html>