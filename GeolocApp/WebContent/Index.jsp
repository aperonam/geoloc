<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if(session.getAttribute("twitter")==null)
		response.sendRedirect("login.jsp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
       #map {
        height: 400px;
        width: 100%;
       }
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="logout"/>Cerrar sesion</a>
 <h3>My Google Maps Demo</h3>
    <div id="map"></div>
      <div id="locationField">
      <input id="autocomplete" placeholder="Enter your address"
             onFocus="geolocate()" type="text"></input>
    </div>
    <script>
 // This example displays a marker at the center of Australia.
 // When the user clicks the marker, an info window opens.

 function initMap() {
	 
   var bernabeu = {lat: 40.530100, lng: -3.6882900};
   var bernabeu2 = {lat: 40.4030100, lng: -3.6682900};
   var bernabeu3 = {lat: 40.4930100, lng: -3.6882900};
   var bernabeu4 = {lat: 40.4830100, lng: -3.6882900};
   
   var map = new google.maps.Map(document.getElementById('map'), {
     zoom: 12,
     center: bernabeu
   });
   
   if (navigator.geolocation) {
       navigator.geolocation.getCurrentPosition(function(position) {
         var pos = {
           lat: position.coords.latitude,
           lng: position.coords.longitude
         };
         map.setCenter(pos);
       }, function() {
         handleLocationError(true, map.getCenter());
       });
     } else {
       // Browser doesn't support Geolocation
       handleLocationError(false, map.getCenter());
     }
	
   var logo100 ='../assets/img/marker50logo.png';
   var logo50 ='../assets/img/Marker10logo.png';
   var logo10 = '../assets/img/marker.png';
   var logodislike = '../assets/img/MarkerDislike64.png';
   
   
   var contentString = '<div id="content">'+
       '<div id="siteNotice">'+
       '</div>'+
       '<h1 id="firstHeading" class="firstHeading">Uluru</h1>'+
       '<div id="bodyContent">'+
       '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
       'sandstone rock formation in the southern part of the '+
       'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
       'south west of the nearest large town, Alice Springs; 450&#160;km '+
       '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
       'features of the Uluru - Kata Tjuta National Park. Uluru is '+
       'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
       'Aboriginal people of the area. It has many springs, waterholes, '+
       'rock caves and ancient paintings. Uluru is listed as a World '+
       'Heritage Site.</p>'+'<button>Like</button>'
       '<p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
       'https://en.wikipedia.org/w/index.php?title=Uluru</a> '+
       '(last visited June 22, 2009).</p>'+
       '</div>'+
       '</div>';

   var infowindow = new google.maps.InfoWindow({
     content: contentString
   });

   var marker = new google.maps.Marker({
     position: bernabeu,
     map: map,
     title: 'Uluru (Ayers Rock)',
     icon: logodislike
   });
   
   var marker2 = new google.maps.Marker({
	     position: bernabeu2,
	     map: map,
	     title: 'Uluru (Ayers Rock)',
	     icon: logo50
	   });
   var marker3 = new google.maps.Marker({
	     position: bernabeu3,
	     map: map,
	     title: 'Uluru (Ayers Rock)',
	     icon: logo100
	   });
   var marker4 = new google.maps.Marker({
	     position: bernabeu4,
	     map: map,
	     title: 'Uluru (Ayers Rock)',
	     icon: logo10
	   });
   
   Marker.addListener(Marker, 'click', function() {
     infowindow.open(map, Marker);
   });
   google.maps.event.addListener(map, 'click', function() {
       infowindow.close();
       marker.open = false;
   });
   
   function initialize() {
	   var input = document.getElementById('autocomplete');
	   new google.maps.places.Autocomplete(input);
	 }
 }
 </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbYLfQmvKGhhqz1nNee2CtW_Xv87dKHn4&callback=initMap">
    </script>
  </body>
</html>