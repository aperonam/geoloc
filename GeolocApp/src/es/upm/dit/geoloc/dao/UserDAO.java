package es.upm.dit.geoloc.dao;

import es.upm.dit.geoloc.dao.model.User;

public interface UserDAO {

	public void createUser(User user);
	public User readUser(long UserId);
	public void updateUser(User user);
	public void deleteUser(User user);
}