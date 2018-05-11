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
			thought.numberOfLikes = 0;
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

// Likes

function postLike(thoughtId) {
	// Make request
	fetch('http://localhost:8080/geoloc/api/thought/like', {
		method: 'POST',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		credentials: 'include',
		body: JSON.stringify({ thoughtId: thoughtId }),
	}).then((response) => {
		window.thoughts.forEach((thought) => {
			if (thought.id == thoughtId) {
				thought.numberOfLikes += 1;
			}
		});
		reloadThoughts();
	}).catch((error) => {
		console.log(error);
	});
}


// Chats

function requestChat(thoughtId) {
	// Make request
	fetch('http://localhost:8080/geoloc/api/thought/chat', {
		method: 'POST',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		credentials: 'include',
		body: JSON.stringify({ thoughtId: thoughtId }),
	}).then((response) => {
		console.log('chat request done');
	}).catch((error) => {
		console.log(error);
	});
}

function acceptChatRequest(chatId) {
	// Make request
	fetch('http://localhost:8080/geoloc/api/chat', {
		method: 'PUT',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		credentials: 'include',
		body: JSON.stringify({ chatId: chatId }),
	}).then((response) => {
		let requests = window.chatRequests.filter((request) => {
			return request.id != chatId;
		});
		window.chats.splice(0, 0, { id: chatId });
		window.chatRequests = requests;
	}).catch((error) => {
		console.log(error);
	});
}

function denyChatRequest(chatId) {
	// Make request
	fetch('http://localhost:8080/geoloc/api/chat', {
		method: 'DELETE',
		headers: {
		Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		credentials: 'include',
		body: JSON.stringify({ chatId: chatId }),
	}).then((response) => {
		let requests = window.chatRequests.filter((request) => {
			return request.id != chatId;
		});
		window.chatRequests = requests;
	}).catch((error) => {
		console.log(error);
	});
}