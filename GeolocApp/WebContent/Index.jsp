<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import = "es.upm.dit.geoloc.dao.model.Thought"%>
<%@page import = "es.upm.dit.geoloc.dao.ThoughtDAOImplementation"%>
<%@page import = "java.util.List" %>
<%@page import = "java.util.ArrayList" %>

<%
List<Thought> ArrayThought = new ArrayList<Thought>();
ThoughtDAOImplementation thought = new ThoughtDAOImplementation();
ArrayThought = thought.getAll();

int longitudPensamientos = ArrayThought.size();
%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if(session.getAttribute("twitter")==null)
		response.sendRedirect("login.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./assets/css/Buttons.css">
<link rel="stylesheet" type="text/css" href="./assets/css/index.css">
<link rel="stylesheet" type="text/css" href="./assets/css/login.css">
<title>Insert title here</title>
</head>
<body>
<div class="cabecera">
<div id="divlogo"><img id="logoCab" src="./assets/img/LOGO.png" alt="logo"/></div>
<div id="divbusca"><a href="Index.jsp"/><img id="busca" src="./assets/img/buscaLogo.png" alt="logoBusca"/></a></div>
<div id="divpublica"><a href="Post.jsp"/><img id="publica" src="./assets/img/publica.png" alt="logoPublica"/></a></div>
<div id="divchatea"><img id="chatea" src="./assets/img/chatea.png" alt="logoChatea"/></div>
<div id="divlogin"><section class="row1">  
 <div class="seccion">
  <div class="boton-linea-ext">
    <span class="linea1"></span>
    <span class="linea2"></span>
    <span class="linea3"></span>
    <span class="linea4"></span>
    <a id="modalBtn" class="button" href="logout">CERRAR SESIÓN</a>
  </div>
 </div>
</section></div>
</div>
 <div id="cuerpoIndex">
    <div id="map">
    </div>
    <img id="localiza" src="./assets/img/localiza.png" alt="localiza"/>
	<input id="autocomplete" type="text"
      placeholder="Introduce una localización">
</div>
    <script>

      function initMap() {
    	  
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -33.8688, lng: 151.2195},
          zoom: 15
        });
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
        var input = /** @type {!HTMLInputElement} */(
            document.getElementById('autocomplete'));

        var types = document.getElementById('type-selector');
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);

        var autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.bindTo('bounds', map);
        
        var logo100 ='./assets/img/marker50logo.png';
        
        <%for(int i = 0; i<longitudPensamientos; i++){%>	
        
        <%  float latitud = (float) ArrayThought.get(i).getLat();
        	float longitud = (float) ArrayThought.get(i).getLng();
        	System.out.println("latitud: "+latitud+"longitud: "+longitud);
        	String mensaje = ArrayThought.get(i).getPensamiento();
        	System.out.println(mensaje);
        	String newMensaje = mensaje.replaceAll("\n","</br>");
        	System.out.println(newMensaje);
        %>
        var lat = "<%=latitud%>";
        var lng = "<%=longitud%>";
        var index = "<%=i%>";
        var content = "<%=newMensaje%>";
        
        var infowindow = new google.maps.InfoWindow();
        
        var marker = new google.maps.Marker({
            position: {lat: parseFloat(lat), lng:parseFloat(lng)},
            map: map,
            icon: logo100,
            zIndex: index,
            draggable: true
          });
        

        google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){ 
                return function() {
                   infowindow.setContent(content);
                   infowindow.open(map,marker);
                };
            })(marker,content,infowindow)); 
        
        function printMarkerLocation(){
            console.log('Lat: ' + marker.position.lat() + ' Lng:' + marker.position.lng() );}
        
        marker.addListener('position_changed', printMarkerLocation);
        
        <%}%>

        autocomplete.addListener('place_changed', function() {
          var place = autocomplete.getPlace();
          if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place.name + "'");
            return;
          }

          // If the place has a geometry, then present it on a map.
          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17); 
          }
        });

        // Sets a listener on a radio button to change the filter type on Places
        // Autocomplete.
        function setupClickListener(id, types) {
          var radioButton = document.getElementById(id);
          radioButton.addEventListener('click', function() {
            autocomplete.setTypes(types);
          });
        }
      }
    </script>
     <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbYLfQmvKGhhqz1nNee2CtW_Xv87dKHn4&libraries=places&callback=initMap"
        async defer></script>
  </body>
</html>