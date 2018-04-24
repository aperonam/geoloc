<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import = "es.upm.dit.geoloc.dao.model.Thought"%>
<%@page import = "es.upm.dit.geoloc.dao.ThoughtDAOImplementation"%>
<%@page import = "java.util.List" %>
<%@page import = "java.util.ArrayList" %>
<%@page import = "twitter4j.Twitter" %>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if(session.getAttribute("twitter")==null){
		response.sendRedirect("login.jsp");
		return;	
	}
%>

<%
List<Thought> ArrayThought = new ArrayList<Thought>();
ThoughtDAOImplementation thought = new ThoughtDAOImplementation();
ArrayThought = (List<Thought>) session.getAttribute("lista");

Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

int longitudPensamientos = ArrayThought.size();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbYLfQmvKGhhqz1nNee2CtW_Xv87dKHn4&libraries=places&callback=initMap"async defer></script>
    <script>
	
      var latitud=0;
      var longitud = 0;
      var identificador = 0;
      function initMap() {
    	  
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: parseFloat(localStorage.lat), lng: parseFloat(localStorage.lng)},
          zoom: 15
        });
        var input = /** @type {!HTMLInputElement} */(
            document.getElementById('autocomplete'));

        var types = document.getElementById('type-selector');
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);

        var autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.bindTo('bounds', map);
        
        var logo ='./assets/img/marker50logo.png';
        
        <%for(int i = 0; i<longitudPensamientos; i++){%>	
        
        <%  double latitud =  ArrayThought.get(i).getLat();
        	double longitud =  ArrayThought.get(i).getLng();
        	System.out.println("latitud: "+latitud+"longitud: "+longitud);
        	String mensaje = ArrayThought.get(i).getPensamiento();
        	System.out.println(mensaje);
        	String newMensaje = mensaje.replaceAll("\n","</br>");
        	System.out.println(newMensaje);
        	int likes = ArrayThought.get(i).getLikes();
        	int id = ArrayThought.get(i).getId();
        %>
        var lat = "<%=latitud%>";
        var lng = "<%=longitud%>";
        var index = "<%=id%>";
        var content = '<div id="iw-container">' +
        '<div class="iw-title"><h1>Pensamiento #<%=id%></h1></div>' +
        '<div class="iw-content">' +'<p><%=newMensaje%></p>'+
        '<div id="like"> <button id="btnLikes" onClick="changeImage(<%=id%>)"><img id="imgLikes<%=id%>" src="./assets/img/likes.png" alt="Likes" style="width: 35px;height: 33px" /></button><%=likes%></h1></div>'+
        '</div>' +
      '</div>';
        var likes = "<%=likes%>"
        
            <%if(likes <10){%>
    		logo = './assets/img/marker.png';
    	<%}else if(likes >=10 && likes<100 ){%>
   			logo = './assets/img/Marker10logo.png';
   		<%}else if (likes >=100 ){%>
    		logo = './assets/img/marker50logo.png';
    	<%} else {%>
    		logo = './assets/img/marker.png';
    	<%}%>
        
        console.log("id = "+index);
        var infowindow = new google.maps.InfoWindow({
            maxWidth: 310
          });
        

        
        var marker = new google.maps.Marker({
            position: {lat: parseFloat(lat), lng:parseFloat(lng)},
            map: map,
            icon: logo,
            customInfo: index,
            <%if(twitter.getId()==ArrayThought.get(i).getUserId()){%>
            draggable: true
 			<%} else {%>
 			draggable : false
 			<%}%>
          });
		
        infowindow.setContent(content);
        infowindow.open(map,marker);
        
        
        google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){ 
                return function() {
                   infowindow.setContent(content);
                   infowindow.open(map,marker);
                };
            })(marker,content,infowindow)); 
        
        function printMarkerLocation(){
            console.log('Lat: ' + marker.position.lat() + ' Lng:' + marker.position.lng() );
       		latitud = marker.position.lat();
       		longitud = marker.position.lng();
       		identificador = this.customInfo;
       		console.log(this.customInfo);
        
        }
        marker.addListener('position_changed', printMarkerLocation);
		
        infowindow.close();        
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
<link rel="stylesheet" type="text/css" href="./assets/css/Buttons.css">
<link rel="stylesheet" type="text/css" href="./assets/css/index.css">
<link rel="stylesheet" type="text/css" href="./assets/css/login.css">
<link rel="stylesheet" type="text/css" href="./assets/css/box2.css">
<link rel="stylesheet" type="text/css" href="./assets/css/tag2.css">
<link rel="stylesheet" type="text/css" href="./assets/css/efectoBoton.css">
<link rel="stylesheet" type="text/css" href="./assets/css/InfoWindow.css">
<title>Tag</title>
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
 <div id="simpleModal" class="modal">
    <div class="modal-content">
      <div class="modal-header">
        <span class="closeBtn">&times;</span>
      </div>
      <div class="modal-body">
      	<main>
 			<p>Introduce el tag por el que quieres filtrar.</p>
 			<h1 id="h1Tag"><span id="Span2">Tags</span> Filtro</h1>
		</main>
		<form method="post" action="filtrar">
 		<input id="tag-input" type="text" name="tag"/>
 	 	<button id="filtrar" type="submit"><img id="imgFiltrar" src="./assets/img/filtro.png" alt="Tag" /></button>
 	 	</form>
      </div>
    </div>
  </div>
    <div id="map">
    </div>
    <input type="checkbox" class="checkbox" id="check">
    <label class="menu" for="check">|||</label>
    <div class=left-panel>
    	<div id="titulo">
    	<h1 id="h1Lista">Lista de pensamientos</h1>
    		<div id="lista">
    			<ul>
    				<%for(int i= 0; i<longitudPensamientos; i++){%>
    				<li><div id="ContenidoPen">
    					<h3>Pensamiento #<%=ArrayThought.get(i).getId() %>
    					
    					<%if(ArrayThought.get(i).getLikes() <10){%>
    					<img src='./assets/img/marker.png' alt="likes"/>		
    					<%}else if(ArrayThought.get(i).getLikes() >=10 && ArrayThought.get(i).getLikes()<100 ){%>
   						<img src='./assets/img/Marker10logo.png' alt="likes"/>
   						<%}else if (ArrayThought.get(i).getLikes() >=100 ){%>
    					<img src='./assets/img/marker50logo.png' alt="likes"/>	
    					<%} else {%>
    					<img src='./assets/img/marker.png' alt="likes"/>
    					<%}%>
    				  					
    					 </h3>
    					<p><%=ArrayThought.get(i).getPensamiento() %></p>
    					<div id="like">
    						<button id="btnLikes" onClick="changeImage(<%=ArrayThought.get(i).getId()%>)">
    							<img id="imgLikes<%=ArrayThought.get(i).getId()%>2" src="./assets/img/likes.png" alt="Likes" style="width: 35px;height: 33px" />
    						</button>
    						<%=ArrayThought.get(i).getLikes()%>
    					</div>
    					
    			</div></li>
    				<%}%>
    			</ul>
    	</div>
    	</div>
    </div>
    <img id="localiza" src="./assets/img/menu.png" alt="localiza"/>
    <button id="actualizar" onClick="sendAjax()">    
    <div class="container">
 		 <div class="div-img" >
   		 	<img class="img" src="./assets/img/actualizar.png" alt="Actualizar">
    		<div class="text"><p>Actualizar<p></div>
 		 </div>
	</div></button>
    <a id="populares" href="Populares.jsp">   
    <div class="container2">
 		 <div class="div-img" >
   		 	<img class="img" src="./assets/img/populares.png" alt="Populares">
    		<div class="text"><p>Populares<p></div>
 		 </div>
	</div></a>
        <a id="populares" href="MisMarkers.jsp">   
    <div class="container2">
 		 <div class="div-img" >
   		 	<img class="img" src="./assets/img/Mimarker.png" alt="Mis Marcadores">
    		<div class="text"><p>Mis Pensamientos<p></div>
 		 </div>
	</div></a>
    <button id="Tag">
       <div class="container">
 		 <div class="div-img" >
   		 	<img class="img" src="./assets/img/tag.png" alt="Tag">
    		<div class="text"><p>Filtrar Tag<p></div>
 		 </div>
	</div>
    </button>
		<input id="autocomplete" type="text"
      placeholder="Introduce una localización">
