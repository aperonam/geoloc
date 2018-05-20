package es.upm.dit.geoloc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import es.upm.dit.geoloc.dao.model.User;
import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.Thought;


public class ChatDAOImplementation implements ChatDAO {
	
	private static ChatDAOImplementation instance;
	public ChatDAOImplementation() {};
	public static ChatDAOImplementation getInstance() {
		if(null == instance) instance = new ChatDAOImplementation();
		return instance;
	}

	@Override
	public void createChat(Chat chat) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			Chat c = (Chat) session.createQuery("select p from Chat p where p.ThoughtId= :ThoughtId")
				.setParameter("ThoughtId",chat.getThoughtId())
				.uniqueResult();
			
			if (c == null) {
				session.save(chat);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		
	}

	@Override
	public Chat readChat(int id) {
		Chat chat = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			Chat xat = (Chat) session.createQuery("select p from Chat p where p.id= :id")
				.setParameter("id", id)
				.uniqueResult();
			chat = xat;
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		return chat;
	}

	@Override
	public void updateChat(Chat chat) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(chat);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteChat(Chat chat) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(chat);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
	}
	
	@Override
	public List<Chat> getMisChats(long UserId) {
		Session session = SessionFactoryService.get().openSession();
		List<Chat> chats = new ArrayList<Chat>();
		try {
			session.beginTransaction();
			chats.addAll(session.createQuery("select p from Chat p where UserId1= :UserId1 or UserId2= :UserId2").setParameter("UserId1",UserId ).setParameter("UserId2", UserId).getResultList());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			session.close();
		}
		return chats;
	}
	
	@Override
	public int getStatus(int ChatId) {
		Session session = SessionFactoryService.get().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Chat chat = (Chat) session.createQuery("select p from Chat p where ChatId= :ChatId").setParameter("ChatId",ChatId ).uniqueResult();
			status = chat.getStatus();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public void changeStatus(int idchat) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Query query = session.createQuery("update Chat set status = :status where id = :id");
		    query.setParameter("id",idchat);
		    query.setParameter("status",2);
		    
		    int result = query.executeUpdate();
		    
		    System.out.println(result);
		    
		            	session.getTransaction().commit();
		} catch (Exception e) {
		       System.out.println(e.getMessage());
		} finally {
		            	session.close();
		}
	}
	
	
	@Override
	public Chat create(Chat chat) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			int id = (int) session.save(chat);
			chat.setId(id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return chat;
	}

}