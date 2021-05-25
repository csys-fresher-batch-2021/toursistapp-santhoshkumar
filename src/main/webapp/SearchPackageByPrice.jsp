<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Search package by Price</title>
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
	<h3>Search package by price</h3>
	<form action="SearchPackageByPriceAction">
	<label for="packagePrice"><strong>Package price</strong></label><br>
	<input type="number" name="packagePrice" placeholder="Enter the package price" min=10000 required>
	<br>
	<br>
	<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	
	
</main>
</body>
</html>