</div>

    <script type="text/javascript">
    
    var Json = new Object();
    
    function sendAjax() {

    	
    	function Redirect() {
			window.location.href = ("/GeolocApp/Index.jsp");
    		}
    	
    	Json.lat = latitud;
    	Json.lng = 	longitud;
    	Json.id = identificador;

		 var JsonF = JSON.stringify(Json);
		 console.log(JsonF);
		 
		$.ajax({
			url: "update",
			type: 'POST',
			dataType: "text",
			data :JsonF,
			
			success: function (data) {
				console.log("success");
				window.location.href = ("/GeolocApp/Index.jsp");
			},
		      error:function(data,status,er) {
		            console.log("error");
		        }		
		});
    	
    }
    </script> 
    <script>
//crea el modal
var modal = document.getElementById('simpleModal');
// crea el boton de abrir
var modalBtn = document.getElementById('Tag');
// crea el boton de cerrar
var closeBtn = document.getElementsByClassName('closeBtn')[0];

// Listen para abrir
modalBtn.addEventListener('click', openModal);
// Listen para cerrar
closeBtn.addEventListener('click', closeModal);
// Listen para click fuera
window.addEventListener('click', outsideClick);

// Abre el modal
function openModal(){
  modal.style.display = 'block';
}

// Cierra el modal
function closeModal(){
  modal.style.display = 'none';
}

// 	Sale del modal si pulsas fuera de él
function outsideClick(e){
  if(e.target == modal){
    modal.style.display = 'none';
  }
}
</script>
 
<script>
 var change = 0;
function changeImage(Likes) {
	var img = 'imgLikes'+Likes;
	var img2 = 'imgLikes'+Likes+2;
	logo = './assets/img/likes2.png';
	logo2 = './assets/img/likes.png';
    var image = document.getElementById(img);
    var image2 = document.getElementById(img2);
	if(change == 0){
    image.src= logo; 
    image2.src = logo;
    change = 1;
	} else {
	image.src = logo2;	
	image2.src = logo2;
	change = 0;
	}
}
 
</script>  
  </body>
</html>