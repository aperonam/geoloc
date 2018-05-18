package es.upm.dit.geoloc.dao;

import java.util.List;

import es.upm.dit.geoloc.dao.model.Message;

public interface MessageDAO {

	public void createMessage(Message message);
	public Message readMessage(double id);
	public void updateMessage(Message message);
	public void deleteMessage(Message message);
	public List<Message> getMessage();
	
}
