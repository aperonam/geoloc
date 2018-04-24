package es.upm.dit.geoloc.dao;

import java.util.List;

import es.upm.dit.geoloc.dao.model.Thought;

public interface ThoughtDAO {
	
	public void createThought(Thought thought);
	public Thought readThought(double id);
	public void updateThought(Thought thought);
	public void deleteThought(Thought thought);
	public List<Thought> getAll();
	public List<Thought> getPopular();
	public List<Thought> getTags(String tag);
	public List<Thought> getTagsPopulares(String tag);
	public List<Thought> getMisMarcadores(long UserId);
	public List<Thought> getTagsMisMarcadores(String tag, long UserId);
}
