package es.upm.dit.geoloc.dao;

import org.hibernate.Session;

import es.upm.dit.geoloc.dao.model.Likee;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;

public class LikeDAOImplementation implements LikeDAO {
	
	private static LikeDAOImplementation instance;
	public LikeDAOImplementation() {};
	public static LikeDAOImplementation getInstance() {
		if (null == instance) instance = new LikeDAOImplementation();
		return instance;
	}

	@Override
	public Likee createLike(Likee like) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			int id = (int) session.save(like);
			like.setId(id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
			e.printStackTrace();
		} finally {
			session.close();
		}
		return like;
	}

	@Override
	public Likee readLike(int id) {
		Likee like = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			like = session.get(Likee.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return like;
	}
	
	@Override
	public Likee readLike(int thoughtId, int userId) {
		Likee like = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			like = (Likee) session.createQuery("SELECT l FROM Likee l WHERE l.thought_id= :thougthId AND l.user_id= :userId")
					.setParameter("thoughtId", thoughtId)
					.setParameter("userId", userId)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: Manage Exceptions
		} finally {
			session.close();
		}
		return like;
	}

	@Override
	public void deleteLike(Likee like) {
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
