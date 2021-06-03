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
		  <option value="" disabled >Select a country</option>
		  <option value="Dubai">Dubai</option>
		  <option value="Maldives">Maldives</option>
		  <option value="Germany">Germany</option>
		  <option value="Singapore">Singapore</option>
		  <option value="Malaysia">Malaysia</option>
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
		<label for="hotelName"><strong>Hotel Name</strong></label><br>
		<select name="hotelName">
		  <option value="" disabled>Select hotel name</option>
		  <option value="ibis Styles Dubai Jumeira">Ibis Styles Dubai Jumeira-Dubai</option>
		  <option value="Crowne Plaza Dubai Festival City">Crowne Plaza Dubai-Dubai</option>
		  <option value="Angaga Island Resort Spa">Angaga Island Resort-Maldives</option>
		  <option value="Paradise Island Resort Maldives">Paradise Island Resort-Maldives</option>
		  <option value="Park Inn by Radisson">Park Inn by Radisson-Germany</option>
		  <option value="Leonardo Royal Hotel Frankfurt">Leonardo Royal Hotel Frankfurt-Germany</option>
		  <option value="Hilton Singapore">Hilton Singapore-Singapore</option>
		  <option value=" Marina Bay Sands Singapore">Marina Bay Sands-Singapore</option>
		  <option value=" Four Seasons Hotel Kuala Lumpur">Four Seasons Hote-Malaysia</option>
		  <option value=" Banyan Tree Kuala Lumpur">Banyan Tree-Malaysia</option>
		</select>
		<br>
		<br>
		<button type="submit" class="btn btn-primary">Submit</button>
		<button  type="reset" class="btn btn-danger">Reset</button>
		
		</form>
		
		
	</main>
</body>
</html>
