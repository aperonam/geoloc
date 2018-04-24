<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
List<String> ArrayThought = new ArrayList<String>();

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./assets/css/Buttons.css">
<link rel="stylesheet" type="text/css" href="./assets/css/login.css">
<link rel="stylesheet" type="text/css" href="./assets/css/post.css">
<link rel="stylesheet" type="text/css" href="./assets/css/tag.css">
<link rel="stylesheet" type="text/css" href="./assets/css/texto.css">
<link rel="stylesheet" type="text/css" href="./assets/css/enviar.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publica</title>
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
<div id="cuerpoPost">

<div id="tituloPublicarDiv">
	<img id="tituloPublicar" src="./assets/img/tituloPublicar.gif" alt="titulo"/>
</div>
<article class="textarea" id="model2">
  <h3></h3>
  <div class="text-box">
    <textarea id="text-area" maxlength="240"></textarea>
    <div class="calc">caracteres restantes       <span id="charLeft"></span></div>
  </div>
</article>
<main>
  <p>Separar cada Tag con una coma. Max 5 Tags.</p>
  <div class="tags-input"></div>
  <h1><span id="Span2">Tags</span> Geoloc</h1>
</main>
<button onClick="sendAjax()">
  <p>publicar</p>
  <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 512 512" enable-background="new 0 0 512 512" xml:space="preserve">
    <path id="paper-plane-icon" d="M462,54.955L355.371,437.187l-135.92-128.842L353.388,167l-179.53,124.074L50,260.973L462,54.955z
M202.992,332.528v124.517l58.738-67.927L202.992,332.528z"></path> 
  </svg>
</button>
</div>

<script type="text/javascript">
var Json = new Object();
var tagsJson =[];
var index=0;
var x = document.getElementById("text-area");
var latLng=0;

var TagsInput = function(element) { 
	  var self = this;
	  var initChar = "\u200B";
	  var initCharPattern = new RegExp(initChar, 'g');
	  
	  var insert = function(element) {
	     if(self.textNode) self.element.insertBefore(element, self.textNode);
	     else self.element.appendChild(element);
	  };
	  
	  var updateCursor = function() {
	    self.cursor = self.blank;
	  };
	  
	  var keydown = function(event) {
	    if(event.keyCode == 188) {
	      event.preventDefault();
	      setTimeout(function() {
	        var text = self.text;
	        if(text) {
	          self.text = initChar;
	          self.add(text);
	        }
	      }, 1);
	    }
	    else if(event.keyCode == 8) {
	      if(self.text.replace(initCharPattern, '') == '') {
	        self.text = initChar+initChar;
	        if(self.selected) {
	          self.element.removeChild(self.selected);
	        }
	        else {
	          var tags = self.tags;
	          var keys = Object.keys(tags)
	          if(keys.length > 0) {
	            var tag = tags[keys[keys.length-1]];
	            tag.setAttribute('data-selected', '');
	          }
	        }
	      }
	    }
	    
	    if(event.keyCode !== 8) {
	      if(self.selected) self.selected.removeAttribute('data-selected');
	    }
	    setTimeout(function() {
	      updateCursor();
	    }, 1);
	  };
	  
	  var focus = function() {
	    updateCursor();
	  };
	  
	  Object.defineProperties(this, {
	    element: {
	      get: function() {
	        return element;
	      },
	      set: function(v) {
	        if(typeof v == 'string') v = document.querySelector(v);
	        element = v instanceof Node ? v : document.createElement('div');
	        if(!element.className.match(/\btags-input\b/)) element.className += ' tags-input';
	        if(element.getAttribute('contenteditable') != 'true') element.setAttribute('contenteditable', 'true');
	        
	        element.removeEventListener('keydown', keydown);
	        element.addEventListener('keydown', keydown);
	        
	        element.removeEventListener('focus', focus);
	        element.addEventListener('focus', focus);
	        this.text = initChar;
	      }
	    },
	    tags: {
	      get: function() {
	        var element;
	        var elements = this.element.querySelectorAll('span');
	        var tags = {};
	        for(var i = 0; i < elements.length; i++) {
	          element = elements[i]
	          tags[element.innerText] = element;
	        }
	        
	        return tags;
	      }
	    },
	    lastChild: {
	      get: function() {
	        return this.element.lastChild;
	      }
	    },
	    textNode: {
	      get: function() {
	        return this.element.lastChild instanceof Text ? this.element.lastChild : null;
	      }
	    },
	    text: {
	      get: function() {
	        return this.textNode ? this.textNode.data : null;
	      },
	      set: function(v) {
	        if(!this.textNode) this.element.appendChild(document.createTextNode(','));
	        this.textNode.data = v;
	      },
	    },
	    cursor: {
	      get: function() {
	        return this.element.getAttribute('data-cursor') !== null;
	      },
	      set: function(v) {
	        if(v) this.element.setAttribute('data-cursor', '');
	        else this.element.removeAttribute('data-cursor');
	      }
	    },
	    focused: {
	      get: function() {
	        return document.activeElement == this.element;
	      }
	    },
	    blank: {
	      get: function() {
	        return this.text.replace(initCharPattern, '') == '';
	      }
	    },
	    selected: {
	      get: function() {
	        return this.element.querySelector('span[data-selected]');
	      }
	    }
	  });
	  
	  this.add = function(tag) {
	    tag = tag.replace(initCharPattern, '');
	    tag = tag.replace(/^\s+/, '').replace(/\s+$/, '');
	    tag = tag[0].toUpperCase()+tag.toLowerCase().slice(1);
	    if(tag != '' && this.tags[tag] === undefined) {
	      tagsJson[index]=tag;
	      index++;
	      var element = document.createElement('span');
	      element.appendChild(document.createTextNode(tag));
	      element.setAttribute('contenteditable', 'false');
	      
	      insert(element);
	    }
	  };
	  
	  this.remove = function(tag) {
	     var element = this.tags[tag];
	     if(element) this.element.removeChild(element);
	  };
	  
	  this.element = element;
	};
	
	var input = new TagsInput('.tags-input');
