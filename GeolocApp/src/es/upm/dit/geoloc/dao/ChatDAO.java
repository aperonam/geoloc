package es.upm.dit.geoloc.dao;

import java.util.List;

import es.upm.dit.geoloc.dao.model.Chat;

public interface ChatDAO {
	public void createChat(Chat chat);
	public Chat readChat(int id);
	public void updateChat(Chat chat);
	public void deleteChat(Chat chat);
	public List<Chat> getMisChats(long UserId);
	public int getStatus(int ChatId);
	public void changeStatus(int idchat);
	public Chat create(Chat chat);
}