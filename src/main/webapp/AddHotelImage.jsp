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

<label for="hotelName"><strong>Hotel Name</strong></label><br>
		<form action="AddHotelImageAction">
			<select name="hotelName">
			  <option value="" disabled>Select a hotel</option>
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
			<label for="myfile"><Strong>Select a image:</Strong></label><br>
			<input type="file" id="myfile" name="myfile" accept="image/*"><br>
			<br>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
</main>
</body>
</html>