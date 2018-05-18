
function ChatWidget(pusher) {
	
	var self = this;
	
	this.pusher = pusher;
	
	this.channelName = "chat";
	
	this.chatContanier = $('#chat-messages');
	
	this.messageContainer = $('.message');
	
	this.chatEntry = $('#sendmessage');
	
	this.channel = this.pusher.subscribe(this.channelName);
	
	this.channel.bind('new-message', function(data) {
		self.displayMessage(data);
	})
	
	this.Allmessage();
};


ChatWidget.prototype.Allmessage = function () {
	
	var self = this;
	
	$.ajax({
		url : 'Mensaje',
		dataType: 'json',
		type: 'get',
		success: function (data) {
			console.log(data);
			
			$.each(data, function(i, message) {
				if(message.ChatId == Chatid){
				self.displayMessage(message);
				}else{
					console.log("mensaje de otro chat");
				}
				
			})
		}
	});

ChatWidget.prototype.displayMessage = function(message){
	
	if(message.UserId == UserId){
	var m = '<div class="message"><img src="./assets/img/anonimo.png" /><div class="bubble">'+message.text+'<div class="corner"></div><span>'+message.createdAt+'</span></div></div>';
	this.messageContainer.append(m);
	} else {
	var m = '<div class="message right"><img src="./assets/img/anonimo.png" /><div class="bubble">'+message.text+'<div class="corner"></div><span>'+message.createdAt+'</span></div></div>';
	this.messageContainer.append(m);

	}

}
}