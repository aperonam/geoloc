<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html;">
		<title>Geoloc - Home</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" type="text/css" href="./assets/css/main.css">
		<script src="./assets/js/request.js"></script>
	</head>
	<body>
 		<%@ include file = "NavBar.jsp" %>
 		<div id="map"></div>
 		<%@ include file = "Post.jsp" %>
 		<script>
 			function initMap() {
 				var mapProp = {
 					center: new google.maps.LatLng(-33.8688, 151.2195),
 					zoom: 15
 				};
 				
 				var map = new google.maps.Map(document.getElementById("map"), mapProp);
 				
 				// User location
 				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(function(position) {
						var pos = {
							lat: position.coords.latitude,
							lng: position.coords.longitude
						};
						map.setCenter(pos);
						map.setZoom(15);
					})
				}
 				
 				// Set markers
 				<c:forEach items="${thoughts}" var="thought">
					// Create info window content   
					var infoWindowContent = '<div class="info_content">' +
							'<h3>${thought.text}</h3>' +
							'<p>${thought.tag}</p>' +
						'</div>';
					
					// Initialise the inforWindow
					var infoWindow = new google.maps.InfoWindow({
						content: infoWindowContent
					});

 					var latitude = ${thought.latitude};
 					var longitude = ${thought.longitude};
 					var marker = new google.maps.Marker({
 						position: new google.maps.LatLng(latitude, longitude),
 						icon:'assets/img/map-marker.png',
 						title: '${thought.text}'
 					});
 					marker.setMap(map);
 					
 					// Add click listener
 					marker.addListener('click', function() {
						infowindow.open(map, marker);
					});
 				</c:forEach>
 			}
 		</script>
	    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbYLfQmvKGhhqz1nNee2CtW_Xv87dKHn4&callback=initMap"></script>
	</body>
</html>