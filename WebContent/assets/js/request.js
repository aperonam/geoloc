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
	fetch('https://localhost:8080/geoloc/thought', {
		method: 'POST',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(thought),
	}).then((response) => {
		return response.json();
	}).catch((error) => {
		console.error(error);
	});
}

function deleteThought(id) {
	// Make request
	fetch('https://localhost:8080/geoloc/thought?id=' + id, {
		method: 'DELETE',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(thought),
	}).then((response) => {
		return response.json();
	}).catch((error) => {
		console.error(error);
	});
}