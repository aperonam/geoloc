package es.upm.dit.geoloc.dao;

import es.upm.dit.geoloc.dao.model.Like;
import es.upm.dit.geoloc.dao.model.Thought;

public interface LikeDAO {

	public int createLike(Like like);
	public Like readLike(int id);
	public void deleteLike(Like like);
	
}
