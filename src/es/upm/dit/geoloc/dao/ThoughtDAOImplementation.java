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

	
	public Thought createThought(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		
		try {
			session.beginTransaction();
			int id = (int) session.save(thought);
			thought.setId(id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
			e.printStackTrace();
		} finally {
			session.close();
		}
		return thought;
	}

	
	public Thought readThought(int id) {
		Session session = SessionFactoryService.get().openSession();
		Thought thought = null;
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
	
	public List<Thought> readThoughts() {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> thoughts = new ArrayList<Thought>();
		try {
			session.beginTransaction();
			thoughts.addAll(session.createQuery("SELECT t FROM Thought t").getResultList());
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return thoughts;
	}
	
	public List<Thought> readThoughtsNear(double latitude, double longitude) {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> thoughts = null;
		try {
			session.beginTransaction();
			thoughts.addAll(session.createQuery("SELECT t FROM Thought t WHERE t.latitude < :latitudeMax AND t.latitude > :latitudeMin AND t.longitude < :longitudeMax AND t.longitude > :longitudeMin")
					.setParameter("latitudeMax", latitude + 0.04)
					.setParameter("latitudeMin", latitude - 0.04)
					.setParameter("longitudeMax", longitude + 0.04)
					.setParameter("longitudeMin", longitude - 0.04)
					.getResultList());
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return thoughts;
	}
	
	public List<Thought> readPopularThoughts() {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> thoughts = null;
		try {
			session.beginTransaction();
			// TODO
			thoughts.addAll(session.createQuery("SELECT t FROM Thought t").getResultList());
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
