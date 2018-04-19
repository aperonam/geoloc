package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User implements Serializable{

	private static final long serialVersionUID = -144978489789589L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long UserID;
	//private DATE createdAt;
	
	
	public User() {		
		
	}


	public long getUserID() {
		return UserID;
	}


	public void setUserID(long UserID) {
		this.UserID = UserID;
	}
	
	public int getId() {
		return id;
	}

}