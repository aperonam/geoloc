package es.upm.dit.geoloc.dao;

import org.hibernate.Session;

import es.upm.dit.geoloc.dao.model.Thought;




public class ThoughtDAOImplementation implements ThoughtDAO{

	private static ThoughtDAOImplementation instance;
	private ThoughtDAOImplementation() {};
	public static ThoughtDAOImplementation getInstance() {
		if(null == instance) instance = new ThoughtDAOImplementation();
		return instance;
	}

	@Override
	public void createThought(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.save(thought);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

	@Override
	public Thought readThought(double id) {
		Thought thought = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	thought = session.get(Thought.class, id);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		return thought;
	}

	@Override
	public void updateThought(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.saveOrUpdate(thought);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

	@Override
	public void deleteThought(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.delete(thought);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

}
