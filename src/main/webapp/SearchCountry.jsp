<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang ="en">
<head>
<meta charset="ISO-8859-1">
<title>Search country</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<h3>Search package</h3>
	<br>
	<br>
		<form action="SearchCountryAction" onsubmit="getAllPackages(event)">
			<label for="countryName"><strong>Country Name</strong></label> <select
				name="countryName" id="countryName">
				<option value=""></option>
				<option value="Dubai">Dubai</option>
				<option value="Maldives">Maldives</option>
				<option value="Germany">Germany</option>
				<option value="Singapore">Singapore</option>
				<option value="Malaysia">Malaysia</option>
				</select>
				<br>
				<br>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
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


				</tr>
			</thead>
			<tbody id="packageList">

			</tbody>
		</table>
		<form action="CancelAllPackagesAction">
		<input type="hidden" name="countryName" id="cancelCountry">
		<input type="hidden" name="status" value="Cancelled">
		<label for="cancelReason">Reason</label>
		<input type="text" name="cancelReason" required>
		<button type="submit" class="btn btn-danger">Cancel All</button>
		</form>
	</main>
	<script>
		function getAllPackages(event){
			event.preventDefault();
			let countryName = document.getElementById("countryName").value ;
			let cancelCountry = document.getElementById("cancelCountry");
			cancelCountry.value = countryName;
			console.log(countryName);
	
			let url = "SearchCountryAction?countryName=" +countryName;
			fetch(url).then(res=> res.json()).then(res=>{
			let packages = res;
			let content = "";
			let i  =0;
			for(let packageDetail of packages){
				let date = new Date(packageDetail.startDate)
				let formattedDate = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear()
				let endDate = new Date(packageDetail.endDate)
				let formattedEndDate = endDate.getDate() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getFullYear()
				content += "<tr><td>" + ++i + 
				"</td><td>"+packageDetail.packageName+
				"</td><td>Rs."+ packageDetail.packagePrice +
				"</td><td>"+packageDetail.numberOfDays+
				"</td><td>"+formattedDate+ 
				"</td><td>"+formattedEndDate+
				"</td><td>"+packageDetail.hotelName+"</td></tr>"
			}
			console.log(content);
			document.querySelector("#packageList").innerHTML= content; 	
		})
		}
</script>
</body>
</html>