package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	@Id
	private int id;
	//private DATE createdAt;
	
	
	public User() {		
		
	}
	
	public double getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
