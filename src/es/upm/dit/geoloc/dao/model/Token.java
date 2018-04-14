package es.upm.dit.geoloc.dao.model;
import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Token implements Serializable{

	@Id
	private double id;
	@OneToOne
	private double userId;
	private String token;
	//private DATE expires;
	
	
	
	public Token() {
		
	}

	public double getId() {
		return id;
	}
	
	public void setId(double id) {
		this.id = id;
	}

	public double getUserId() {
		return userId;
	}


	public void setUserId(double userId) {
		this.userId = userId;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

}
