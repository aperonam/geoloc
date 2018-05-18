<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "es.upm.dit.geoloc.dao.model.Chat"%>
<%@page import = "es.upm.dit.geoloc.dao.ChatDAOImplementation"%>
<%@page import = "es.upm.dit.geoloc.dao.model.User"%>
<%@page import = "es.upm.dit.geoloc.dao.UserDAOImplementation"%>
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
Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
List<Chat> chat = new ArrayList<Chat>();
ChatDAOImplementation chats = new ChatDAOImplementation();
UserDAOImplementation user = new UserDAOImplementation();
chat = chats.getMisChats(twitter.getId());
User usuario = null;
int longitudChat = chat.size();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chat</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="https://use.typekit.net/hoy3lrg.js" type="text/javascript"></script>
<script type="text/javascript">
var UserId = <%=twitter.getId()%>
</script>
<script src="./assets/server.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="./assets/css/Chat.css">
<link rel="stylesheet" type="text/css" href="./assets/css/Buttons.css">
<link rel="stylesheet" type="text/css" href="./assets/css/box.css">
<link rel="stylesheet" type="text/css" href="./assets/css/login.css">
</head>
<body>
<div class="cabecera">
<div id="divlogo"><img id="logoCab" src="./assets/img/LOGO.png" alt="logo"/></div>
<div id="divbusca"><a href="Index.jsp"/><img id="busca" src="./assets/img/buscaLogo.png" alt="logoBusca"/></a></div>
<div id="divpublica"><a href="Post.jsp"/><img id="publica" src="./assets/img/publica.png" alt="logoPublica"/></a></div>
<div id="divchatea"><a href="Chat.jsp"/><img id="chatea" src="./assets/img/chatea.png" alt="logoChatea"/></div>
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
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>
<img id ="ChatImg" src='./assets/img/CHAT.png' alt="CHAT"/>
<div id="chatbox">
	<div id="friendslist">
    	<div id="topmenu">
    	<h1>Lista de conversaciones</h1>
        </div>
        
	    <div id="friends">
        
         <%for(int i = 0; i<longitudChat; i++){	
         
         long UserId1 = chat.get(i).getUserId1();
         long UserId2 = chat.get(i).getUserId2();
         int idChat = chat.get(i).getId();
         int id = 25;
         int status = chat.get(i).getStatus();
         
         if(twitter.getId() != UserId1){
        //	usuario = user.readUser(UserId1);
        //	id = usuario.getId(); 
         }
         %>

            <div class="friend" id=<%=idChat%> value=<%=status%> >
            	<img src="./assets/img/anonimo.png" />
                <p>
                	<strong>CHAT <%=idChat%> </strong>
                </p>
                 <%if (status == 1 && chat.get(i).getUserId1() != twitter.getId()){ %>
                 	<p id ="pendiente"><strong>PENDIENTE DE ACEPTAR</strong></p>
                <%}else if (status == 1 && chat.get(i).getUserId1() == twitter.getId()){ %>
                
                
                <form action="Status">
					<input type="hidden" name="status" value=<%=idChat%> />
					<button id ="aceptarbtn" type="submit"><img id="imgAc" src="./assets/img/aceptar.png" alt="Aceptar"></button>
				</form>
             	<form action="Delete">
             	<input type="hidden" name="status" value=<%=idChat%> />
                <button id= "rechazarbtn"><img id="imgRe" src="./assets/img/rechazar.png" alt="Rechazar"></button>
                </form>
                <%}else{ %>
                <div class="status away"></div>
                <%} %>
            </div>  
            
         <%} %>
          
       </div>         
        
    </div>	
    
    <div id="chatview" class="p1">    	
        <div id="profile">

            <div id="close">
                <div class="cy"></div>
                <div class="cx"></div>
            </div>
            
            <p>Miro Badev</p>
        </div>
        <div id="chat-messages">
            
            <div class="message">

                </div>
            </div>
    	
        <div id="sendmessage">
        	<input id="mensaje-chat" type="text" value="Envía un mensaje..."  onkeypress="pulsar(event)" />
            <button  id="send" onClick="sendAjax()"></button>
        </div>
    
    </div>        
