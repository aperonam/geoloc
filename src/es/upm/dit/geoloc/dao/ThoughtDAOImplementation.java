package es.upm.dit.geoloc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import es.upm.dit.geoloc.dao.model.Thought;


public class ThoughtDAOImplementation implements ThoughtDAO {

	private static ThoughtDAOImplementation instance;
	private ThoughtDAOImplementation() {};
	public static ThoughtDAOImplementation getInstance() {
		if (null == instance) instance = new ThoughtDAOImplementation();
		return instance;
	}

	
	public Integer createThought(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		Integer thoughtId = null;
		
		try {
			session.beginTransaction();
			thoughtId = (Integer) session.save(thought);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
			e.printStackTrace();
		} finally {
			session.close();
		}
		return thoughtId;
	}

	
	public Thought readThought(double id) {
		Thought thought = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			thought = session.get(Thought.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return thought;
	}
	
	public List<Thought> readThoughtsNear(double latitude, double longitude) {
		List<Thought> thoughts = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			thoughts = session.createQuery("SELECT text, tag, latitude, longitude FROM Thought;").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return thoughts;
	}

	
	public void updateThought(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(thought);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		
	}

	
	public void deleteThought(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(thought);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		
	}

}
