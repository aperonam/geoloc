package es.upm.dit.geoloc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;




public class ThoughtDAOImplementation implements ThoughtDAO{

	private static ThoughtDAOImplementation instance;
	public ThoughtDAOImplementation() {};
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
	public Thought readThought(int id) {
		Thought thought = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Thought pensamiento = (Thought) session.createQuery("select p from Thought p where p.id= :Id")
		        				.setParameter("Id", id)
		        				.uniqueResult();
		            	thought = pensamiento;
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
		            	Query query = session.createQuery("update Thought set lat = :latitud, lng = :longitud where id = :id");
		    query.setParameter("latitud",thought.getLat());
		    query.setParameter("longitud",thought.getLng());
		    query.setParameter("id", thought.getId() );
		    
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
	
	@Override
	public List<Thought> getAll() {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> ArrayThought = new ArrayList<Thought>();
		try {
        	session.beginTransaction();
        	List<Thought> pensamientos = session.createQuery("select p from Thought p").list();
        	ArrayThought=pensamientos;
        	session.getTransaction().commit();
} catch (Exception e) {
        	// manejar excepciones
} finally {
        	session.close();
}
		return ArrayThought;
	}
	
	
	@Override
	public List<Thought> getPopular() {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> ArrayThought = new ArrayList<Thought>();
		try {
        	session.beginTransaction();
        	List<Thought> pensamientos = session.createQuery("select p from Thought p where likes >= :likes").setParameter("likes", 100).list();
        	ArrayThought=pensamientos;
        	session.getTransaction().commit();
} catch (Exception e) {
        	// manejar excepciones
} finally {
        	session.close();
}
		return ArrayThought;
	}
	
	@Override
	public List<Thought> getTags(String tag) {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> ArrayThought = new ArrayList<Thought>();
		try {
        	session.beginTransaction();
        	List<Thought> pensamientos = session.createQuery("select p from Thought p where tag1= :tag or tag2= :tag or tag3= :tag or tag4= :tag or tag5= :tag").setParameter("tag", tag).list();
        	ArrayThought=pensamientos;
        	session.getTransaction().commit();
} catch (Exception e) {
        	// manejar excepciones
} finally {
        	session.close();
}
		return ArrayThought;
	}
	
	@Override
	public List<Thought> getTagsPopulares(String tag) {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> ArrayThought = new ArrayList<Thought>();
		try {
        	session.beginTransaction();
        	List<Thought> pensamientos = session.createQuery("select p from Thought p where likes >= :likes and (tag1= :tag or tag2= :tag or tag3= :tag or tag4= :tag or tag5= :tag)").setParameter("tag", tag).setParameter("likes", 100).list();
        	ArrayThought=pensamientos;
        	session.getTransaction().commit();
} catch (Exception e) {
        	// manejar excepciones
} finally {
        	session.close();
}
		return ArrayThought;
	}	
	
	@Override
	public List<Thought> getTagsMisMarcadores(String tag, long UserId) {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> ArrayThought = new ArrayList<Thought>();
		try {
        	session.beginTransaction();
        	List<Thought> pensamientos = session.createQuery("select p from Thought p where UserId= :UserId and (tag1= :tag or tag2= :tag or tag3= :tag or tag4= :tag or tag5= :tag)").setParameter("tag", tag).setParameter("UserId",UserId ).list();
        	ArrayThought=pensamientos;
        	session.getTransaction().commit();
} catch (Exception e) {
        	// manejar excepciones
} finally {
        	session.close();
}
		return ArrayThought;
	}	
	
	@Override
	public List<Thought> getMisMarcadores(long UserId) {
		Session session = SessionFactoryService.get().openSession();
		List<Thought> ArrayThought = new ArrayList<Thought>();
		try {
        	session.beginTransaction();
        	List<Thought> pensamientos = session.createQuery("select p from Thought p where UserId= :UserId").setParameter("UserId",UserId ).list();
        	ArrayThought=pensamientos;
        	session.getTransaction().commit();
} catch (Exception e) {
        	e.getMessage();
} finally {

	session.close();
}
		return ArrayThought;
	}
	


	
	@Override
	public void changeLike(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Query query = session.createQuery("update Thought set likes = :likes where id = :id");
		    query.setParameter("id",thought.getId());
		    query.setParameter("likes",thought.getLikes()+1);
		    
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
	public void changeDislike(Thought thought) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Query query = session.createQuery("update Thought set likes = :likes where id = :id");
		    query.setParameter("id",thought.getId());
		    query.setParameter("likes",thought.getLikes()-1);
		    
		    int result = query.executeUpdate();
		    
		    System.out.println(result);
		    
		            	session.getTransaction().commit();
		} catch (Exception e) {
		       System.out.println(e.getMessage());
		} finally {
		            	session.close();
		}
}
	
}