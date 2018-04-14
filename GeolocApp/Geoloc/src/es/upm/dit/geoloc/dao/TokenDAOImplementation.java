package es.upm.dit.geoloc.dao;

import org.hibernate.Session;


import es.upm.dit.geoloc.dao.model.Token;
import es.upm.dit.geoloc.dao.SessionFactoryService;

public class TokenDAOImplementation implements TokenDAO{

	private static TokenDAOImplementation instance;
	private TokenDAOImplementation() {};
	public static TokenDAOImplementation getInstance() {
		if(null == instance) instance = new TokenDAOImplementation();
		return instance;
	}
	@Override
	public void createToken(Token token) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.save(token);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}
	@Override
	public Token readToken(double id) {
		Token token = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	token = session.get(Token.class, id);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		return token;
	}
	@Override
	public void updateToken(Token token) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.saveOrUpdate(token);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}
	@Override
	public void deleteToken(Token token) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.delete(token);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
	}

}
