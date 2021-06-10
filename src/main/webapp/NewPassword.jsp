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
	<%long mobileNumber=(long)session.getAttribute("USERNUMBER"); %>
	<%
		String errorMessage = request.getParameter("errorMessage");
		if(errorMessage != null){
			out.println("<font color='red'>" + errorMessage + "</font>");
		}
	%>
	<form action="UpadateForgotPasswordAction" method="post">
	<input type="hidden" name="mobileNumber" value=<%=mobileNumber %>>
		<label for="password"><strong>Enter password</strong></label><br>
		<input type="password" name="password" placeholder="Enter your new password" required><br>
		<p class="text-muted"><em>* First letter should be capital and length should be greater than 8</em></p>
		<label for="newpassword"><strong>Retype your password</strong></label><br>
		<input type="password" name="newPassword" placeholder="Retype your new password" required><br>
		<br>
		<button type="submit" class="btn btn-primary">Change password</button>
	</form>
</main>
</body>
</html>