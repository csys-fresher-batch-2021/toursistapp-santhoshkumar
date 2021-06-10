<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Change password</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<%
		int userId = (int) session.getAttribute("LOGINUSER_ID");
		%>
		<%
		String errorMessage = request.getParameter("errorMessage");
		if(errorMessage != null){
			out.println("<font color='red'>" + errorMessage + "</font>");
		}
		%>
		<%
		String infoMessage = request.getParameter("infoMessage");
		if(infoMessage != null){
			out.println("<font color='green'>" + infoMessage + "</font>");
		}
		%>
	<form action="ChangePasswordAction" method="post">
	<input type="hidden" name="userId" value=<%=userId %>>
		<label for="password"><strong>Old Password</strong></label><br>
		<input type="password" name="password" placeholder="Enter your old password" required><br>
		<label for="newpassword"><strong>Enter your new  password</strong></label><br>
		<input type="password" name="newpassword" placeholder="Enter your new password" required><br>
		<p class="text-muted"><em>* First letter should be capital and length should be greater than 8</em></p>
		<br>
		<button type="submit" class="btn btn-primary">Change password</button>
	</form>
	
	
	
</main>
</body>
</html>