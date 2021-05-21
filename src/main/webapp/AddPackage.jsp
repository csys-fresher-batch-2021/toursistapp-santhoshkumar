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
		<%
		String existsMessage=request.getParameter("existsMessage");
		if(existsMessage != null){
			out.println("<font color='red'>" + existsMessage + "</font>");
		}
		%>
		<%
		String invalidDate=request.getParameter("invalidDate");
		if(invalidDate != null){
			out.println("<font color='red'>" + invalidDate + "</font>");
		}
		%>
		<form action="AddPackageAction" >
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
