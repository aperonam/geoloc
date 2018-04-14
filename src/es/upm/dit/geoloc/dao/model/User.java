package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	@Id
	private double id;
	private String email;
	//private DATE createdAt;
	
	
	public User() {		
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getId() {
		return id;
	}


	public void setId(double id) {
		this.id = id;
	}

}
