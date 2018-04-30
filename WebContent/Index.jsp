<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
		<div id="map"></div>
		<%@ include file = "NavBar.jsp" %>
		<script>
			function initMap() {
				var mapProp = {
					center: new google.maps.LatLng(-33.8688, 151.2195),
					zoom: 15,
					// Map style
					styles: [{
						"featureType": "landscape.man_made",
						"elementType": "geometry.fill",
						"stylers": [{
							"lightness": 5
						}]
					}, {
						"featureType": "poi",
						"elementType": "labels",
						"stylers": [{
							"visibility": "off"
						}]
					}, {
						"featureType": "transit",
						"elementType": "labels",
						"stylers": [{
							"visibility": "off"
						}]
					}]
				};
				
				map = new google.maps.Map(document.getElementById("map"), mapProp);
				
				// Center map in user location
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(function(position) {
						var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
						map.setCenter(pos);
						map.setZoom(15);
					})
				}
				
				// Toughts
				var thoughts = [
				<c:forEach items="${thoughts}" var="thought">
					{
						id: ${thought.id},
						text: "${thought.text}",
						tag: "${thought.tag}",
						latitude: ${thought.latitude},
						longitude: ${thought.longitude}
					},
				</c:forEach>
				];
				
				window.thoughts = thoughts;
				
				// Set markers
				markers = [];
				thoughts.forEach((thought) => {
					// Create info window content   
					let infoWindowContent = '<div class="info_content">' +
							'<h3>' + thought.text + '</h3>' +
							'<p>' + thought.tag + '</p>' +
						'</div>';
					
					// Initialise the inforWindow
					let infoWindow = new google.maps.InfoWindow({
						content: infoWindowContent
					});
					
 					let marker = new google.maps.Marker({
 						position: new google.maps.LatLng(thought.latitude, thought.longitude),
 						icon:'assets/img/map-marker.png',
 						title: thought.text
 					});
 					marker.setMap(map);
 					
 					// Add click listener
 					marker.addListener('click', function() {
						infowindow.open(map, marker);
					});
 					
 					markers.push(marker);
				});
			}
			
 		</script>
 		<script type="text/javascript">
			function setMapOnAll(map) {
				for (var i = 0; i < markers.length; i++) {
					markers[i].setMap(map);
				}
			}
			
 			function reloadThoughts() {
 				
 				var thoughts = window.thoughts;
 				
 				var thoughtsHTML = "";
 				
 				markers = [];
 				
 				thoughts.forEach((thought) => {
 					thoughtsHTML += `
	 					<div class="thought">
							<p class="thought-text">` + thought.text + `</p>
							<p class="thought-tag">` + thought.tag + `</p>
						</div>
 					`;
 					
 					// Clear markers
 					setMapOnAll(null);
 					
					// Create info window content   
					let infoWindowContent = '<div class="info_content">' +
							'<h3>' + thought.text + '</h3>' +
							'<p>' + thought.tag + '</p>' +
						'</div>';
					
					// Initialise the inforWindow
					let infoWindow = new google.maps.InfoWindow({
						content: infoWindowContent
					});
					
 					let marker = new google.maps.Marker({
 						position: new google.maps.LatLng(thought.latitude, thought.longitude),
 						icon:'assets/img/map-marker.png',
 						title: thought.text
 					});
 					marker.setMap(map);
 					
 					// Add click listener
 					marker.addListener('click', function() {
						infowindow.open(map, marker);
					});
 					markers.push(marker);
				});
 				
 				document.getElementById("thoughts-container").innerHTML = thoughtsHTML;
 			}
 		</script>
	    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbYLfQmvKGhhqz1nNee2CtW_Xv87dKHn4&callback=initMap"></script>
	</body>
</html>