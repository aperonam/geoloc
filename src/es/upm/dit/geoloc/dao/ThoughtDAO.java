package es.upm.dit.geoloc.dao;

import es.upm.dit.geoloc.dao.model.Thought;

public interface ThoughtDAO {
	
	public Integer createThought(Thought thought);
	public Thought readThought(double id);
	public void updateThought(Thought thought);
	public void deleteThought(Thought thought);
	
}