package es.upm.dit.geoloc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.geoloc.dao.model.Message;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;


public class MessageDAOImplementation implements MessageDAO {

	@Override
	public void createMessage(Message message) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.save(message);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

	@Override
	public Message readMessage(double id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Message> getMessage() {
		Session session = SessionFactoryService.get().openSession();
		List<Message> ArrayMessage = new ArrayList<Message>();
		try {
        	session.beginTransaction();
        	List<Message> mensajes = session.createQuery("select p from Message p").list();
        	ArrayMessage = mensajes;
        	session.getTransaction().commit();
} catch (Exception e) {
        	e.getMessage();
} finally {

	session.close();
}
		return ArrayMessage;
	}

}
