<%@page import="in.santhosh.model.TourPackageDetail"%>
<%@page import="java.time.LocalDate"%>
<%@page import="in.santhosh.model.FlightDetail"%>
<%@page import="in.santhosh.service.Packages"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Booking details</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<%
		int userId = (int) session.getAttribute("LOGINUSER_ID");
		%>
		<%
		String packageName = request.getParameter("packageName");
		int packagePrice = Integer.parseInt(request.getParameter("packagePrice"));
		int days = Integer.parseInt(request.getParameter("NumberOfDays"));
		LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
		String hotelName = request.getParameter("hotelName");
		String status=request.getParameter("status");
		TourPackageDetail packageDetail = new TourPackageDetail(packageName, packagePrice, days, startDate, endDate, hotelName);
		%>
		<br>
		<div class="card">
			<div class="card-body">
				<p class="h3">
					<strong><%=packageName%><%=days%>D</strong>
				</p>
				<p class="h4">
					<strong><%=hotelName%></strong>
				</p>
				
			</div>
		</div>

		<div>
			<img src="RetireveImageAction?packageName=<%=packageName%>"
				alt="image" height="400" width="100%">
		</div>
		<br> <br>
		<%
		List<FlightDetail> flights = Packages.userSelectedPackage(packageDetail);
		int i = 0;
		for (FlightDetail flightList : flights) {
			i++;
		%>
		<div class="row">
			<br> <br>
			<div class="col-8">
				<div class="card w-75">
					<div class="card-body">
						<h5 class="card-title">Depart flight details</h5>
						<hr style="width: 50%">
						<div class="float-left">
							<strong><%=flightList.getDeparture()%></strong>
						</div>
						<div class="float-right">
							<strong><%=flightList.getArrival()%></strong>
						</div>
						<br>
						<div class="float-left">
							<strong><%=flightList.getSource()%></strong>
						</div>
						<div class="float-right">
							<strong><%=flightList.getDestination()%></strong>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
			<div class="col-4">
				<form action="contactUsAction">
					<div class="border">
						<%
						String infoMessage = request.getParameter("infoMessage");
						if (infoMessage != null) {
							out.println("<font color='green'>" + infoMessage + "</font>");
						}
						%>
						<%
						String errorMessage = request.getParameter("errorMessage");
						if (errorMessage != null) {
							out.println("<font color='red'>" + errorMessage + "</font>");
						}
						%>

						<%
						String existsMessage = request.getParameter("existsMessage");
						if (existsMessage != null) {
							out.println("<font color='red'>" + existsMessage + "</font>");
						}
						%>
						<h3 class="text-center">Enquiry</h3>
					</div>
					<div class="border">
						<div class="text-center">
							<input type="hidden" name="status" value="Pending">
							<input type="hidden" name="numberOfDays" value=<%=days%>>
							<input type="hidden" name="countryName" value=<%=packageName%>>
							<input type="hidden" name="startDate" value=<%=startDate%>>
							<input type="hidden" name="endDate" value=<%=endDate%>> <input
								type="hidden" name="price" value=<%=packagePrice%>> <label
								for="name"><strong>Name</strong></label><br> <input
								type="text" placeholder="Enter your name" name="name" autofocus
								required><br> <label for="mobileNumber"><strong>Mobile
									number</strong></label><br> <input type="tel"
								placeholder="Enter your mobile number" name="mobileNumber"
								required><br> <br>
							<button type="submit" class="btn btn-primary">Contact me</button>
							<button type="reset" class="btn btn-danger">Reset</button>
							<br> <br>
						</div>
					</div>
				</form>
			</div>
		</div>

		<br>
		<%
		List<FlightDetail> returnFlights = Packages.userSelectedPackageReturnFlightDetail(packageDetail, "Return");
		for (FlightDetail flightList : returnFlights) {
		%>
		<div class="row">
			<div class="col-8">
				<div class="card w-75">
					<div class="card-body">
						<h5 class="card-title">Return flight details</h5>
						<hr style="width: 75%">
						<div class="float-left">
							<strong><%=flightList.getDeparture()%></strong>
						</div>
						<div class="float-right">
							<strong><%=flightList.getArrival()%></strong>
						</div>
						<br>
						<div class="float-left">
							<strong><%=flightList.getSource()%></strong>
						</div>
						<div class="float-right">
							<strong><%=flightList.getDestination()%></strong>
						</div>
						<br>
					</div>

				</div>
			</div>
			<div class="col-4">
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<h3 class="card-title">Book now</h3>
						<h2 class="card-text"><%=packageName%></h2>
						<h3 class="card-text">
							Rs.<%=packagePrice%></h3>
						<p class="card-text">per person</p>
						<form action="PackageBookingAction">
							<input type="hidden" name="status" value="Confirmed">
							<input type="hidden" name="comment" value="Have a safe journey">
							<input type="hidden" name="packageName" value=<%=packageName%>>
							<input type="hidden" name="packagePrice" value=<%=packagePrice%>>
							<input type="hidden" name="numberOfDays" value=<%=days%>>
							<input type="hidden" name="startDate" value=<%=startDate%>>
							<input type="hidden" name="endDate" value=<%=endDate%>> <input
								type="hidden" name="hotelName" value=<%=hotelName%>> <input
								type="hidden" name="id" value=<%=userId%>> <label
								for="person"><Strong>Person</Strong></label><br> <input
								type="number" name="person" placeholder="number of passengers"
								min="1" max="10" required><br> <br>
							<button type="submit" class="btn btn-primary">Book</button>
							<br>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		%>
		<hr>
		<p class="font-weight-bold">Stay</p>
		<ul>
			<li class="font-weight-normal">Stay in a hotel for 3 nights on a
				double sharing basis.</li>
		</ul>
		<hr>
		<p class="font-weight-bold">Meal</p>
		<ul>
			<li class="font-weight-normal">Breakfast is included on all
				mornings of the trip.</li>
			<li class="font-weight-normal">Dinner on the Cruise.</li>
			<li class="font-weight-normal">BBQ dinner.</li>
		</ul>
		<p class="font-weight-bold">Tour price includes</p>
		<ul>
			<li class="font-weight-normal">Economy Class in flights</li>
			<li class="font-weight-normal">Hotel accommodation.</li>
			<li class="font-weight-normal">Daily breakfast, lunch & dinner.</li>
			<li class="font-weight-normal">Transport for airport</li>
		</ul>
		<p class="font-weight-bold">Tour price excludes</p>
		<ul>
			<li class="font-weight-normal">Meals, tips, laundry, telephone,
				fax, optional excursions, and other items of a personal nature, mini
				bar in the room, telephone bills, gratuities, etc.</li>
			<li class="font-weight-normal">Tips & Porterage for Local driver
				& guide</li>
			<li class="font-weight-normal">GST & TCS</li>
			<li class="font-weight-normal">Transport for airport</li>
		</ul>
		<br> <br>
		<div class="text-center">
			<form action="PackageBookingAction">
				<input type="hidden" name="packageName" value=<%=packageName%>>
				<input type="hidden" name="packagePrice" value=<%=packagePrice%>>
				<input type="hidden" name="numberOfDays" value=<%=days%>> <input
					type="hidden" name="startDate" value=<%=startDate%>> <input
					type="hidden" name="endDate" value=<%=endDate%>> <input
					type="hidden" name="hotelName" value=<%=hotelName%>> <input
					type="hidden" name="id" value=<%=userId%>> <label
					for="person"><Strong>Person</Strong></label><br> <input
					type="number" name="person" placeholder="number of passengers"
					min="1" required><br> <br>
				<button type="submit" class="btn btn-primary">Book</button>
				<br> <br>

			</form>
		</div>

	</main>
</body>
</html>