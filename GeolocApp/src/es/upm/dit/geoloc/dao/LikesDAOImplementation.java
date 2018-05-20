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
	
	private static LikesDAOImplementation instance;
	public LikesDAOImplementation() {};
	public static LikesDAOImplementation getInstance() {
		if(null == instance) instance = new LikesDAOImplementation();
		return instance;
	}

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
	public Likes readLikes(int thoughtId, long userId) {
		Session session = SessionFactoryService.get().openSession();
		Likes likes = null;
		try {
			session.beginTransaction();
			likes = (Likes) session.createQuery("select p from Likes p where p.ThoughtId= :ThoughtId and UserId= :UserId")
				.setParameter("ThoughtId", thoughtId)
				.setParameter("UserId", userId)
				.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		return likes;
	}

	
	@Override
	public List<Likes> getMisLikes(long UserId) {
		Session session = SessionFactoryService.get().openSession();
		List<Likes> likes = new ArrayList<Likes>();
		try {
			session.beginTransaction();
			likes.addAll(session.createQuery("select p from Likes p where UserId= :UserId").setParameter("UserId",UserId ).getResultList());
	        	session.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			session.close();
		}
		return likes;
	}

	@Override
	public void changeStatus(int thoughtId, long userId) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.createQuery("update Likes set status = :status where ThoughtId = :ThoughtId and UserId= :UserId")
				.setParameter("ThoughtId", thoughtId)
				.setParameter("UserId", userId)
				.setParameter("status", 2)
				.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	@Override
	public void changeStatus2(int thoughtId, long userId) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.createQuery("update Likes set status = :status where ThoughtId = :ThoughtId and UserId= :UserId")
				.setParameter("ThoughtId", thoughtId)
				.setParameter("UserId", userId)
				.setParameter("status", 1)
				.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	@Override
	public void delete(Likes like) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(like);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
	}
	
	@Override
	public Likes read(int id) {
		Session session = SessionFactoryService.get().openSession();
		Likes like = null;
		try {
			session.beginTransaction();
			like = (Likes) session.find(Likes.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return like;
	}

}