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
	 <%
		String existsMessage=request.getParameter("existsMessage");
		if(existsMessage != null){
			out.println("<font color='red'>" + existsMessage + "</font>");
		}
	%>
	<h3>Search package by number of days</h3>
	<form action="SearchPackageByDays">
	<label for="days"><strong>Days</strong></label><br>
	<input type="number" name="days" placeholder="Enter the number of days" min=3 max=15   style="width: 200px" required >
	<br>
	<br>
	<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	
	
	
</main>
</body>
</html>