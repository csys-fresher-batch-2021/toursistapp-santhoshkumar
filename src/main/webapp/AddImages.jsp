<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add images</title>
</head>
<body>
<%
		String errorMessage = request.getParameter("errorMessage");
		if(errorMessage != null){
			out.println("<font color='red'>" + errorMessage + "</font>");
		}
	%>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<form action="AddImageAction" >
		<label for="countryName"><strong>Country Name</strong></label><br>
		<select name="countryName">
		  <option value="" disabled>Select a country</option>
		  <option value="Dubai">Dubai</option>
		  <option value="Maldives">Maldives</option>
		  <option value="Germany">Germany</option>
		  <option value="Singapore">Singapore</option>
		  <option value="Malaysia">Malaysia</option>
		  <option value="Manali">Manali</option>
		</select>
		<br>
		<label for="myfile"><Strong>Select a image:</Strong></label><br>
		<input type="file" id="myfile" name="myfile" accept="image/*"><br>
		<br>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</main>
</body>
</html>