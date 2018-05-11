package es.upm.dit.geoloc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.Likee;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;

public class ChatDAOImplementation implements ChatDAO {
	
	private static ChatDAOImplementation instance;
	private ChatDAOImplementation() {}
	public static ChatDAOImplementation getInstance() {
		if (null == instance) instance = new ChatDAOImplementation();
		return instance;
	}

	@Override
	public Chat requestChat(Chat chat) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			int id = (int) session.save(chat);
			chat.setId(id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
			e.printStackTrace();
		} finally {
			session.close();
		}
		return chat;
	}

	@Override
	public void acceptChatRequest(Chat chat) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			chat.setAccepted(true);
			session.saveOrUpdate(chat);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
	}
	
	@Override
	public void declineChatRequest(Chat chat) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(chat);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
	}

	@Override
	public Chat readChat(int id) {
		Session session = SessionFactoryService.get().openSession();
		Chat chat = null;
		try {
			session.beginTransaction();
			chat = session.get(Chat.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return chat;
	}
	
	@Override
	public Chat readChat(int thoughtId, int userId) {
		Session session = SessionFactoryService.get().openSession();
		Chat chat = null;
		try {
			session.beginTransaction();
			chat = (Chat) session.createQuery("SELECT c FROM Chat c WHERE c.thought_id= :thougthId AND c.user_id= :userId")
					.setParameter("thoughtId", thoughtId)
					.setParameter("userId", userId)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return chat;
	}

	@Override
	public List<Chat> readUserChats(User user) {
		Session session = SessionFactoryService.get().openSession();
		List<Chat> chats = new ArrayList<Chat>();
		try {
			session.beginTransaction();
			chats.addAll(session.createQuery("SELECT c FROM Chat c WHERE (c.user_id= :id OR c.to_user_id= :id) AND c.accepted=true")
					.setParameter("id", user.getId())
					.getResultList());
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return chats;
	}
	
	@Override
	public List<Chat> readUserChatRequests(User user) {
		Session session = SessionFactoryService.get().openSession();
		List<Chat> chats = new ArrayList<Chat>();
		try {
			session.beginTransaction();
			chats.addAll(session.createQuery("SELECT c FROM Chat c WHERE c.to_user_id= :id AND c.accepted=false")
					.setParameter("id", user.getId())
					.getResultList());
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return chats;
	}

}
