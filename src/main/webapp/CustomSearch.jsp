<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Custom Search</title>
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
	<br>
	<br>
		<form action="CustomSearchAction" onsubmit="getAllPackages(event)">
			<label for="countryName"><strong>Country Name</strong></label> <select
				name="countryName" id="countryName">
				<option value=""></option>
				<option value="Dubai">Dubai</option>
				<option value="Maldives">Maldives</option>
				<option value="Germany">Germany</option>
				<option value="Singapore">Singapore</option>
				<option value="Malaysia">Malaysia</option>
			</select> <label for="packagePrice"><strong>Package price</strong></label> <input
				type="number" id="packagePrice" name="packagePrice"
				placeholder="Enter the package price" min=10000> <label
				for="days"><strong>Days</strong></label> <input type="number"
				id="days" name="days" placeholder="Enter the number of days" min=3
				max=15> <br>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<br>
		<h3>List of packages</h3>
		<table class="table table-bordered">
			<caption>List all the packages with details</caption>
			<thead>
				<tr>
					<th scope="col">S.No</th>
					<th scope="col">Country Name</th>
					<th scope="col">Package Price</th>
					<th scope="col">Days</th>
					<th scope="col">Journey Start Date</th>
					<th scope="col">Journey End Date</th>
					<th scope="col">Hotel Name</th>
					<th scope="col">Book</th>


				</tr>
			</thead>
			<tbody id="packageList">

			</tbody>
		</table>
	</main>
	<script>
		function getAllPackages(event){
			event.preventDefault();
			let countryName = document.getElementById("countryName").value ;
			let packagePrice = document.getElementById("packagePrice").value ;
			let days = document.getElementById("days").value ;
			let url = "CustomSearchAction?packagePrice="+ packagePrice + "&countryName=" +countryName +"&days="+days;
			fetch(url).then(res=> res.json()).then(res=>{
			let packages = res;
			let content = "";
			let i  =0;
			for(let packageDetail of packages){
				content += "<tr><td>" + ++i + 
				"</td><td>"+packageDetail.packageName+
				"</td><td>"+ packageDetail.packagePrice +
				"</td><td>"+packageDetail.numberOfDays+
				"</td><td>"+packageDetail.startDate + 
				"</td><td>"+packageDetail.endDate+
				"</td><td>"+packageDetail.hotelName +
				"</td><td><a class=\"btn btn-primary\" href=\"BookingPackageAction?packageName="+packageDetail.packageName+
						"&packagePrice="+packageDetail.packagePrice+"&numberOfDays="+packageDetail.numberOfDays+
						"&startDate="+packageDetail.startDate
						+"&endDate="+packageDetail.endDate+
						"&hotelName="+packageDetail.hotelName+"\">Book</a></td></tr>";
			}
			console.log(content);
			document.querySelector("#packageList").innerHTML= content;
			
		})
	}
</script>
</body>
</html>