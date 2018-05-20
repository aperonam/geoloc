package es.upm.dit.geoloc.dao;

import java.util.List;

import es.upm.dit.geoloc.dao.model.Likes;

public interface LikesDAO {
	public void createLikes(Likes likes);
	public Likes read(int id);
	public Likes readLikes(int thoughtId, long userId);
	public List<Likes> getMisLikes(long UserId);
	public void changeStatus(int thoughtId, long userId);
	public void changeStatus2(int thoughtId, long userId);
	public void delete(Likes like);
}