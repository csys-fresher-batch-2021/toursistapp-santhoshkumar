<html>
<head>
<title>Tourist Management App</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Add packages</h3>
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
		<form action="PackageAction.jsp">
		<label for="countryName"><b>Country name</b></label><br>
		<input type="text" name="countryName" placeholder="Enter country name" autofocus required>
		<br>
		<label for="packagePrice"><b>Package price</b></label><br>
		<input type="number" name="packagePrice" placeholder="Enter the package price" min=10000 required>
		<br>
		<label for="days"><b>Days</b></label><br>
		<input type="number" name="days" placeholder="Enter the number of days" min=3 max=15 required>
		<br>
		<label for="startDate"><b>Journey start date</b></label><br>
		<input type="date" id="startDate" name="startDate" placeholder="Journey start date" required>
		<br>
		<label for="endDate"><b>Journey end date</b></label><br>
		<input type="date" id="endDate" name="endDate" placeholder="Journey end date" required>
		<br>
		<br>
		<button type="submit" class="btn btn-primary">Add package</button>
		
		</form>
		
	</main>
</body>
</html>
