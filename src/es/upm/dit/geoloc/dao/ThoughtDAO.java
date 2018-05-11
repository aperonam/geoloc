package es.upm.dit.geoloc.dao;

import es.upm.dit.geoloc.dao.model.Thought;

public interface ThoughtDAO {
	
	public Thought createThought(Thought thought);
	public Thought readThought(int id);
	public void updateThought(Thought thought);
	public void deleteThought(Thought thought);
	
}