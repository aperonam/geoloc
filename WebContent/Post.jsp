<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="share-container">
	<textarea id="share-text" placeholder="Share something..."></textarea>
	<input id="share-tag" type="text" placeholder="Add tag..." />
	<button id="share-button" onclick="shareThought()"><img alt="share-icon" src="./assets/img/share-button.svg"></button>
</div>
<script>
	function shareThought() {
		// Take values
		var text = document.getElementById("share-text").value;
		var tag = document.getElementById("share-tag").value;
		if (tag == '') { tag = undefined; }
		
		// Refresh user location
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				window.latitude = position.coords.latitude;
				window.longitude = position.coords.longitude;
			})
		} else {
			return;
		}
		
		// Make request
		postThought(text, tag, window.latitude, window.longitude);
		
		// Clean values
		document.getElementById("share-text").value = '';
		document.getElementById("share-tag").value = ''
	}
</script>