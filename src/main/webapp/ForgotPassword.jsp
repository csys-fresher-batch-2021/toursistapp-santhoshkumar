<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Forgot password</title>
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
	<form action=SendOtpAction>
	<label for="mobileNumber"><strong>Mobile Number</strong></label><br>
	<input type="tel" name="mobileNumber" placeholder="Enter your mobile number" required><br>
	<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</main>
</body>
</html>