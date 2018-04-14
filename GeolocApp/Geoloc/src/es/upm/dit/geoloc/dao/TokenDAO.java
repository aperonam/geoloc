package es.upm.dit.geoloc.dao;



import es.upm.dit.geoloc.dao.model.Token;

public interface TokenDAO {
	
	public void createToken(Token token);
	public Token readToken(double id);
	public void updateToken(Token token);
	public void deleteToken(Token token);
	
}
