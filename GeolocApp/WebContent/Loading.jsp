<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if(session.getAttribute("twitter")==null){
		response.sendRedirect("login.jsp");
		return;	
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cargando</title>
<link rel="stylesheet" type="text/css" href="./assets/css/Cargando.css">
</head>
<body>

<img id="Cargando" src="./assets/img/cargando.gif" alt="cargando"/>
<div id="contenedor">
  <div id="barra">
    <div id="texto"></div>
  </div>
</div>

</body>

<script type="text/javascript">
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;
		localStorage.lat = lat;
		localStorage.lng = lng;
		setTimeout(function(){
			window.location.href = ("/GeolocApp/Index.jsp");
		},3000);
    })
}
</script>
</html>