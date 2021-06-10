<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<%
String role = (String) session.getAttribute("ROLE");
String LoginUser=(String)session.getAttribute("LOGINUSER");

%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
	
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>




<header>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <a class="navbar-brand" href="index.jsp">Tourist management app</a>
  <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
      aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavId">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <%if(role!=null && role.equalsIgnoreCase("admin")) {%>
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Package</a>
        <div class="dropdown-menu" aria-labelledby="dropdownId">
          <a class="dropdown-item" href="AddPackage.jsp">Add Packages</a>
          <a class="dropdown-item" href="ListOfPackages.jsp">List Packages</a>
        </div>
       </li>
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Flights</a>
        <div class="dropdown-menu" aria-labelledby="dropdownId">
          <a class="dropdown-item" href="AddFlight.jsp">Add Flights</a>
          <a class="dropdown-item" href="ListOfFlight.jsp">List Flights</a>
        </div>
       </li>
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Images</a>
        <div class="dropdown-menu" aria-labelledby="dropdownId">
          <a class="dropdown-item" href="AddImages.jsp">Add Package Images</a>
          <a class="dropdown-item" href="AddHotelImage.jsp">Add Hotel Images</a>
        </div>
       </li>
        <li>
       <a class="nav-link" href="ListOfEnquiry.jsp">List of enquiry</a>
      </li>
      <li>
       <a class="nav-link" href="ListOfBookings.jsp">List of bookings</a>
      </li>
       <li>
       <a class="nav-link" href="ListOfCancelledBooking.jsp">Cancelled booking</a>
      </li>
        <li>
       <a class="nav-link" href="SalesReport.jsp">Sales Report</a>
      </li>
      <%} %>
      <%if(LoginUser!=null && LoginUser.equalsIgnoreCase("user")){ %>
      <li>
       <a class="nav-link" href="ListOfPackages.jsp">List Packages</a>
      </li>
       <li>
       <a class="nav-link" href="CustomSearch.jsp">Custom Search</a>
      </li>
        <li>
       <a class="nav-link" href="UserBookingDetail.jsp">My Bookings</a>
      </li>
        <li>
       <a class="nav-link" href="ChangePassword.jsp">Change Password</a>
      </li>
      <%} %>
    </ul>
    <%if(role==null && LoginUser==null){ %>
     <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="UserLogin.jsp">UserLogin</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="AdminLogin.jsp">AdminLogin</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Registration.jsp">Register</a>
      </li>
      </ul>
      <%} if(role!=null){%>
     <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
      <li class="nav-item active">
      <a class="nav-link" href="AdminLogOutAction">Log out</a>
      </li>
      </ul>
      <%} %>
      <% if(LoginUser!=null){ %>
       <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
      <li class="nav-item active">
      <a class="nav-link" href="UserLogOutAction">Log out</a>
      </li>
      </ul>
      <%} %>
   
  </div>
</nav>
</header>
