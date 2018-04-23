<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<textarea id="share-text" rows="4" cols="20" placeholder="Share something..."></textarea>
	<input id="share-tag" type="text" placeholder="Add tag" />
	<button id="share-button" onclick="shareThought()">SHARE</button>
</div>
<script>
	function shareThought() {
		// Take values
		var text = document.getElementById("share-text").value;
		var tag = document.getElementById("share-tag").value;
		if (tag == '') { tag = undefined; }
		
		var latitude = null;
		var longitude = null;
		
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
		var thought = postThought(text, tag, latitude, longitude);
		
		// Reload page
		location.reload();
	}
</script>