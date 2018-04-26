<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="share-container">
	<textarea id="share-text" placeholder="Share something..."></textarea>
	<input id="share-tag" type="text" placeholder="Add tag..." />
	<button id="share-button" onclick="shareThought()"><img alt="share-icon" src="./assets/img/plane-icon.png"></button>
</div>
<script>
	function shareThought() {
		// Take values
		var text = document.getElementById("share-text").value;
		var tag = document.getElementById("share-tag").value;
		if (tag == '') { tag = undefined; }
		
		var latitude = 40.458848;
		var longitude = -3.688259;
		
		// Get user location
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				latitude = position.coords.latitude;
				longitude = position.coords.longitude;
			})
		} else {
			return;
		}
		
		// Make request
		postThought(text, tag, latitude, longitude);
		
		// Clean values
		document.getElementById("share-text").value = '';
		document.getElementById("share-tag").value = ''
	}
</script>