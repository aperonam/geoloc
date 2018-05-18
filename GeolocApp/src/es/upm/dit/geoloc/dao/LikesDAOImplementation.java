package es.upm.dit.geoloc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.Likes;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;

public class LikesDAOImplementation implements LikesDAO {

	@Override
	public void createLikes(Likes likes) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Likes likes1 = (Likes) session.createQuery("select p from Likes p where p.ThoughtId= :ThoughtId and UserId= :UserId")
		        				.setParameter("ThoughtId", likes.getThoughtId())
		        				.setParameter("UserId", likes.getUserId())
		        				.uniqueResult();
		        		if(likes1 == null) {
			            	session.save(likes);
			            	session.getTransaction().commit();
			}
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

	@Override
	public Likes readLikes(int id, long UserId) {
		Likes likes = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Likes l = (Likes) session.createQuery("select p from Likes p where p.ThoughtId= :ThoughtId and UserId= :UserId")
		        				.setParameter("ThoughtId", id)
		        				.setParameter("UserId", UserId)
		        				.uniqueResult();
		            	likes = l;
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		return likes;
	}

	@Override
	public void updateChat(Likes likes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteChat(Likes likes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Likes> getMisLikes(long UserId) {
		Session session = SessionFactoryService.get().openSession();
		List<Likes> likes = new ArrayList<Likes>();
		try {
        	session.beginTransaction();
        	List<Likes> l = session.createQuery("select p from Likes p where UserId= :UserId").setParameter("UserId",UserId ).list();
        	likes = l;
        	session.getTransaction().commit();
} catch (Exception e) {
        	e.getMessage();
} finally {

	session.close();
}
		return likes;
	}

	@Override
	public void changeStatus(int id, long UserId) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Query query = session.createQuery("update Likes set status = :status where ThoughtId = :ThoughtId and UserId= :UserId");
		    query.setParameter("ThoughtId",id);
		    query.setParameter("UserId", UserId);
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
	public void changeStatus2(int id, long UserId) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	Query query = session.createQuery("update Likes set status = :status where ThoughtId = :ThoughtId and UserId= :UserId");
		    query.setParameter("ThoughtId",id);
		    query.setParameter("UserId", UserId);
		    query.setParameter("status",1);
		    
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
