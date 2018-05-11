package es.upm.dit.geoloc.dao;

import es.upm.dit.geoloc.dao.model.Likee;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;

public interface LikeDAO {

	public Likee createLike(Likee like);
	public Likee readLike(int id);
	public void deleteLike(Likee like);
	public Likee readLike(int thoughtId, int userId);
	
}
