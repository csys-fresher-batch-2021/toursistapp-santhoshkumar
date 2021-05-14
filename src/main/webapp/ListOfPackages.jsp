<%@page import="in.santhosh.model.TourPackageDetail"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.service.Packages"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>List of packages</h3>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>S.No</th>
					<th>Country Name</th>
					<th>Package Price</th>
					<th>Days</th>
					<th>Journey Start Date</th>
					<th>Journey End Date</th>

				</tr>
				<%
				List<TourPackageDetail> packageList = Packages.displayPackages();
				int i = 0;
				for (TourPackageDetail packages : packageList) {
					i++;
				%>
				<tr>
					<td><%=i%></td>
					<td><%=packages.getPackageName()%></td>
					<td><%=packages.getPackagePrice()%></td>
					<td><%=packages.getNumberOfDays()%></td>
					<td><%=packages.getStartDate()%></td>
					<td><%=packages.getEndDate()%></td>
				</tr>
				<%
				}
				%>
			</thead>
		</table>
		<a href="AddPackage.jsp">Add Package</a>



	</main>
</body>
</html>