</script>
<script type="text/javascript" >
var h3 = document.getElementsByTagName("h3");
h3[0].innerHTML = "PENSAMIENTO";

var textArea = document.getElementById("text-area"),
    charLeft = document.getElementById("charLeft");

charLeft.textContent = 240;

textArea.onkeyup = function () {
    'use strict';
    charLeft.textContent = 240 - this.value.length;
    
    if (charLeft.textContent > 10) {
    
        charLeft.style.color = "green";

    } else if (charLeft.textContent > 0) {
        
        charLeft.style.color = "orange";
        
    } else {

        charLeft.style.color = "red";

    }
};

// clear placeholder onfocus

textArea.onfocus = function () {
    'use strict';
    
    if (this.placeholder === "Escribe tu pensamiento...") {
        
        this.placeholder = "";
        
    }
};

// write on placeholder onblur
textArea.onblur = function () {
    'use strict';
    
    if (this.placeholder === "") {
        
        this.placeholder = "Escribe tu pensamiento...";
        
    }
    
};
</script>
    <script type="text/javascript">
    
    function sendAjax() {

    	
    	function Redirect() {
			window.location.href = ("/GeolocApp/Index.jsp");
    		}
    	
    	Json.pensamiento = x.value;
    	Json.lat = localStorage.lat;
    	Json.lng = localStorage.lng;
    	Json.likes = 0;
		Json.tag1 = tagsJson[0];
		Json.tag2 = tagsJson[1];
		Json.tag3 = tagsJson[2];
		Json.tag4 = tagsJson[3];
		Json.tag5 = tagsJson[4];
		;
		console.log(Json);
		
		 if (typeof Json == 'object'){
			 console.log("Es ya un Json");
		 }else {
			 console.log("que es eso");
		 }
		 var JsonF = JSON.stringify(Json);
		 
		$.ajax({
			url: "publicar",
			type: 'POST',
			dataType: "text",
			data :JsonF,
			
			success: function (data) {
				console.log("success");
				setTimeout(Redirect, 1000);
			},
		      error:function(data,status,er) {
		            console.log("error");
		        }
			
		});
    	
    }
    </script>
     <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbYLfQmvKGhhqz1nNee2CtW_Xv87dKHn4"
        async defer></script>
        
     <script>
     $('button').click(function() {
    	  $(this).toggleClass('clicked');
    	  $('button p').text(function(i, text) {
    	    return text === "" ? "Send" : "";
    	  });
    	});
     </script>
</body>
</html>