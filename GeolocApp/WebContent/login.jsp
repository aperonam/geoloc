<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>


<link rel="stylesheet" type="text/css" href="./assets/css/Buttons.css">
<link rel="stylesheet" type="text/css" href="./assets/css/box.css">
<link rel="stylesheet" type="text/css" href="./assets/css/login.css">
</head>
<body>
<div class="cabecera">
<div id="divlogo"><img id="logoCab" src="./assets/img/LOGO.png" alt="logo"/></div>
<div id="divbusca"><img id="busca" src="./assets/img/buscaLogo.png" alt="logoBusca"/></div>
<div id="divpublica"><img id="publica" src="./assets/img/publica.png" alt="logoPublica"/></div>
<div id="divchatea"><img id="chatea" src="./assets/img/chatea.png" alt="logoChatea"/></div>
<div id="divlogin"><section class="row1">  
 <div class="seccion">
  <div class="boton-linea-ext">
    <span class="linea1"></span>
    <span class="linea2"></span>
    <span class="linea3"></span>
    <span class="linea4"></span>
    <a id="modalBtn" class="button">INICIA SESIÓN</a>
  </div>
 </div>
</section></div>
</div>
<div id="simpleModal" class="modal">
    <div class="modal-content">
      <div class="modal-header">
      	<img id="logo" src="./assets/img/logoPop.png" alt="logo"/>
        <span class="closeBtn">&times;</span>
      </div>
      <div class="modal-body">
      
		<a href="signin" id="twtButton"><img id="twitter" src="./assets/img/twitter.png" alt="Twitter"/></a>
		<p id="Condiciones">*Al iniciar sesión aceptas que la aplicación obtenga tu ubicación, así como tu email de registro de twitter. Estos datos se mantendrán almacenados de forma segura y nunca serán expuestos sin su consentimiento.</p>
      </div>
    </div>
  </div>
<div id="cuerpo">
	<div id="Logoinicio">
		<img id="logoIni" src="./assets/img/inicio.png" alt="logoInicia"/>
	</div>
<div id="bloque">	
	<div id="logoMensaje">
		<img id="logoMen" src="./assets/img/mensaje.png" alt="logoMensaje"/>
	</div>
	<div id="Registrate">		
	<section class="row11">  
	 <div class="seccion2">
 		 <div class="boton-linea-ext">
    		<span class="linea1"></span>
    		<span class="linea2"></span>
    		<span class="linea3"></span>
    		<span class="linea4"></span>
    		<a id="modalBtn2" class="button">REGÍSTRATE AHORA</a>
  		</div>
 	</div>
	</section>
	</div>
</div>
</div>  
<script>
//crea el modal
var modal = document.getElementById('simpleModal');
// crea el boton de abrir
var modalBtn = document.getElementById('modalBtn');
//crea el boton de abrir
var modalBtn2 = document.getElementById('modalBtn2');
// crea el boton de cerrar
var closeBtn = document.getElementsByClassName('closeBtn')[0];

// Listen para abrir
modalBtn.addEventListener('click', openModal);
//Listen para abrir
modalBtn2.addEventListener('click', openModal);
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
</body>
</html>