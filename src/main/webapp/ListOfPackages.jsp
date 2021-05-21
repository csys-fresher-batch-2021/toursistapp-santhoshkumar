<%@page import="in.santhosh.Dao.PackageDao"%>
<%@page import="in.santhosh.model.TourPackageDetail"%>
<%@page import="java.util.List"%>
<%@page import="in.santhosh.service.Packages"%>
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
		<%
		String infoMessage = request.getParameter("infoMessage");
		if (infoMessage != null) {
			out.println("<font color='green'>" + infoMessage + "</font>");
		}
		%>
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
					<th scope="col"></th>

				</tr>
				<%
				PackageDao dao=new PackageDao();
				List<TourPackageDetail> packageDetails =Packages.displayPackages();
				int i = 0;
				for (TourPackageDetail packages : packageDetails) {
					i++;
				%>
				<tr>
					<td><%=i%></td>
					<td><%=packages.getPackageName()%></td>
					<td><%=packages.getPackagePrice()%></td>
					<td><%=packages.getNumberOfDays()%></td>
					<td><%=packages.getStartDate()%></td>
					<td><%=packages.getEndDate()%></td>
					<td><a
						href="DeletePackageAction?packageName=<%=packages.getPackageName()%>
						&packagePrice=<%=packages.getPackagePrice()%>
						&numberOfDays=<%=packages.getNumberOfDays() %>
						&startDate=<%=packages.getStartDate()%>
						&endDate=<%=packages.getEndDate()%>"
						class="btn btn-danger">Delete</a>
					</td>
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
