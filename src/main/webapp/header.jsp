<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<%
String role = (String) session.getAttribute("ROLE");
String LoginUser=(String)session.getAttribute("LOGINUSER");

%>

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
      <li class="nav-item">
        <a class="nav-link" href="AddPackage.jsp">Add Packages</a>
      </li>
       <li>
       <a class="nav-link" href="ListOfPackages.jsp">List Packages</a>
      </li>
      <%} %>
      <%if(LoginUser!=null && LoginUser.equalsIgnoreCase("user")){ %>
      <li>
       <a class="nav-link" href="ListOfPackages.jsp">List Packages</a>
      </li>
       <li>
       <a class="nav-link" href="SearchPackageByName.jsp">Search by country</a>
      </li>
       <li>
       <a class="nav-link" href="SearchPackageByPrice.jsp">Search by price</a>
       
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Filters</a>
        <div class="dropdown-menu" aria-labelledby="dropdownId">
          <a class="dropdown-item" href="">Action 1</a>
          <a class="dropdown-item" href="#">Action 2</a>
        </div>
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