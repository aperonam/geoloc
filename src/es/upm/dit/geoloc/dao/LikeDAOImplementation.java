package es.upm.dit.geoloc.dao;

import org.hibernate.Session;

import es.upm.dit.geoloc.dao.model.Like;
import es.upm.dit.geoloc.dao.model.Thought;

public class LikeDAOImplementation implements LikeDAO {
	
	private static LikeDAOImplementation instance;
	public LikeDAOImplementation() {};
	public static LikeDAOImplementation getInstance() {
		if (null == instance) instance = new LikeDAOImplementation();
		return instance;
	}

	@Override
	public int createLike(Like like) {
		Session session = SessionFactoryService.get().openSession();
		Integer likeId = null;
		
		try {
			session.beginTransaction();
			likeId = (Integer) session.save(like);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
			e.printStackTrace();
		} finally {
			session.close();
		}
		return likeId;
	}

	@Override
	public Like readLike(int id) {
		Like like = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			like = session.get(Like.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return like;
	}

	@Override
	public void deleteLike(Like like) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(like);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
	}
	
}
