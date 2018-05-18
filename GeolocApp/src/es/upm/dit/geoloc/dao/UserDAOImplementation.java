package es.upm.dit.geoloc.dao;

import org.hibernate.Session;

import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;

public class UserDAOImplementation implements UserDAO{

	private static UserDAOImplementation instance;
	public UserDAOImplementation() {};
	public static UserDAOImplementation getInstance() {
		if(null == instance) instance = new UserDAOImplementation();
		return instance;
	}

	@Override
	public void createUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
						session.beginTransaction();
		User usuario = (User) session.createQuery("select p from User p where p.UserID= :UserID")
				.setParameter("UserID", user.getUserID())
				.uniqueResult();
		if(usuario == null) {
		            	session.save(user);
		            	session.getTransaction().commit();
		}
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

	@Override
	public User readUser(long UserId) {
		User user = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	User usuario = (User) session.createQuery("select p from User p where p.UserId= :UserId")
		        				.setParameter("UserId", UserId)
		        				.uniqueResult();
		            	user = usuario;
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();

		            	
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

	@Override
	public void deleteUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.delete(user);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

}