</div>	

  <script src="https://js.pusher.com/4.1/pusher.min.js"></script>
  <script>
  var Chatid;
  var Json = new Object();
  var status;
 
 $(document).ready(function(){
		
	  var preloadbg = document.createElement("img");
	  preloadbg.src = "./assets/img/anonimo.png";
	  
		$("#sendmessage input").focus(function(){
			if($(this).val() == "Envía un mensaje..."){
				$(this).val("");
			}
		});
		$("#sendmessage input").focusout(function(){
			if($(this).val() == ""){
				$(this).val("Envía un mensaje...");
				
			}
		});
			

			
		
		$(".friend").each(function(i, message){	
			status =  $(this).attr("value");
			
			if(status == 1){
				return;
			}else{
			$(this).click(function(){		
				
				Chatid = $(this).attr("id");
				
			    var pusher = new Pusher('e8eb951d24dae72ff214', {
			        cluster: 'eu'
			      });
			    
			    var chat = new ChatWidget(pusher, Chatid);
			    
				var childOffset = $(this).offset();
				var parentOffset = $(this).parent().parent().offset();
				var childTop = childOffset.top - parentOffset.top;
				var clone = $(this).find('img').eq(0).clone();
				var top = childTop+12+"px";
				
				$(clone).css({'top': top}).addClass("floatingImg").appendTo("#chatbox");									
				
				setTimeout(function(){$("#profile p").addClass("animate");$("#profile").addClass("animate");}, 100);
				setTimeout(function(){
					$("#chat-messages").addClass("animate");
					$('.cx, .cy').addClass('s1');
					setTimeout(function(){$('.cx, .cy').addClass('s2');}, 100);
					setTimeout(function(){$('.cx, .cy').addClass('s3');}, 200);			
				}, 150);														
				
				$('.floatingImg').animate({
					'width': "68px",
					'left':'108px',
					'top':'20px'
				}, 200);
				
				var name = $(this).find("p strong").html();
				var email = $(this).find("p span").html();														
				$("#profile p").html(name);
				$("#profile span").html(email);			
				
				$(".message").not(".right").find("img").attr("src", $(clone).attr("src"));									
				$('#friendslist').fadeOut();
				$('#chatview').fadeIn();
				
				$('#ChatImg').hide();
				$('#chat-messages').animate({scrollTop:9999}, '500', 'swing');
				
				$('#close').unbind("click").click(function(){		
					window.location.href = ("/GeolocApp/Chat.jsp");
					$("#chat-messages, #profile, #profile p").removeClass("animate");
					$('.cx, .cy').removeClass("s1 s2 s3");
					$('.floatingImg').animate({
						'width': "40px",
						'top':top,
						'left': '12px'
					}, 200, function(){$('.floatingImg').remove()});				
					
					setTimeout(function(){
						$('#chatview').fadeOut();
						$('#friendslist').fadeIn();				
					}, 50);
					
					$('#ChatImg').show();
				});
				
			});
			}
		});			
	});
  </script>    
  
   <script type="text/javascript">
    
   var x = document.getElementById("mensaje-chat");
   
   	function pulsar(e) {
   		if (e.keyCode == 13 && !e.shiftKey) {
   			e.preventDefault();
   			if($("#sendmessage input").val() != ""){
   			$('#chat-messages').animate({scrollTop:9999}, '500', 'swing');
   			document.getElementById("send").onclick();
   			}
   		}
   	}
   
    function sendAjax() {

    	
    	function Redirect() {
			window.location.href = ("/GeolocApp/Index.jsp");
    		}
    	
    	Json.text = x.value;
    	Json.ChatId = Chatid;
    	Json.UserId = <%=twitter.getId()%>
		;
		console.log(Json);
		
		 if (typeof Json == 'object'){
			 console.log("Es ya un Json");
		 }else {
			 console.log("que es eso");
		 }
		 var JsonF = JSON.stringify(Json);
		 	 
		 
		$.ajax({
			url: "NewMessage",
			type: 'POST',
			dataType: "text",
			data :JsonF,
			
			success: function (data) {
				console.log("success");
				$("#sendmessage input").val("");
			},
		      error:function(data,status,er) {
		            console.log("error");
		        }
			
		});
    	
    }
    </script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>