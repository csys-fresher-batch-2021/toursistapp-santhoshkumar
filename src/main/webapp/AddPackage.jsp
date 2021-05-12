<!DOCTYPE>
<html lang="en">
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
		<form action="PackageAction">
		<label for="countryName"><strong>Country name</strong></label><br>
		<input type="text" name="countryName" placeholder="Enter country name" autofocus required>
		<br>
		<label for="packagePrice"><strong>Package price</strong></label><br>
		<input type="number" name="packagePrice" placeholder="Enter the package price" min=10000 required>
		<br>
		<label for="days"><strong>Days</strong></label><br>
		<input type="number" name="days" placeholder="Enter the number of days" min=3 max=15 required>
		<br>
		<label for="startDate"><strong>Journey start date</strong></label><br>
		<input type="date" name="startDate" placeholder="Journey start date" required>
		<br>
		<label for="endDate"><strong>Journey end date</strong></label><br>
		<input type="date"  name="endDate" placeholder="Journey end date" required>
		<br>
		<br>
		<button type="submit" class="btn btn-primary">Add package</button>
		<button  type="reset" class="btn btn-danger">Reset</button>
		
		</form>
		
	</main>
</body>
</html>
