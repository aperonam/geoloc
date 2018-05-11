package es.upm.dit.geoloc.dao;

import java.util.List;

import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.User;

public interface ChatDAO {

	public Chat requestChat(Chat chat);
	public void acceptChatRequest(Chat chat);
	public void declineChatRequest(Chat chat);
	public Chat readChat(int id);
	public List<Chat> readUserChats(User user);
	public List<Chat> readUserChatRequests(User user);
	public Chat readChat(int thoughtId, int userId);
	
}
