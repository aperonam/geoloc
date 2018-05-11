<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="nav-buttons">
	<button id="filter-button" onclick="openFilter()"><img alt="filter button" src="./assets/img/filter-button.svg"></button>
	<button id="chats-button" onclick="openChats()"><img alt="chats button" src="./assets/img/chat-button.svg"></button>
</div>
<script>
	function openFilter() {
		let value = document.getElementById("filter").style.display;
		if (value == "none") {
			document.getElementById("filter").style.display = "block";
			document.getElementById("chats").style.display = "none";
		} else {
			document.getElementById("filter").style.display = "none";
		}
	}
	
	function openChats() {
		let value = document.getElementById("chats").style.display;
		if (value == "none") {
			document.getElementById("chats").style.display = "block";
			document.getElementById("filter").style.display = "none";
		} else {
			document.getElementById("chats").style.display = "none";
		}
	}
</script>