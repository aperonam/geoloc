/*
 *	Requests to API
 */


function postThought(text, tag, latitude, longitude) {
	var thought = {
		text: text,
		location: {
			latitude: latitude,
			longitude: longitude
		}
	};

	if (tag) {
		thought.tag = tag;
	}

	// Make request
	fetch('http://localhost:8080/geoloc/api/thought', {
		method: 'POST',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		credentials: 'include',
		body: JSON.stringify(thought),
	}).then((response) => {
		response.json().then(thought => {
			// Add thought to thoughts storage
			window.thoughts.splice(0, 0, thought);
			
			// Reload thoughts
			reloadThoughts();
		});
	}).catch((error) => {
		console.log(error);
	});
}

function deleteThought(id) {
	// Make request
	fetch('http://localhost:8080/geoloc/api/thought?id=' + id, {
		method: 'DELETE',
		credentials: 'include',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(thought),
	}).then((response) => {
		return response.json();
	}).catch((error) => {
		console.log(error);
	});
}