<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Verify Otp</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<% String genratedOtp=(String)session.getAttribute("LOGINUSEROTP");%>
	<form action="VerifyOtpAction">
	<label for="otp"><strong>Enter your otp</strong></label><br>
	<input type="hidden" name="gOtp" value=<%=genratedOtp%>>
		<input type="number" name="otp" placeholder="Enter the otp"  required>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</main>
</body>
</html>